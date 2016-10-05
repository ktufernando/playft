package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.MatrixDetailRepository;
import ar.com.jf.antilavado.repository.model.MatrixDetail;
import org.springframework.stereotype.Repository;

/**
 * MatrixDetailRepositoryImpl.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 19/01/2015.
 */
@Repository
public class MatrixDetailRepositoryImpl extends AbstractGenericRepository<Long, MatrixDetail> implements MatrixDetailRepository {

    @Override
    public Class getDomainClass() {
        return MatrixDetail.class;
    }

}
