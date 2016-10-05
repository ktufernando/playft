package ar.com.jf.antilavado.service.matrix.factors;

import ar.com.jf.antilavado.repository.dto.response.matrix.FactorData;
import ar.com.jf.antilavado.repository.dto.response.matrix.RiskMatrix;
import ar.com.jf.antilavado.repository.interfaces.DetailRiskFactorRepository;
import ar.com.jf.antilavado.repository.interfaces.EconomicFinancialProfileRepository;
import ar.com.jf.antilavado.repository.model.DetailRiskFactor;
import ar.com.jf.antilavado.service.exceptions.FactorHandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * TaxpayerTypeFactorHandler.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 03/12/2015.
 */
@Component
public class FiscalSituationFactorHandler implements FactorHandler{

    private static Long FACTOR_ID = 1L;

    @Autowired
    private DetailRiskFactorRepository detailRiskFactorRepository;

    @Autowired
    private EconomicFinancialProfileRepository economicFinancialProfileRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public void handleFactor(RiskMatrix riskMatrix, Long clientId) {
        Long fiscalSituation = economicFinancialProfileRepository.getFiscaSituationBy(clientId);
        if(fiscalSituation == null){
            throw new FactorHandlerException(messageSource.getMessage("riskmatrix.factorhandler.fiscalsituation.null", null, Locale.getDefault()));
        }

        DetailRiskFactor detailRiskFactor = detailRiskFactorRepository.loadBy(FACTOR_ID, fiscalSituation);
        if(detailRiskFactor == null){
            throw new FactorHandlerException(messageSource.getMessage("riskmatrix.factorhandler.fiscalsituation.notdetail", new Object[]{fiscalSituation}, Locale.getDefault()));
        }

        riskMatrix.addFactorData(new FactorData(detailRiskFactor.getMasterRiskFactor().getDescription(), detailRiskFactor.getDescription(), detailRiskFactor.getMasterRiskFactor().getPonderation() + detailRiskFactor.getPonderation()));
    }
}
