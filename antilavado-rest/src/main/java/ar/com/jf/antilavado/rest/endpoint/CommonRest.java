package ar.com.jf.antilavado.rest.endpoint;

import ar.com.jf.antilavado.repository.contant.AlertFrequency;
import ar.com.jf.antilavado.repository.contant.StatusTypes;
import ar.com.jf.antilavado.repository.dto.response.BasicResponse;
import ar.com.jf.antilavado.repository.dto.response.CodDesc;
import ar.com.jf.antilavado.repository.model.*;
import ar.com.jf.antilavado.rest.annotation.PerformanceLog;
import ar.com.jf.antilavado.service.common.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * CommonRest.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by ktufernando on 30/10/2015.
 */
@RestController
@RequestMapping(value = "/user/common")
public class CommonRest {

    @Autowired
    private CommonService commonService;

    @Value("${alerts.process.frequency}")
    private AlertFrequency frequency;

    @PerformanceLog
    @RequestMapping(value = "/banks", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Bank> banks() {
        return this.commonService.banks();
    }

    @PerformanceLog
    @RequestMapping(value = "/accountsTypes", method = RequestMethod.GET)
    public
    @ResponseBody
    List<AccountType> accountsType() {
        return this.commonService.accountsType();
    }

    @PerformanceLog
    @RequestMapping(value = "/documentsType", method = RequestMethod.GET)
    public
    @ResponseBody
    List<DocumentType> documentsType() {
        return this.commonService.documentsType();
    }

    @PerformanceLog
    @RequestMapping(value = "/telephonesType", method = RequestMethod.GET)
    public
    @ResponseBody
    List<TelephoneType> telephonesType() {
        return this.commonService.telephonesType();
    }

    @PerformanceLog
    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Country> countries() {
        return this.commonService.countries();
    }

    @PerformanceLog
    @RequestMapping(value = "/{countryId}/provinces", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Province> provinces(@PathVariable Long countryId) {
        return this.commonService.provinces(countryId);
    }

    @PerformanceLog
    @RequestMapping(value = "/{provinceId}/districts", method = RequestMethod.GET)
    public
    @ResponseBody
    List<District> districtsBy(@PathVariable Long provinceId) {
        return this.commonService.districtsBy(provinceId);
    }

    @PerformanceLog
    @RequestMapping(value = "/{districtId}/localities", method = RequestMethod.GET)
    public
    @ResponseBody
    List<Locality> localitiesBy(@PathVariable Long districtId) {
        return this.commonService.localitiesBy(districtId);
    }

    @PerformanceLog
    @RequestMapping(value = "/parentAFIPActivities", method = RequestMethod.GET)
    public
    @ResponseBody
    List<AFIPActivity> parentAFIPActivities() {
        return this.commonService.parentAFIPActivities();
    }

    @PerformanceLog
    @RequestMapping(value = "/childrenAFIPActivities/{parent}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<AFIPActivity> childrenAFIPActivities(@PathVariable String parent) {
        return this.commonService.childrenAFIPActivities(parent);
    }

    @PerformanceLog
    @RequestMapping(value = "/tableNames", method = RequestMethod.GET)
    public
    @ResponseBody
    List<CodDesc> tableNames() {
        return this.commonService.tableNames();
    }

    @PerformanceLog
    @RequestMapping(value = "/columnNames/{tableName}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<CodDesc> columnNames(@PathVariable String tableName) throws Exception {
        return this.commonService.columnNames(tableName);
    }

    @PerformanceLog
    @RequestMapping(value = "/statusTypes/{type}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<StatusType> statusTypes(@PathVariable StatusTypes type) {
        return this.commonService.statusTypes(type);
    }

    @PerformanceLog
    @RequestMapping(value = "/valuesTypes/{parentId}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<ValuesType> statusTypes(@PathVariable Long parentId) {
        return this.commonService.valuesTypes(parentId);
    }

    @RequestMapping(value = "/frequency/label", method = RequestMethod.GET)
    public
    @ResponseBody
    BasicResponse getFrequencyLabel() {
        return new BasicResponse(this.frequency.getLabel());
    }
}
