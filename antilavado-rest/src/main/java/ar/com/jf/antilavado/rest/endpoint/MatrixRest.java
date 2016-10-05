package ar.com.jf.antilavado.rest.endpoint;

import ar.com.jf.antilavado.repository.dto.response.matrix.RiskMatrix;
import ar.com.jf.antilavado.rest.annotation.PerformanceLog;
import ar.com.jf.antilavado.service.matrix.MatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * MatrixRest.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 01/12/2015.
 */
@RestController
@RequestMapping(value = "/user")
public class MatrixRest {

    @Autowired
    private MatrixService matrixService;

    @PerformanceLog
    @RequestMapping(value = "/matrix/{clientId}", method = RequestMethod.GET)
    @ResponseBody
    public RiskMatrix get(@PathVariable Long clientId) {
        return this.matrixService.getMatrixBy(clientId);
    }

    @PerformanceLog
    @RequestMapping(value = "/matrix/all", method = RequestMethod.POST)
    @ResponseBody
    public void matrixForAllClients(Principal principal) {
        this.matrixService.matrixForAllClients(principal.getName());
    }

    @PerformanceLog
    @RequestMapping(value = "/matrix/{clientId}", method = RequestMethod.PUT)
    @ResponseBody
    public void matrixForClient(@PathVariable Long clientId, Principal principal) {
        this.matrixService.matrixForClient(clientId, principal.getName());
    }

}
