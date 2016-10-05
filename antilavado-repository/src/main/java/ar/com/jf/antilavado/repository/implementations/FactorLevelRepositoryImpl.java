package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.CountryRepository;
import ar.com.jf.antilavado.repository.interfaces.FactorLevelRepository;
import ar.com.jf.antilavado.repository.model.Country;
import ar.com.jf.antilavado.repository.model.FactorLevel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * FactorLevelRepositoryImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 17/11/2015.
 */
@Repository
public class FactorLevelRepositoryImpl extends AbstractGenericRepository<Long, FactorLevel> implements FactorLevelRepository {

    @Override
    public Class getDomainClass() {
        return FactorLevel.class;
    }

    public List<FactorLevel> factorLevelsWithout(Long id){
        return getSession().createQuery("from FactorLevel l where l.id != :id").setLong("id", id).list();
    }

}
