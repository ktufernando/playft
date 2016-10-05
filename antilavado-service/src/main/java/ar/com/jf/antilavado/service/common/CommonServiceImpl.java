package ar.com.jf.antilavado.service.common;

import ar.com.jf.antilavado.repository.contant.StatusTypes;
import ar.com.jf.antilavado.repository.dto.response.CodDesc;
import ar.com.jf.antilavado.repository.interfaces.*;
import ar.com.jf.antilavado.repository.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CommonServiceImpl.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 14/09/2015.
 */
@Service
@Transactional(readOnly = true)
public class CommonServiceImpl implements CommonService {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private AccountTypeRepository accountTypeRepository;

    @Autowired
    private DocumentTypeRepository documentTypeRepository;

    @Autowired
    private TelephoneTypeRepository telephoneTypeRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private LocalityRepository localityRepository;

    @Autowired
    private AFIPActivityRepository afipActivity;

    @Autowired
    private MasterRiskFactorRepository masterRiskFactorRepository;

    @Autowired
    private StatusTypeRepository statusTypeRepository;

    @Autowired
    private ValuesTypeRepository valuesTypeRepository;

    @Override
    public List<Bank> banks() {
        return bankRepository.findAll();
    }

    @Override
    public List<AccountType> accountsType() {
        return accountTypeRepository.findAll();
    }

    @Override
    public List<DocumentType> documentsType() {
        return documentTypeRepository.findAll();
    }

    @Override
    public List<TelephoneType> telephonesType() {
        return telephoneTypeRepository.findAll();
    }

    @Override
    public List<Country> countries() {
        return countryRepository.findAll();
    }

    @Override
    public List<Province> provinces(Long countryId) {
        return provinceRepository.findBy(countryId);
    }

    @Override
    public List<District> districtsBy(Long provinceId) {
        return districtRepository.findBy(provinceId);
    }

    @Override
    public List<Locality> localitiesBy(Long districtId) {
        return localityRepository.findBy(districtId);
    }

    @Override
    public List<AFIPActivity> parentAFIPActivities() {
        return afipActivity.getParentActivities();
    }

    @Override
    public List<AFIPActivity> childrenAFIPActivities(String parent) {
        return afipActivity.getChildremActivities(parent);
    }

    @Override
    public List<CodDesc> tableNames() {
        return masterRiskFactorRepository.tableNames();
    }

    @Override
    public List<CodDesc> columnNames(String tableName) throws Exception {
        return masterRiskFactorRepository.columnNames(tableName);
    }

    @Override
    public List<StatusType> statusTypes(StatusTypes statusType) {
        return statusTypeRepository.findAllByParent(statusType);
    }

    @Override
    public List<ValuesType> valuesTypes(Long parentId) {
        return valuesTypeRepository.findAllByParent(parentId);
    }
}
