package ar.com.jf.antilavado.service.matrix;

import ar.com.jf.antilavado.repository.dto.response.matrix.RiskMatrix;
import ar.com.jf.antilavado.repository.dto.response.matrix.RiskMatrixCron;
import ar.com.jf.antilavado.service.exceptions.FactorHandlerException;
import ar.com.jf.antilavado.service.matrix.factors.*;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * FactorsProcessor.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 03/12/2015.
 */
@Component
public class FactorsProcessor {

    private List<FactorHandler> handlers = Lists.newArrayList();

    @Autowired
    private FactorHandler activityFactorHandler;
    @Autowired
    private FactorHandler specialCustomerFactorHandler;
    @Autowired
    private FactorHandler legalStructureFactorHandler;
    @Autowired
    private FactorHandler operationFrequencyFactorHandler;
    @Autowired
    private FactorHandler LUTFactorHandler;
    @Autowired
    private FactorHandler monthlyIncomeLevelFactorHandler;
    @Autowired
    private FactorHandler PEPFactorHandler;
    @Autowired
    private FactorHandler ROSFactorHandler;
    @Autowired
    private FactorHandler fiscalSituationFactorHandler;
    @Autowired
    private FactorHandler SOIFactorHandler;
    @Autowired
    private FactorHandler marketTrajectoryFactorHandler;
    @Autowired
    private FactorHandler geographicZoneFactorHandler;

    @PostConstruct
    private void addHandlers() {
        handlers.add(activityFactorHandler);
        handlers.add(specialCustomerFactorHandler);
        handlers.add(legalStructureFactorHandler);
        handlers.add(operationFrequencyFactorHandler);
        handlers.add(LUTFactorHandler);
        handlers.add(monthlyIncomeLevelFactorHandler);
        handlers.add(PEPFactorHandler);
        handlers.add(ROSFactorHandler);
        handlers.add(fiscalSituationFactorHandler);
        handlers.add(SOIFactorHandler);
        handlers.add(marketTrajectoryFactorHandler);
        handlers.add(geographicZoneFactorHandler);
    }

    public void handleOneClient(RiskMatrix riskMatrix, Long clientId) {
        StringBuilder errors = new StringBuilder();
        handleFactors(clientId, errors, riskMatrix);
        if (!Strings.isNullOrEmpty(errors.toString())) {
            throw new FactorHandlerException(errors.toString());
        }
    }

    public void handleOneClient(RiskMatrixCron riskMatrixCron, Long clientId) {
        StringBuilder errors = new StringBuilder();
        RiskMatrix riskMatrix = new RiskMatrix();
        handleFactors(clientId, errors, riskMatrix);
        riskMatrixCron.setRiskMatrix(riskMatrix);
        riskMatrixCron.setErrors(errors.toString());
    }

    private void handleFactors(Long clientId, StringBuilder errors, RiskMatrix riskMatrix) {
        for (FactorHandler handler : handlers) {
            try {
                handler.handleFactor(riskMatrix, clientId);
            } catch (FactorHandlerException e) {
                errors.append(e.getError());
                errors.append("<br/>");
            }
        }
    }

}
