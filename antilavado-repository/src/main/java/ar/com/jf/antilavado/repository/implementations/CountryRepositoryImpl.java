package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.CountryRepository;
import ar.com.jf.antilavado.repository.model.Country;
import org.springframework.stereotype.Repository;

/**
 * CountryRepositoryImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 26/07/2015.
 */
@Repository
public class CountryRepositoryImpl extends AbstractGenericRepository<Long, Country> implements CountryRepository {

    @Override
    public Class getDomainClass() {
        return Country.class;
    }

    @Override
    public Integer getTaxationBy(Long clientId) {
        return (Integer)getSession().createQuery("SELECT c.taxation FROM Country c LEFT JOIN c.clients cli WHERE cli.id = :clientId")
                .setLong("clientId", clientId).uniqueResult();
    }
}
