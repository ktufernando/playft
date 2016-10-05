package ar.com.jf.antilavado.rest.endpoint;

import ar.com.jf.antilavado.repository.dto.response.BasicResponse;
import ar.com.jf.antilavado.repository.dto.response.alert.CountAlerts;
import ar.com.jf.antilavado.repository.dto.response.alert.GridAlert;
import ar.com.jf.antilavado.repository.dto.response.client.BasicClient;
import ar.com.jf.antilavado.repository.dto.response.user.BasicUser;
import ar.com.jf.antilavado.repository.model.AlertManagement;
import ar.com.jf.antilavado.repository.model.Client;
import ar.com.jf.antilavado.rest.annotation.PerformanceLog;
import ar.com.jf.antilavado.service.alert.AlertManagementService;
import ar.com.jf.antilavado.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.interceptor.BasicOperation;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * AlertRest.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 06/04/2016.
 */
@RestController
@RequestMapping(value = "/user")
public class AlertRest {

    @Autowired
    private AlertManagementService alertManagementService;

    @PerformanceLog
    @RequestMapping(value = "/alerts", method = RequestMethod.GET)
    public @ResponseBody Collection<GridAlert> findAll() {
        return this.alertManagementService.findAll();
    }

    @PerformanceLog
    @RequestMapping(value = "/alerts/{id}", method = RequestMethod.GET)
    @ResponseBody
    public AlertManagement get(@PathVariable Long id) {
        return this.alertManagementService.getBy(id);
    }

    @PerformanceLog
    @RequestMapping(value = "/alerts/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void update(@RequestBody AlertManagement alertManagement) {
        this.alertManagementService.update(alertManagement);
    }

    @PerformanceLog
    @RequestMapping(value = "/alerts/assigned", method = RequestMethod.GET)
    public @ResponseBody Collection<GridAlert> findAssignedAlerts() {
        return this.alertManagementService.findAssignedAlerts();
    }

    @PerformanceLog
    @RequestMapping(value = "/alerts/nottreatment", method = RequestMethod.GET)
    public @ResponseBody Collection<GridAlert> findNoTreatmentAlerts() {
        return this.alertManagementService.findNoTreatmentAlerts();
    }

    @PerformanceLog
    @RequestMapping(value = "/alerts/closed", method = RequestMethod.GET)
    public @ResponseBody Collection<GridAlert> findClosedAlerts() {
        return this.alertManagementService.findClosedAlerts();
    }

    @PerformanceLog
    @RequestMapping(value = "/alerts/suspicious", method = RequestMethod.GET)
    public @ResponseBody Collection<GridAlert> findSuspiciousAlerts() {
        return this.alertManagementService.findSuspiciousAlerts();
    }

    @PerformanceLog
    @RequestMapping(value = "/alerts/count", method = RequestMethod.GET)
    public @ResponseBody
    CountAlerts counts() {
        return this.alertManagementService.counts();
    }

}
