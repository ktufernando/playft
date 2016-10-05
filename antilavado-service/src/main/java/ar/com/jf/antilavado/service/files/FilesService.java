package ar.com.jf.antilavado.service.files;

import ar.com.jf.antilavado.repository.dto.response.files.File;
import ar.com.jf.antilavado.repository.dto.response.handbok.Handbook;
import ar.com.jf.antilavado.repository.model.FileMovement;
import ar.com.jf.antilavado.repository.model.GeneralFile;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

/**
 * FilesService.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by ktufernando on 11/01/2016.
 */
public interface FilesService {

    Collection<GeneralFile> getAllGeneralFiles();

    void addGeneralFile(GeneralFile generalFile);

    void updateGeneralFile(GeneralFile generalFile);

    Collection<FileMovement> getAllFileMovement();

    void addFileMovement(FileMovement fileMovement);

    void updateFileMovement(FileMovement fileMovement);

    List<File> listClientFiles(Long clientId) throws Exception;

    void downloadClientFile(Long clientId, String fileName, OutputStream out) throws Exception;

    void uploadClientFile(Long clientId, String fileName, InputStream in) throws Exception;

    void deleteClientFile(Long clientId, String fileName) throws Exception;


}
