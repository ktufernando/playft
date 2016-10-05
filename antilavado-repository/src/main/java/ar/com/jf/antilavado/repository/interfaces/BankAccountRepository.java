package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.generic.GenericRepository;
import ar.com.jf.antilavado.repository.model.BankAccount;

import java.util.Collection;

/**
 * BankAccountRepository.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 14/09/2015.
 */
public interface BankAccountRepository extends GenericRepository<Long, BankAccount> {

    Collection<BankAccount> findBy(Long clientId);
}
