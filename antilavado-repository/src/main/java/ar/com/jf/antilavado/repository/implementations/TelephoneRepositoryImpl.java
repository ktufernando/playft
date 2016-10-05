package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.TelephoneRepository;
import ar.com.jf.antilavado.repository.model.Telephone;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * TelephoneRepositoryImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 26/07/2015.
 */
@Repository
public class TelephoneRepositoryImpl extends AbstractGenericRepository<Long, Telephone> implements TelephoneRepository {

    @Override
    public Class getDomainClass() {
        return Telephone.class;
    }

    @Override
    public Collection<Telephone> findBy(Long clientId) {
        return getSession().createQuery("from Telephone t where t.client.id = :clientId").setLong("clientId", clientId).list();
    }
}
