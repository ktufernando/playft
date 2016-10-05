package ar.com.jf.antilavado.service.alert;

import ar.com.jf.antilavado.repository.contant.StatusTypes;
import ar.com.jf.antilavado.repository.dto.response.alert.CountAlerts;
import ar.com.jf.antilavado.repository.dto.response.alert.GridAlert;
import ar.com.jf.antilavado.repository.interfaces.*;
import ar.com.jf.antilavado.repository.model.*;
import ar.com.jf.antilavado.service.exceptions.ServiceException;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

/**
 * AlertManagementServiceImpl.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 05/05/2016.
 */
@Service
public class AlertManagementServiceImpl implements AlertManagementService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlertManagementServiceImpl.class);

    @Autowired
    private AlertManagementRepository alertManagementRepository;

    @Autowired
    private StatusTypeRepository statusTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageSource messageSource;

    @Override
    @Transactional(readOnly = true)
    public List<GridAlert> findAll() {
        return alertManagementRepository.findAllGrid();
    }

    @Override
    @Transactional(readOnly = true)
    public List<GridAlert> findAssignedAlerts() {
        return alertManagementRepository.findAlertsByStatus(StatusTypes.ALERT_ASIGNADA.getCode());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GridAlert> findNoTreatmentAlerts() {
        return alertManagementRepository.findAlertsByStatus(StatusTypes.ALERT_SIN_TRATAMIENTO.getCode());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GridAlert> findClosedAlerts() {
        return alertManagementRepository.findAlertsByStatus(StatusTypes.ALERT_CERRADA.getCode());
    }

    @Override
    @Transactional(readOnly = true)
    public List<GridAlert> findSuspiciousAlerts() {
        return alertManagementRepository.findAlertsByStatus(StatusTypes.ALERT_ES_SOSPECHOSA.getCode());
    }

    @Override
    @Transactional(readOnly = true)
    public AlertManagement getBy(Long id) {
        return alertManagementRepository.get(id);
    }

    @Override
    @Transactional
    public void update(AlertManagement alert) {
        if(!alert.getUnusualOperation().getId().equals(StatusTypes.ALERT_SIN_TRATAMIENTO.getCode()) && Strings.isNullOrEmpty(alert.getMonitoring())){
            throw new ServiceException(messageSource.getMessage("alerts.validation.monitoring", null, Locale.getDefault()), "Update Alert: Validation exception");
        }
        if(alert.getUnusualOperation().getId().equals(StatusTypes.ALERT_ES_SOSPECHOSA.getCode()) && alert.getUifReport() == null){
            throw new ServiceException(messageSource.getMessage("alerts.validation.uidReport", null, Locale.getDefault()), "Update Alert: Validation exception");
        }

        AlertManagement alertModel = alertManagementRepository.get(alert.getId());
        alertModel.setMonitoring(alert.getMonitoring());
        alertModel.setUifReport(alert.getUifReport());
        if(alert.getAssignedUser() != null){
            User userModel = userRepository.get(alert.getAssignedUser().getId());
            alertModel.setAssignedUser(userModel);
        }else{
            alertModel.setAssignedUser(null);
        }
        if(alert.getUnusualOperation().getId().equals(StatusTypes.ALERT_SIN_TRATAMIENTO.getCode()) && alertModel.getAssignedUser() != null){
            StatusType status = statusTypeRepository.get(StatusTypes.ALERT_ASIGNADA.getCode());
            alertModel.setUnusualOperation(status);
            status.getAlerts().add(alertModel);
        }else if(alertModel.getAssignedUser() == null && alert.getUnusualOperation().getId().equals(StatusTypes.ALERT_ASIGNADA.getCode())){
            StatusType status = statusTypeRepository.get(StatusTypes.ALERT_SIN_TRATAMIENTO.getCode());
            alertModel.setUnusualOperation(status);
            status.getAlerts().add(alertModel);
        }else{
            alertModel.setUnusualOperation(alert.getUnusualOperation());
        }
        alertManagementRepository.auditUpdate(alertModel);
        alertManagementRepository.update(alertModel);
    }

    @Override
    @Transactional(readOnly = true)
    public CountAlerts counts() {
        CountAlerts counts = new CountAlerts();
        counts.setAssigned(alertManagementRepository.countAlertsByStatus(StatusTypes.ALERT_ASIGNADA.getCode()));
        counts.setClosed(alertManagementRepository.countAlertsByStatus(StatusTypes.ALERT_CERRADA.getCode()));
        counts.setNotTreatment(alertManagementRepository.countAlertsByStatus(StatusTypes.ALERT_SIN_TRATAMIENTO.getCode()));
        counts.setIsSuspicious(alertManagementRepository.countAlertsByStatus(StatusTypes.ALERT_ES_SOSPECHOSA.getCode()));
        return counts;
    }

}
