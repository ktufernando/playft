package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.LocalityRepository;
import ar.com.jf.antilavado.repository.model.Locality;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * LocalityRepositoryImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 26/07/2015.
 */
@Repository
public class LocalityRepositoryImpl extends AbstractGenericRepository<Long, Locality> implements LocalityRepository {

    @Override
    public Class getDomainClass() {
        return Locality.class;
    }

    @Override
    public List<Locality> findBy(Long districtId) {
        return getSession().createQuery("from Locality d where d.district.id = :id").setLong("id", districtId).list();
    }

}
