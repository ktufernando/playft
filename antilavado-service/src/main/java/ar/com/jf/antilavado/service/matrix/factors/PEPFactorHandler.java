package ar.com.jf.antilavado.service.matrix.factors;

import ar.com.jf.antilavado.repository.dto.response.matrix.FactorData;
import ar.com.jf.antilavado.repository.dto.response.matrix.RiskMatrix;
import ar.com.jf.antilavado.repository.interfaces.ClientRepository;
import ar.com.jf.antilavado.repository.interfaces.DetailRiskFactorRepository;
import ar.com.jf.antilavado.repository.model.DetailRiskFactor;
import ar.com.jf.antilavado.service.exceptions.FactorHandlerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * PEPFactorHandler.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 03/12/2015.
 */
@Component
public class PEPFactorHandler implements FactorHandler{

    private static Long FACTOR_ID = 8L;

    @Autowired
    private DetailRiskFactorRepository detailRiskFactorRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public void handleFactor(RiskMatrix riskMatrix, Long clientId) {
        if (!clientRepository.isFisic(clientId)) {
            return;
        }
        Long pep = clientRepository.getPEP(clientId);

        if(pep == null){
            throw new FactorHandlerException(messageSource.getMessage("riskmatrix.factorhandler.pep.null", null, Locale.getDefault()));
        }

        DetailRiskFactor detailRiskFactor = detailRiskFactorRepository.loadBy(FACTOR_ID, pep);

        if(detailRiskFactor == null){
            throw new FactorHandlerException(messageSource.getMessage("riskmatrix.factorhandler.pep.notdetail", new Object[]{pep}, Locale.getDefault()));
        }

        riskMatrix.addFactorData(new FactorData(detailRiskFactor.getMasterRiskFactor().getDescription(), detailRiskFactor.getDescription(), detailRiskFactor.getMasterRiskFactor().getPonderation() + detailRiskFactor.getPonderation()));
    }
}
