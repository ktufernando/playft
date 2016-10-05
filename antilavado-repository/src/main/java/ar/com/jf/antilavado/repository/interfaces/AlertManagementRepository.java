package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.dto.response.alert.GridAlert;
import ar.com.jf.antilavado.repository.generic.GenericRepository;
import ar.com.jf.antilavado.repository.model.AlertManagement;
import ar.com.jf.antilavado.repository.model.ClientTransaction;

import java.util.List;

/**
 * AlertManagementRepository.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 17/03/2016.
 */
public interface AlertManagementRepository extends GenericRepository<Long, AlertManagement> {

    List<GridAlert> findAllGrid();

    List<GridAlert> findAlertsByStatus(Long code);

    Long countAlertsByStatus(Long code);
}
