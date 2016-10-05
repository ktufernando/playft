package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.dto.response.client.BasicClient;
import ar.com.jf.antilavado.repository.dto.response.client.transaction.SumOfTransaccions;
import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.ClientTransactionsRepository;
import ar.com.jf.antilavado.repository.model.ClientTransaction;
import org.hibernate.transform.Transformers;
import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClientTransactionsRepositoryImpl.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 16/03/2016.
 */
@Repository
public class ClientTransactionsRepositoryImpl extends AbstractGenericRepository<Long, ClientTransaction> implements ClientTransactionsRepository {

    @Override
    public Class getDomainClass() {
        return ClientTransaction.class;
    }


    @Override
    public void saveAndCommit(ClientTransaction clientTransaction) {
        this.save(clientTransaction);
        this.getSession().flush();
    }

    @Override
    public List<SumOfTransaccions> findTransactionsBy(DateTime afterDate){
        return getSession().createQuery("SELECT t.client as client, sum(t.invoiceAmount) as sumAmounts FROM ClientTransaction t WHERE t.invoiceDate >= :after GROUP BY t.client")
                .setParameter("after", afterDate)
                .setResultTransformer(Transformers.aliasToBean(SumOfTransaccions.class))
                .list();
    }

}
