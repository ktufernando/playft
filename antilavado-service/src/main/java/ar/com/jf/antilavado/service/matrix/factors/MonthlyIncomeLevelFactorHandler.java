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

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

/**
 * EconomicProfileFactorHandler.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 03/12/2015.
 */
@Component
public class MonthlyIncomeLevelFactorHandler implements FactorHandler{

    private static Long FACTOR_ID = 5L;

    @Autowired
    private DetailRiskFactorRepository detailRiskFactorRepository;

    @Autowired
    private EconomicFinancialProfileRepository economicFinancialProfileRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public void handleFactor(RiskMatrix riskMatrix, Long clientId) {
        BigDecimal monthlyIncomeLevel = economicFinancialProfileRepository.getMonthleEstimateBy(clientId);

        DetailRiskFactor detailRiskFactor = null;

        if(monthlyIncomeLevel == null){
            //detailRiskFactor = detailRiskFactorRepository.loadBy(FACTOR_ID, DETAIL_NOT_STATE);
            throw new FactorHandlerException(messageSource.getMessage("riskmatrix.factorhandler.monthlyincomelevel.null", null, Locale.getDefault()));
        }

        List<DetailRiskFactor> details = detailRiskFactorRepository.findBy(FACTOR_ID);
        for (DetailRiskFactor d: details) {
            if (d.getValuesType().getValuesTypeExt() != null){
                BigDecimal from = new BigDecimal(d.getValuesType().getValuesTypeExt().getFrom());
                BigDecimal to = new BigDecimal(d.getValuesType().getValuesTypeExt().getTo());
                if(monthlyIncomeLevel.compareTo(from) >= 0 && monthlyIncomeLevel.compareTo(to) <= 0){
                    detailRiskFactor = d;
                }
            }
        }

        if(detailRiskFactor == null){
            throw new FactorHandlerException(messageSource.getMessage("riskmatrix.factorhandler.monthlyincomelevel.notdetail", new Object[]{monthlyIncomeLevel}, Locale.getDefault()));
        }

        riskMatrix.addFactorData(new FactorData(detailRiskFactor.getMasterRiskFactor().getDescription(), detailRiskFactor.getDescription(), detailRiskFactor.getMasterRiskFactor().getPonderation() + detailRiskFactor.getPonderation()));
    }
}
