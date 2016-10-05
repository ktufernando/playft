package ar.com.jf.antilavado.service.matrix;

import ar.com.jf.antilavado.repository.dto.response.matrix.RiskMatrix;
import ar.com.jf.antilavado.repository.model.MatrixMaster;

import java.util.List;

/**
 * MatrixService.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 01/12/2015.
 */
public interface MatrixService {

    RiskMatrix getMatrixBy(Long clientId);
    void matrixForClient(Long clientId, String username);
    void matrixForAllClients(String username);
    void matrixForAllClients();
    void save(MatrixMaster master);
}
