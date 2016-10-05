package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.dto.response.CodDesc;
import ar.com.jf.antilavado.repository.generic.GenericRepository;
import ar.com.jf.antilavado.repository.model.MasterRiskFactor;

import java.util.List;

/**
 * MasterRiskFactorRepository.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 17/11/2015.
 */
public interface MasterRiskFactorRepository extends GenericRepository<Long, MasterRiskFactor> {

    List<CodDesc> tableNames();

    List<CodDesc> columnNames(String tableName) throws Exception;

    String clientValue(String tableName, String columnName, Long clientId);
}
