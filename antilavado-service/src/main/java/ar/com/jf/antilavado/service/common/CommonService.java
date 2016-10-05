package ar.com.jf.antilavado.service.common;

import ar.com.jf.antilavado.repository.contant.StatusTypes;
import ar.com.jf.antilavado.repository.dto.response.CodDesc;
import ar.com.jf.antilavado.repository.model.*;

import java.util.List;

/**
 * CommonService.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 14/09/2015.
 */
public interface CommonService {

    List<Bank> banks();

    List<AccountType> accountsType();

    List<DocumentType> documentsType();

    List<TelephoneType> telephonesType();

    List<Country> countries();

    List<Province> provinces(Long countryId);

    List<District> districtsBy(Long provinceId);

    List<Locality> localitiesBy(Long localitiesId);

    List<AFIPActivity> parentAFIPActivities();

    List<AFIPActivity> childrenAFIPActivities(String parent);

    List<CodDesc> tableNames();

    List<CodDesc> columnNames(String tableName) throws Exception;

    List<StatusType> statusTypes(StatusTypes statusType);

    List<ValuesType> valuesTypes(Long parentId);
}
