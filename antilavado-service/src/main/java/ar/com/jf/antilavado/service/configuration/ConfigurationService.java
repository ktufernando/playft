package ar.com.jf.antilavado.service.configuration;

import ar.com.jf.antilavado.repository.model.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * ConfigurationService.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 17/11/2015.
 */
public interface ConfigurationService {

    Collection<FactorLevel> getAllFactorLevels();

    void addFactorLevel(FactorLevel factorLevel);

    void updateFactorLevel(FactorLevel factorLevel);

    @Transactional
    void updateAllFactorLevel(List<FactorLevel> factorLevels);

    void deleteFactorLevel(Long id);

    Collection<MasterRiskFactor> getAllMasterRiskFactors();

    void addMasterRiskFactor(MasterRiskFactor masterRiskFactor);

    void updateMasterRiskFactor(MasterRiskFactor masterRiskFactor);

    void updateDetailRiskFactor(DetailRiskFactor detailRiskFactor);

    void deleteMasterRiskFactor(Long id);

    Collection<DetailRiskFactor> getAllDetailRiskFactors(Long masterFactorId);
}
