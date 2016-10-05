package ar.com.jf.antilavado.service.ftp;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 * FtpServiceImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */
@Service
public class FtpServiceImpl implements FtpService {

    private static final Logger LOGGER = LoggerFactory
            .getLogger(FtpServiceImpl.class);

    @Value("${ftp.host}")
    private String host;

    @Value("${ftp.port}")
    private int port;

    @Value("${ftp.username}")
    private String username;

    @Value("${ftp.password}")
    private String password;

    public List<String> listFiles(String path) throws Exception {
        JSch sftp = new JSch();
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            session = sftp.getSession(username,
                    host, port);
            session.setPassword(password);
            Properties prop = new Properties();
            prop.put("StrictHostKeyChecking", "no");
            session.setConfig(prop);
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();

            for (String p: path.split("/")) {
                if(!Strings.isNullOrEmpty(p)){
                    try {
                        channelSftp.lstat(p);
                    }catch (Exception ex){
                        LOGGER.error("No existe el path: " + channelSftp.pwd() + p  + ". Creandolo...");
                        channelSftp.mkdir(p);
                    }
                    channelSftp.cd(p);
                }
            }

            Vector<ChannelSftp.LsEntry> entries = channelSftp.ls(path + "*");
            List<String> filesNames = Lists.newArrayList();
            for (ChannelSftp.LsEntry entry : entries) {
                filesNames.add(entry.getFilename());
            }
            return filesNames;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        } finally {
            if (channelSftp != null && channelSftp.isConnected())
                channelSftp.disconnect();
            if (session != null && session.isConnected())
                session.disconnect();
        }
    }

    public void downloadFile(String path,
                             OutputStream out) throws Exception {
        JSch sftp = new JSch();
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            session = sftp.getSession(username,
                    host, port);
            session.setPassword(password);
            Properties prop = new Properties();
            prop.put("StrictHostKeyChecking", "no");
            session.setConfig(prop);
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.get(path, out);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        } finally {
            if (channelSftp != null && channelSftp.isConnected())
                channelSftp.disconnect();
            if (session != null && session.isConnected())
                session.disconnect();
        }
    }

    public List<String> downloadFileAndReadLines(String path) throws Exception {
        JSch sftp = new JSch();
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            session = sftp.getSession(username,
                    host, port);
            session.setPassword(password);
            Properties prop = new Properties();
            prop.put("StrictHostKeyChecking", "no");
            session.setConfig(prop);
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            return IOUtils.readLines(channelSftp.get(path));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        } finally {
            if (channelSftp != null && channelSftp.isConnected())
                channelSftp.disconnect();
            if (session != null && session.isConnected())
                session.disconnect();
        }
    }

    public void uploadFile(String path,
                             InputStream in) throws Exception {
        JSch sftp = new JSch();
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            session = sftp.getSession(username,
                    host, port);
            session.setPassword(password);
            Properties prop = new Properties();
            prop.put("StrictHostKeyChecking", "no");
            session.setConfig(prop);
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.put(in, path);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        } finally {
            if (channelSftp != null && channelSftp.isConnected())
                channelSftp.disconnect();
            if (session != null && session.isConnected())
                session.disconnect();
        }
    }

    public void deleteFile(String path) throws Exception {
        JSch sftp = new JSch();
        Session session = null;
        ChannelSftp channelSftp = null;
        try {
            session = sftp.getSession(username,
                    host, port);
            session.setPassword(password);
            Properties prop = new Properties();
            prop.put("StrictHostKeyChecking", "no");
            session.setConfig(prop);
            session.connect();
            channelSftp = (ChannelSftp) session.openChannel("sftp");
            channelSftp.connect();
            channelSftp.rm(path);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw e;
        } finally {
            if (channelSftp != null && channelSftp.isConnected())
                channelSftp.disconnect();
            if (session != null && session.isConnected())
                session.disconnect();
        }
    }
}
