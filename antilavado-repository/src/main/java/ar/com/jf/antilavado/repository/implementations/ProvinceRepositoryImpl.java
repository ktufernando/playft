package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.ProvinceRepository;
import ar.com.jf.antilavado.repository.model.Province;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ProvinceRepositoryImpl.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 26/07/2015.
 */
@Repository
public class ProvinceRepositoryImpl extends AbstractGenericRepository<Long, Province> implements ProvinceRepository {

    @Override
    public Class getDomainClass() {
        return Province.class;
    }

    @Override
    public List<Province> findBy(Long countryId) {
        return getSession().createQuery("from Province d where d.country.id = :id").setLong("id", countryId).list();
    }

}
