package ar.com.jf.antilavado.service.configuration;

import ar.com.jf.antilavado.repository.interfaces.DetailRiskFactorRepository;
import ar.com.jf.antilavado.repository.interfaces.FactorLevelRepository;
import ar.com.jf.antilavado.repository.interfaces.MasterRiskFactorRepository;
import ar.com.jf.antilavado.repository.model.DetailRiskFactor;
import ar.com.jf.antilavado.repository.model.FactorLevel;
import ar.com.jf.antilavado.repository.model.MasterRiskFactor;
import ar.com.jf.antilavado.repository.util.UserAuditorUtils;
import ar.com.jf.antilavado.service.exceptions.ServiceException;
import com.google.common.collect.Collections2;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * ConfigurationServiceImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 17/11/2015.
 */
@Service
public class ConfigurationServiceImpl implements ConfigurationService {

    @Autowired
    private FactorLevelRepository factorLevelRepository;

    @Autowired
    private MasterRiskFactorRepository masterRiskFactorRepository;

    @Autowired
    private DetailRiskFactorRepository detailRiskFactorRepository;

    @Override
    @Transactional(readOnly = true)
    public Collection<FactorLevel> getAllFactorLevels() {
        return factorLevelRepository.findAll();
    }

    @Override
    @Transactional
    public void addFactorLevel(FactorLevel factorLevel) {
        validateLevel(factorLevel);
        factorLevel.setDescription(factorLevel.getDescription().toUpperCase());
        this.factorLevelRepository.auditSave(factorLevel);
        this.factorLevelRepository.save(factorLevel);
    }

    @Override
    @Transactional
    public void updateFactorLevel(FactorLevel factorLevel) {
        validateLevel(factorLevel);
        factorLevel.setDescription(factorLevel.getDescription().toUpperCase());
        this.factorLevelRepository.auditUpdate(factorLevel);
        this.factorLevelRepository.update(factorLevel);
    }

    private void validateLevel(FactorLevel factorLevel) {
        List<FactorLevel> levels = null;
        if(factorLevel.getId() != null){
            levels = this.factorLevelRepository.factorLevelsWithout(factorLevel.getId());
        }else{
            levels = this.factorLevelRepository.findAll();
        }
        int sumPercentage = factorLevel.getPercentage();
        if(CollectionUtils.isNotEmpty(levels)){
            for (FactorLevel l : levels){
                if (l.isActive() && l.getColor() == factorLevel.getColor()) {
                    throw new ServiceException("configuration.level.unique.color", "The color exist in another level");
                }
                if (l.isActive() && l.getDescription().equals(factorLevel.getDescription())) {
                    throw new ServiceException("configuration.level.unique.description", "The description exist in another level");
                }
                sumPercentage = sumPercentage + l.getPercentage();
            }
        }
        if(factorLevel.getLowerBound().compareTo(factorLevel.getUpperBound()) != -1){
            throw new ServiceException("configuration.level.invalid.bounds", "The lower bound is greater than upper bound");
        }

    }

    @Override
    @Transactional
    public void updateAllFactorLevel(List<FactorLevel> factorLevels) {
        int totalPercentage = 0;
        for(FactorLevel level : factorLevels){
            if(level.isActive()){
                totalPercentage = totalPercentage + level.getPercentage();
            }
        }
        if(totalPercentage != 100){
            throw new ServiceException("configuration.level.invalid.percentage", "The sum of all percentages is not 100%");
        }
        for(FactorLevel level : factorLevels){
            this.factorLevelRepository.auditUpdate(level);
            this.factorLevelRepository.update(level);
        }
    }

    @Override
    @Transactional
    public void deleteFactorLevel(Long id) {
        factorLevelRepository.delete(factorLevelRepository.load(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<MasterRiskFactor> getAllMasterRiskFactors() {
        return this.masterRiskFactorRepository.findAll();
    }

    @Override
    @Transactional
    public void addMasterRiskFactor(MasterRiskFactor masterRiskFactor) {
        masterRiskFactor.setDescription(masterRiskFactor.getDescription().toUpperCase());
        for (DetailRiskFactor detail : masterRiskFactor.getDetailRiskFactors()){
            detail.setDescription(masterRiskFactor.getDescription().toUpperCase());
            this.detailRiskFactorRepository.auditSave(detail);
            detail.setMasterRiskFactor(masterRiskFactor);

        }
        this.masterRiskFactorRepository.auditSave(masterRiskFactor);
        this.masterRiskFactorRepository.save(masterRiskFactor);
    }

    @Override
    @Transactional
    public void updateMasterRiskFactor(MasterRiskFactor masterRiskFactor) {
        MasterRiskFactor factorModel = this.masterRiskFactorRepository.load(masterRiskFactor.getId());
        //factorModel.setDescription(masterRiskFactor.getDescription().toUpperCase());
        factorModel.setActive(masterRiskFactor.getActive());
        factorModel.setPonderation(masterRiskFactor.getPonderation());
        this.masterRiskFactorRepository.auditUpdate(factorModel);
        this.masterRiskFactorRepository.update(factorModel);
    }

    @Override
    @Transactional
    public void updateDetailRiskFactor(DetailRiskFactor detailRiskFactor) {
        DetailRiskFactor detailModel = this.detailRiskFactorRepository.load(detailRiskFactor.getId());
        //detailModel.setDescription(detailRiskFactor.getDescription().toUpperCase());
        detailModel.setActive(detailRiskFactor.isActive());
        detailModel.setPonderation(detailRiskFactor.getPonderation());
        this.detailRiskFactorRepository.auditUpdate(detailModel);
        this.detailRiskFactorRepository.update(detailModel);
    }

    @Override
    @Transactional
    public void deleteMasterRiskFactor(Long id) {
        this.masterRiskFactorRepository.delete(masterRiskFactorRepository.load(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<DetailRiskFactor> getAllDetailRiskFactors(Long masterFactorId) {
        return this.detailRiskFactorRepository.findBy(masterFactorId);
    }


}
