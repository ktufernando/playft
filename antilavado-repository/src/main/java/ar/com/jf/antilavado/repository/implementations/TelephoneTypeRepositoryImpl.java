package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.TelephoneTypeRepository;
import ar.com.jf.antilavado.repository.model.TelephoneType;
import org.springframework.stereotype.Repository;

/**
 * TelephoneTypeRepositoryImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 26/07/2015.
 */
@Repository
public class TelephoneTypeRepositoryImpl extends AbstractGenericRepository<Long, TelephoneType> implements TelephoneTypeRepository {

    @Override
    public Class getDomainClass() {
        return TelephoneType.class;
    }

}
