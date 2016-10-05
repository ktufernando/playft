package ar.com.jf.antilavado.service.ftp;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

/**
 * FtpService.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by ktufernando on 10/11/2015.
 */
public interface FtpService {

    List<String> listFiles(String path) throws Exception;

    void downloadFile(String path, OutputStream out) throws Exception;

    List<String> downloadFileAndReadLines(String path) throws Exception;

    void uploadFile(String path, InputStream in) throws Exception;

    void deleteFile(String path) throws Exception;
}
