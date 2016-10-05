package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.DomicileRepository;
import ar.com.jf.antilavado.repository.interfaces.LicenseRepository;
import ar.com.jf.antilavado.repository.model.Domicile;
import ar.com.jf.antilavado.repository.model.License;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * LicenseRepositoryImpl.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 25/02/2016.
 */
@Repository
public class LicenseRepositoryImpl extends AbstractGenericRepository<Long, License> implements LicenseRepository {

    @Override
    public Class getDomainClass() {
        return License.class;
    }

    @Override
    public License loadLastSave() {
        return (License) getSession().createQuery("from License l where l.createdDate = (select max(ll.createdDate) from License ll)").uniqueResult();
    }
}
