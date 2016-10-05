package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.dto.response.client.transaction.SumOfTransaccions;
import ar.com.jf.antilavado.repository.generic.GenericRepository;
import ar.com.jf.antilavado.repository.model.ClientTransaction;
import org.joda.time.DateTime;

import java.util.List;

/**
 * ClientTransactionsRepository.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 16/03/2016.
 */
public interface ClientTransactionsRepository extends GenericRepository<Long, ClientTransaction> {

    void saveAndCommit(ClientTransaction clientTransaction);


    List<SumOfTransaccions> findTransactionsBy(DateTime afterDate);
}
