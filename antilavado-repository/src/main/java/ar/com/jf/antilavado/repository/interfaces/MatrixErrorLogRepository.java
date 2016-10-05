package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.generic.GenericRepository;
import ar.com.jf.antilavado.repository.model.DetailRiskFactor;
import ar.com.jf.antilavado.repository.model.MatrixErrorLog;
import ar.com.jf.antilavado.repository.model.MatrixMaster;

import java.util.List;

/**
 * MatrixErrorLogRepository.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 19/01/2016.
 */
public interface MatrixErrorLogRepository extends GenericRepository<Long, MatrixErrorLog> {

    MatrixErrorLog loadBy(Long clientId);

}
