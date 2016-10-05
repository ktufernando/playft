package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.MatrixErrorLogRepository;
import ar.com.jf.antilavado.repository.model.MatrixErrorLog;
import ar.com.jf.antilavado.repository.model.MatrixMaster;
import org.springframework.stereotype.Repository;

/**
 * MatrixErrorLogRepositoryImpl.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 19/01/2015.
 */
@Repository
public class MatrixErrorLogRepositoryImpl extends AbstractGenericRepository<Long, MatrixErrorLog> implements MatrixErrorLogRepository {

    @Override
    public Class getDomainClass() {
        return MatrixErrorLog.class;
    }

    @Override
    public MatrixErrorLog loadBy(Long clientId) {
        return (MatrixErrorLog)getSession().createQuery(
                "from MatrixErrorLog m where m.client.id = :clientId")
                .setLong("clientId", clientId)
                .uniqueResult();
    }

}
