package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.DistrictRepository;
import ar.com.jf.antilavado.repository.model.District;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * DistrictRepositoryImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 26/07/2015.
 */
@Repository
public class DistrictRepositoryImpl extends AbstractGenericRepository<Long, District> implements DistrictRepository {

    @Override
    public Class getDomainClass() {
        return District.class;
    }

    @Override
    public List<District> findBy(Long provinceId) {
        return getSession().createQuery("from District d where d.province.id = :id").setLong("id", provinceId).list();
    }
}
