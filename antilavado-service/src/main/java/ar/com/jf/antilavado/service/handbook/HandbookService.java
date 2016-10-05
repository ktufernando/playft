package ar.com.jf.antilavado.service.handbook;

import ar.com.jf.antilavado.repository.dto.response.handbok.Handbook;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * HandbookService.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */
public interface HandbookService {

    List<Handbook> list() throws Exception;

    void downloadFile(String fileName, OutputStream out) throws Exception;

    void uploadFile(String fileName, InputStream in) throws Exception;

    void deleteFile(String fileName) throws Exception;

}
