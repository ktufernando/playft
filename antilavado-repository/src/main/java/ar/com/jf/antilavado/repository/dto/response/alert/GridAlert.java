package ar.com.jf.antilavado.repository.dto.response.alert;

import ar.com.jf.antilavado.repository.model.util.CustomDateTimeDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * GridAlert.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 07/04/16.
 */
public class GridAlert {

    private Long id;
    private String clientNames;
    private String clientLastNames;
    private String clientBusinessName;
    private Boolean clientIsIndividual;
    private BigDecimal estimatedIncome;
    private BigDecimal periodAmount;
    private BigDecimal difference;
    private String unusualOperation;
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    private DateTime uifReport;
    private String userFirstName;
    private String userLastName;
    private String periodDescription;
    private String monitoring;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientNames() {
        return clientNames;
    }

    public void setClientNames(String clientNames) {
        this.clientNames = clientNames;
    }

    public String getClientLastNames() {
        return clientLastNames;
    }

    public void setClientLastNames(String clientLastNames) {
        this.clientLastNames = clientLastNames;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public Boolean getClientIsIndividual() {
        return clientIsIndividual;
    }

    public void setClientIsIndividual(Boolean clientIsIndividual) {
        this.clientIsIndividual = clientIsIndividual;
    }

    public BigDecimal getEstimatedIncome() {
        return estimatedIncome;
    }

    public void setEstimatedIncome(BigDecimal estimatedIncome) {
        this.estimatedIncome = estimatedIncome;
    }

    public BigDecimal getPeriodAmount() {
        return periodAmount;
    }

    public void setPeriodAmount(BigDecimal periodAmount) {
        this.periodAmount = periodAmount;
    }

    public BigDecimal getDifference() {
        return difference;
    }

    public void setDifference(BigDecimal difference) {
        this.difference = difference;
    }

    public String getUnusualOperation() {
        return unusualOperation;
    }

    public void setUnusualOperation(String unusualOperation) {
        this.unusualOperation = unusualOperation;
    }

    public String getClientBusinessName() {
        return clientBusinessName;
    }

    public void setClientBusinessName(String clientBusinessName) {
        this.clientBusinessName = clientBusinessName;
    }

    public String getPeriodDescription() {
        return periodDescription;
    }

    public void setPeriodDescription(String periodDescription) {
        this.periodDescription = periodDescription;
    }

    public DateTime getUifReport() {
        return uifReport;
    }

    public void setUifReport(DateTime uifReport) {
        this.uifReport = uifReport;
    }

    public String getMonitoring() {
        return monitoring;
    }

    public void setMonitoring(String monitoring) {
        this.monitoring = monitoring;
    }
}
