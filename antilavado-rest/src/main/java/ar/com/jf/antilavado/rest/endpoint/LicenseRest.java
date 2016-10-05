package ar.com.jf.antilavado.rest.endpoint;

import ar.com.jf.antilavado.repository.dto.request.license.LicenseRequest;
import ar.com.jf.antilavado.repository.dto.response.license.LicenseData;
import ar.com.jf.antilavado.rest.annotation.PerformanceLog;
import ar.com.jf.antilavado.service.license.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * LicenseRest.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 25/02/2016.
 */
@RestController
@RequestMapping(value = "/anonymous")
public class LicenseRest {

    @Autowired
    private LicenseService licenseService;


    @PerformanceLog
    @RequestMapping(value = "/license", method = RequestMethod.POST)
    @ResponseBody
    public LicenseData addLicense(@RequestBody LicenseRequest request) throws Exception {
        return this.licenseService.addLicense(request.getLicenseString());
    }

    @PerformanceLog
    @RequestMapping(value = "/license", method = RequestMethod.GET)
    @ResponseBody
    public LicenseData getLicense() throws Exception {
        return this.licenseService.getLicenseData();
    }


}
