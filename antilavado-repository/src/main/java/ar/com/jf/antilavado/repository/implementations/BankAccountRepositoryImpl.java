package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.BankAccountRepository;
import ar.com.jf.antilavado.repository.model.BankAccount;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * BankAccountRepositoryImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 26/07/2015.
 */
@Repository
public class BankAccountRepositoryImpl extends AbstractGenericRepository<Long, BankAccount> implements BankAccountRepository {

    @Override
    public Class getDomainClass() {
        return BankAccount.class;
    }

    @Override
    public Collection<BankAccount> findBy(Long clientId) {
        return getSession().createQuery("from BankAccount t where t.client.id = :clientId").setLong("clientId", clientId).list();
    }
}
