package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.BankRepository;
import ar.com.jf.antilavado.repository.model.Bank;
import org.springframework.stereotype.Repository;

/**
 * BankRepositoryImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 *  * Created by fvaldes on 26/07/2015.
 */
@Repository
public class BankRepositoryImpl extends AbstractGenericRepository<Long, Bank> implements BankRepository {

    @Override
    public Class getDomainClass() {
        return Bank.class;
    }

}
