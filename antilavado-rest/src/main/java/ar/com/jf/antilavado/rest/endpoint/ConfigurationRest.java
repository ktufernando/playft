package ar.com.jf.antilavado.rest.endpoint;

import ar.com.jf.antilavado.repository.model.*;
import ar.com.jf.antilavado.rest.annotation.PerformanceLog;
import ar.com.jf.antilavado.service.client.ClientService;
import ar.com.jf.antilavado.service.configuration.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * ConfigurationRest.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 17/11/2015.
 */
@RestController
@RequestMapping(value = "/admin/configuration")
public class ConfigurationRest {

    @Autowired
    private ConfigurationService configurationService;

    @PerformanceLog
    @RequestMapping(value = "/factorLevels", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<FactorLevel> getAllFactorLevels() {
        return this.configurationService.getAllFactorLevels();
    }

    @PerformanceLog
    @RequestMapping(value = "/factorLevels", method = RequestMethod.POST)
    public
    @ResponseBody
    void addFactorLevel(@RequestBody FactorLevel factorLevel) {
        this.configurationService.addFactorLevel(factorLevel);
    }

    @PerformanceLog
    @RequestMapping(value = "/factorLevels/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public void updateFactorLevel(@RequestBody FactorLevel factorLevel) {
        this.configurationService.updateFactorLevel(factorLevel);
    }

    @PerformanceLog
    @RequestMapping(value = "/factorLevels", method = RequestMethod.PUT)
    @ResponseBody
    public void updateAllFactorLevel(@RequestBody List<FactorLevel> factorLevels) {
        this.configurationService.updateAllFactorLevel(factorLevels);
    }

    @PerformanceLog
    @RequestMapping(value = "/factorLevels/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteFactorLevel(@PathVariable Long id) {
        this.configurationService.deleteFactorLevel(id);
    }

    @PerformanceLog
    @RequestMapping(value = "/masterFactors", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<MasterRiskFactor> getAllMasterFactors() {
        return this.configurationService.getAllMasterRiskFactors();
    }

    @PerformanceLog
    @RequestMapping(value = "/masterFactors", method = RequestMethod.POST)
    public
    @ResponseBody
    void addMasterFactor(@RequestBody MasterRiskFactor masterRiskFactor) {
        this.configurationService.addMasterRiskFactor(masterRiskFactor);
    }

    @PerformanceLog
    @RequestMapping(value = "/masterFactors", method = RequestMethod.PUT)
    @ResponseBody
    public void updateMasterFactor(@RequestBody MasterRiskFactor masterRiskFactor) {
        this.configurationService.updateMasterRiskFactor(masterRiskFactor);
    }

    @PerformanceLog
    @RequestMapping(value = "/masterFactors/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteMasterFactor(@PathVariable Long id) {
        this.configurationService.deleteMasterRiskFactor(id);
    }

    @PerformanceLog
    @RequestMapping(value = "/detailFactors/{masterFactorId}", method = RequestMethod.GET)
    public
    @ResponseBody
    Collection<DetailRiskFactor> getAllDetailFactors(@PathVariable Long masterFactorId) {
        return this.configurationService.getAllDetailRiskFactors(masterFactorId);
    }

    @PerformanceLog
    @RequestMapping(value = "/detailFactors", method = RequestMethod.PUT)
    @ResponseBody
    public void updateDetailFactor(@RequestBody DetailRiskFactor detailRiskFactor) {
        this.configurationService.updateDetailRiskFactor(detailRiskFactor);
    }

}
