package ar.com.jf.antilavado.service.files;

import ar.com.jf.antilavado.repository.contant.StatusTypes;
import ar.com.jf.antilavado.repository.dto.response.files.File;
import ar.com.jf.antilavado.repository.dto.response.handbok.Handbook;
import ar.com.jf.antilavado.repository.interfaces.FileMovementRepository;
import ar.com.jf.antilavado.repository.interfaces.GeneralFileRepository;
import ar.com.jf.antilavado.repository.interfaces.StatusTypeRepository;
import ar.com.jf.antilavado.repository.model.FileMovement;
import ar.com.jf.antilavado.repository.model.GeneralFile;
import ar.com.jf.antilavado.repository.model.StatusType;
import ar.com.jf.antilavado.service.exceptions.ServiceException;
import ar.com.jf.antilavado.service.ftp.FtpService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

import static ar.com.jf.antilavado.repository.contant.StatusTypes.GENERAL_FILE_ARCHIVO;
import static ar.com.jf.antilavado.repository.contant.StatusTypes.GENERAL_FILE_EN_MOVIMIENTO;

/**
 * FilesServiceImpl.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 11/01/2016.
 */
@Service
public class FilesServiceImpl implements FilesService {

    @Autowired
    private GeneralFileRepository generalFileRepository;

    @Autowired
    private FileMovementRepository fileMovementRepository;

    @Autowired
    private StatusTypeRepository statusTypeRepository;

    @Autowired
    private FtpService ftpService;

    @Value("${ftp.client.files.path}")
    private String path;

    @Override
    @Transactional(readOnly = true)
    public Collection<GeneralFile> getAllGeneralFiles() {
        return generalFileRepository.findAll();
    }

    @Override
    @Transactional
    public void addGeneralFile(GeneralFile generalFile) {
        if(generalFile.getClient() == null){
            throw new ServiceException("files.client.null","Falto cargar el cliente");
        }
        if(this.generalFileRepository.isTheClientHasAGeneralFile(generalFile.getClient().getId())){
            throw new ServiceException("files.client.has.generalfile","El cliente ya tiene un archivo general cargado");
        }
        generalFile.getStatus().getGeneralFiles().add(generalFile);
        generalFile.getClient().getGeneralFiles().add(generalFile);
        this.generalFileRepository.auditSave(generalFile);
        this.generalFileRepository.save(generalFile);
    }

    @Override
    @Transactional
    public void updateGeneralFile(GeneralFile generalFile) {
        this.generalFileRepository.auditUpdate(generalFile);
        this.generalFileRepository.update(generalFile);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<FileMovement> getAllFileMovement() {
        return fileMovementRepository.findAll();
    }

    @Override
    @Transactional
    public void addFileMovement(FileMovement fileMovement) {
        if(fileMovement.getClient() == null){
            throw new ServiceException("files.client.null","Falto cargar el cliente");
        }
        if(!this.generalFileRepository.isTheClientHasAGeneralFile(fileMovement.getClient().getId())){
            throw new ServiceException("files.client.has.not.generalfile","El cliente ya tiene un archivo general cargado");
        }
        if(DateTimeComparator.getDateOnlyInstance().compare(fileMovement.getOrderDate(), fileMovement.getShippingDate()) > 0){
            throw new ServiceException("files.movement.orderdate.higher.shippingdate","");
        }
        if(fileMovement.getReturnDate() != null){
            if(DateTimeComparator.getDateOnlyInstance().compare(fileMovement.getReturnDate(), fileMovement.getShippingDate()) < 0){
                throw new ServiceException("files.movement.returndate.less.shippingdate","");
            }
            if(DateTimeComparator.getDateOnlyInstance().compare(fileMovement.getReturnDate(), DateTime.now()) > 0){
                throw new ServiceException("files.movement.returndate.higher.today","");
            }
        }
        fileMovement.getClient().getFilesMovement().add(fileMovement);
        this.fileMovementRepository.auditSave(fileMovement);
        this.fileMovementRepository.save(fileMovement);
        updateGeneralFileStatus(fileMovement);
    }

    private void updateGeneralFileStatus(FileMovement fileMovement){
        GeneralFile generalFile = generalFileRepository.getBy(fileMovement.getClient().getId());
        StatusType status = null;
        if(fileMovement.getReturnDate() != null){
            if(generalFile.getStatus().getId().equals(GENERAL_FILE_ARCHIVO.getCode())){
                return;
            }
            status = statusTypeRepository.get(GENERAL_FILE_ARCHIVO.getCode());
        }else{
            if(generalFile.getStatus().getId().equals(GENERAL_FILE_EN_MOVIMIENTO.getCode())){
                return;
            }
            status = statusTypeRepository.get(GENERAL_FILE_EN_MOVIMIENTO.getCode());
        }
        generalFile.setStatus(status);
        status.getGeneralFiles().add(generalFile);
        generalFileRepository.auditUpdate(generalFile);
        generalFileRepository.update(generalFile);
    }

    @Override
    @Transactional
    public void updateFileMovement(FileMovement fileMovement) {
        if(DateTimeComparator.getDateOnlyInstance().compare(fileMovement.getOrderDate(), fileMovement.getShippingDate()) > 0){
            throw new ServiceException("files.movement.orderdate.higher.shippingdate","");
        }
        if(fileMovement.getReturnDate() != null){
            if(DateTimeComparator.getDateOnlyInstance().compare(fileMovement.getReturnDate(), fileMovement.getShippingDate()) < 0){
                throw new ServiceException("files.movement.returndate.less.shippingdate","");
            }
            if(DateTimeComparator.getDateOnlyInstance().compare(fileMovement.getReturnDate(), DateTime.now()) > 0){
                throw new ServiceException("files.movement.returndate.higher.today","");
            }
        }
        this.fileMovementRepository.auditUpdate(fileMovement);
        this.fileMovementRepository.update(fileMovement);
        updateGeneralFileStatus(fileMovement);
    }

    @Override
    public List<File> listClientFiles(Long clientId) throws Exception {
        List<String> filesNames = ftpService.listFiles(path + clientId + "/");
        List<File> files = Lists.newArrayList();
        for(String fileName : filesNames){
            files.add(new File(fileName, getType(fileName)));
        }
        return files;
    }

    @Override
    public void downloadClientFile(Long clientId, String fileName, OutputStream out) throws Exception {
        ftpService.downloadFile(path + clientId + "/" + fileName, out);

    }

    @Override
    public void uploadClientFile(Long clientId, String fileName, InputStream in) throws Exception {
        ftpService.uploadFile(path + clientId + "/" + fileName, in);
    }

    @Override
    public void deleteClientFile(Long clientId, String fileName) throws Exception {
        ftpService.deleteFile(path + clientId + "/" + fileName);
    }

    private String getType(String name){
        if(StringUtils.contains(name.toLowerCase(), "pdf")){
            return "pdf";
        }else if(StringUtils.contains(name.toLowerCase(), "xls")){
            return "excel";
        }else if(StringUtils.contains(name.toLowerCase(), "doc")){
            return "word";
        }else if(StringUtils.contains(name.toLowerCase(), "ppt")){
            return "powerpoint";
        }
        return "image";
    }

}