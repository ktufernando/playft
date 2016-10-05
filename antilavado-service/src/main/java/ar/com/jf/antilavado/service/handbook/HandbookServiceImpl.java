package ar.com.jf.antilavado.service.handbook;

import ar.com.jf.antilavado.repository.dto.response.handbok.Handbook;
import ar.com.jf.antilavado.service.ftp.FtpService;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * HandbookServiceImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */
@Service
public class HandbookServiceImpl implements HandbookService {

    @Autowired
    private FtpService ftpService;

    @Value("${ftp.handbook.path}")
    private String path;

    @Override
    public List<Handbook> list() throws Exception{
        List<String> filesNames = ftpService.listFiles(path);
        List<Handbook> handbooks = Lists.newArrayList();
        for(String fileName : filesNames){
            handbooks.add(new Handbook(fileName, getType(fileName)));
        }
        return handbooks;
    }

    @Override
    public void downloadFile(String fileName, OutputStream out) throws Exception {
        ftpService.downloadFile(path + fileName, out);
    }

    @Override
    public void uploadFile(String fileName, InputStream in) throws Exception {
        ftpService.uploadFile(path + fileName, in);
    }

    @Override
    public void deleteFile(String fileName) throws Exception {
        ftpService.deleteFile(path + fileName);
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
