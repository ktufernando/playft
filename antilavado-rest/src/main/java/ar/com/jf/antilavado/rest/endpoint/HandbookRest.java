package ar.com.jf.antilavado.rest.endpoint;

import ar.com.jf.antilavado.repository.dto.response.BasicResponse;
import ar.com.jf.antilavado.repository.dto.response.handbok.Handbook;
import ar.com.jf.antilavado.rest.annotation.PerformanceLog;
import ar.com.jf.antilavado.service.handbook.HandbookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * HandbookRest.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 11/11/2015.
 */
@RestController
public class HandbookRest {

    @Autowired
    private HandbookService handbookService;

    @PerformanceLog
    @RequestMapping(value = "/user/handbook/list", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    BasicResponse<List<Handbook>> getAll() throws Exception {
        List<Handbook> list = handbookService.list();
        return new BasicResponse(list);
    }


    @PerformanceLog
    @RequestMapping(value = "/anonymous/handbook/{name}/download", method = RequestMethod.GET)
    @ResponseBody
    public void download(@PathVariable String name, HttpServletResponse response) throws Exception {
        String fileName = URLEncoder.encode(name, "UTF-8");
        fileName = URLDecoder.decode(fileName, "ISO8859_1");
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-disposition", "attachment; filename="+ fileName);
        this.handbookService.downloadFile(name, response.getOutputStream());
    }

    @PerformanceLog
    @RequestMapping(value = "/admin/handbook/upload", method = RequestMethod.POST)
    @ResponseBody
    public BasicResponse upload(@RequestParam MultipartFile file) throws Exception {
        this.handbookService.uploadFile(file.getOriginalFilename(), file.getInputStream());
        return new BasicResponse(null);
    }

    @PerformanceLog
    @RequestMapping(value = "/admin/handbook/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public BasicResponse upload(@RequestParam String fileName) throws Exception {
        this.handbookService.deleteFile(fileName);
        return new BasicResponse(null);
    }



}
