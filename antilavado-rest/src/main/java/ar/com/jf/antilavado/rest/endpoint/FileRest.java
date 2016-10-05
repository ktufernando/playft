package ar.com.jf.antilavado.rest.endpoint;

import ar.com.jf.antilavado.repository.dto.response.BasicResponse;
import ar.com.jf.antilavado.repository.dto.response.client.BasicClient;
import ar.com.jf.antilavado.repository.dto.response.files.File;
import ar.com.jf.antilavado.repository.dto.response.handbok.Handbook;
import ar.com.jf.antilavado.repository.model.*;
import ar.com.jf.antilavado.rest.annotation.PerformanceLog;
import ar.com.jf.antilavado.service.client.ClientService;
import ar.com.jf.antilavado.service.configuration.ConfigurationService;
import ar.com.jf.antilavado.service.files.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;

/**
 * FileRest.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 11/01/2016.
 */
@RestController
@RequestMapping(value = "/user/files")
public class FileRest {

    @Autowired
    private FilesService filesService;

    @Autowired
    private ClientService clientService;

    @PerformanceLog
    @RequestMapping(value = "/general", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<GeneralFile> getAllGeneralFiles() {
        return this.filesService.getAllGeneralFiles();
    }

    @PerformanceLog
    @RequestMapping(value = "/general", method = RequestMethod.POST)
    public
    @ResponseBody
    void addGeneralFile(@RequestBody GeneralFile generalFile) {
        this.filesService.addGeneralFile(generalFile);
    }

    @PerformanceLog
    @RequestMapping(value = "/general/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateGeneralFile(@RequestBody GeneralFile generalFile) {
        this.filesService.updateGeneralFile(generalFile);
    }

    @PerformanceLog
    @RequestMapping(value = "/general/clients/{name}", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<BasicClient> findBasicClientsForGeneralFileBy(@PathVariable String name) {
        return this.clientService.findBasicClientsForGeneralFileBy(name);
    }

    @PerformanceLog
    @RequestMapping(value = "/movement", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<FileMovement> getAllFileMovement() {
        return this.filesService.getAllFileMovement();
    }

    @PerformanceLog
    @RequestMapping(value = "/movement", method = RequestMethod.POST)
    public
    @ResponseBody
    void addFileMovement(@RequestBody FileMovement fileMovement) {
        this.filesService.addFileMovement(fileMovement);
    }

    @PerformanceLog
    @RequestMapping(value = "/movement/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateFileMovement(@RequestBody FileMovement fileMovement) {
        this.filesService.updateFileMovement(fileMovement);
    }

    @PerformanceLog
    @RequestMapping(value = "/movement/clients/{name}", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<BasicClient> findBasicClientsForFileMovementBy(@PathVariable String name) {
        return this.clientService.findBasicClientsForFileMovementBy(name);
    }

    @PerformanceLog
    @RequestMapping(value = "/{clientId}/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    BasicResponse<List<File>> getAll(@PathVariable Long clientId) throws Exception {
        List<File> list = filesService.listClientFiles(clientId);
        return new BasicResponse(list);
    }


    @PerformanceLog
    @RequestMapping(value = "/{clientId}/{name}/download", method = RequestMethod.GET)
    @ResponseBody
    public void download(@PathVariable Long clientId, @PathVariable String name, HttpServletResponse response) throws Exception {
        String fileName = URLEncoder.encode(name, "UTF-8");
        fileName = URLDecoder.decode(fileName, "ISO8859_1");
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-disposition", "attachment; filename="+ fileName);
        this.filesService.downloadClientFile(clientId, name, response.getOutputStream());
    }

    @PerformanceLog
    @RequestMapping(value = "/{clientId}/upload", method = RequestMethod.POST)
    @ResponseBody
    public BasicResponse upload(@PathVariable Long clientId, @RequestParam MultipartFile file) throws Exception {
        this.filesService.uploadClientFile(clientId, file.getOriginalFilename(), file.getInputStream());
        return new BasicResponse(null);
    }

    @PerformanceLog
    @RequestMapping(value = "/{clientId}/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public BasicResponse upload(@PathVariable Long clientId, @RequestParam String fileName) throws Exception {
        this.filesService.deleteClientFile(clientId, fileName);
        return new BasicResponse(null);
    }

}
