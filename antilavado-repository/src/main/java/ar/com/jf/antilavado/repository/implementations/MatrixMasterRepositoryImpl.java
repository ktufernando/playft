package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.MatrixMasterRepository;
import ar.com.jf.antilavado.repository.model.DetailRiskFactor;
import ar.com.jf.antilavado.repository.model.MasterRiskFactor;
import ar.com.jf.antilavado.repository.model.MatrixDetail;
import ar.com.jf.antilavado.repository.model.MatrixMaster;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * MatrixMasterRepositoryImpl.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 19/01/2015.
 */
@Repository
public class MatrixMasterRepositoryImpl extends AbstractGenericRepository<Long, MatrixMaster> implements MatrixMasterRepository {

    @Override
    public Class getDomainClass() {
        return MatrixMaster.class;
    }

    @Override
    public MatrixMaster loadBy(Long clientId) {
        return (MatrixMaster)getSession().createQuery(
                "from MatrixMaster m where m.client.id = :clientId")
                .setLong("clientId", clientId)
                .uniqueResult();
    }

    @Override
    public void deleteAll(){
        getSession().createQuery("delete from MatrixDetail").executeUpdate();
        getSession().createQuery("delete from MatrixErrorLog").executeUpdate();
        getSession().createQuery("delete from MatrixMaster").executeUpdate();
    }

    @Override
    public void saveFlushAndClear(MatrixMaster matrix){
        getSession().save(matrix);
        for (MatrixDetail detail : matrix.getMatrixDetails()){
            getSession().save(detail);
        }
        getSession().flush();
        getSession().clear();
    }

}
