package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.DomicileRepository;
import ar.com.jf.antilavado.repository.model.Domicile;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * DomicileRepositoryImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 26/07/2015.
 */
@Repository
public class DomicileRepositoryImpl extends AbstractGenericRepository<Long, Domicile> implements DomicileRepository {

    @Override
    public Class getDomainClass() {
        return Domicile.class;
    }

    @Override
    public Collection<Domicile> findBy(Long clientId) {
        return getSession().createQuery("from Domicile t where t.client.id = :clientId").setLong("clientId", clientId).list();
    }

}
