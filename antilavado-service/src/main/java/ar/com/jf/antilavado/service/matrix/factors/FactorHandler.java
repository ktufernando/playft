package ar.com.jf.antilavado.service.matrix.factors;

import ar.com.jf.antilavado.repository.dto.response.matrix.RiskMatrix;

/**
 * Created by ktufernando on 03/12/2015.
 */
public interface FactorHandler {

    void handleFactor(RiskMatrix riskMatrix, Long clientId);
}
