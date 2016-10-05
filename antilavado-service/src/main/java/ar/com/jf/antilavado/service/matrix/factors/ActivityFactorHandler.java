package ar.com.jf.antilavado.service.matrix.factors;

import ar.com.jf.antilavado.repository.dto.response.matrix.FactorData;
import ar.com.jf.antilavado.repository.dto.response.matrix.RiskMatrix;
import ar.com.jf.antilavado.repository.interfaces.AFIPActivityRepository;
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
 * ActivityFactorHandler.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 03/12/2015.
 */
@Component
public class ActivityFactorHandler implements FactorHandler {

    private static Long FACTOR_ID = 12L;

    @Autowired
    private DetailRiskFactorRepository detailRiskFactorRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AFIPActivityRepository afipActivityRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    public void handleFactor(RiskMatrix riskMatrix, Long clientId) {

        String afipActivityCode = clientRepository.getAfipActivity(clientId);
        if (Strings.isNullOrEmpty(afipActivityCode)) {
            throw new FactorHandlerException(messageSource.getMessage("riskmatrix.factorhandler.activity.null", null, Locale.getDefault()));
        }

        String activityDescription = afipActivityRepository.getActivityDescriptionBy(afipActivityCode.substring(1));

        DetailRiskFactor detailRiskFactor = detailRiskFactorRepository.loadBy(FACTOR_ID, activityDescription);
        if (detailRiskFactor == null) {
            throw new FactorHandlerException(messageSource.getMessage("riskmatrix.factorhandler.activity.notdetail", new Object[]{activityDescription}, Locale.getDefault()));
        }

        riskMatrix.addFactorData(new FactorData(detailRiskFactor.getMasterRiskFactor().getDescription(), detailRiskFactor.getDescription(), detailRiskFactor.getMasterRiskFactor().getPonderation() + detailRiskFactor.getPonderation()));

    }
}
