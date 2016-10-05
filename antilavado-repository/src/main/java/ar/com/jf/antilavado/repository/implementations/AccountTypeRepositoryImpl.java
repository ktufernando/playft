package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.AccountTypeRepository;
import ar.com.jf.antilavado.repository.model.AccountType;
import org.springframework.stereotype.Repository;

/**
 * AccountTypeRepositoryImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 26/07/2015.
 */
@Repository
public class AccountTypeRepositoryImpl extends AbstractGenericRepository<Long, AccountType> implements AccountTypeRepository {

    @Override
    public Class getDomainClass() {
        return AccountType.class;
    }

}
