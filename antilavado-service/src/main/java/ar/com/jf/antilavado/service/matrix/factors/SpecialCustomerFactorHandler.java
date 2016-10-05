package ar.com.jf.antilavado.service.matrix.factors;

import ar.com.jf.antilavado.repository.dto.response.matrix.FactorData;
import ar.com.jf.antilavado.repository.dto.response.matrix.RiskMatrix;
import ar.com.jf.antilavado.repository.interfaces.ClientRepository;
import ar.com.jf.antilavado.repository.interfaces.DetailRiskFactorRepository;
import ar.com.jf.antilavado.repository.model.DetailRiskFactor;
import ar.com.jf.antilavado.service.exceptions.FactorHandlerException;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * SpecialCustomerFactorHandler.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 03/12/2015.
 */
@Component
public class SpecialCustomerFactorHandler implements FactorHandler {

    private static Long FACTOR_ID = 6L;

    @Autowired
    private DetailRiskFactorRepository detailRiskFactorRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public void handleFactor(RiskMatrix riskMatrix, Long clientId) {
        Long specialClient = clientRepository.getSpecialClient(clientId);
        if(specialClient == null){
            throw new FactorHandlerException(messageSource.getMessage("riskmatrix.factorhandler.specialClient.null", null, Locale.getDefault()));
        }
        DetailRiskFactor detailRiskFactor = detailRiskFactorRepository.loadBy(FACTOR_ID, specialClient);
        if(detailRiskFactor == null){
            throw new FactorHandlerException(messageSource.getMessage("riskmatrix.factorhandler.specialClient.notdetail", new Object[]{specialClient}, Locale.getDefault()));
        }

        riskMatrix.addFactorData(new FactorData(detailRiskFactor.getMasterRiskFactor().getDescription(), detailRiskFactor.getDescription(), detailRiskFactor.getMasterRiskFactor().getPonderation() + detailRiskFactor.getPonderation()));
    }
}
