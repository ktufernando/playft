package ar.com.jf.antilavado.service.alert;

import ar.com.jf.antilavado.repository.dto.response.alert.CountAlerts;
import ar.com.jf.antilavado.repository.dto.response.alert.GridAlert;
import ar.com.jf.antilavado.repository.model.AlertManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AlertManagementService.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 05/04/2016.
 */
public interface AlertManagementService {

    List<GridAlert> findAll();

    List<GridAlert> findAssignedAlerts();

    List<GridAlert> findNoTreatmentAlerts();

    List<GridAlert> findClosedAlerts();

    @Transactional(readOnly = true)
    List<GridAlert> findSuspiciousAlerts();

    AlertManagement getBy(Long id);

    void update(AlertManagement alert);

    CountAlerts counts();
}
