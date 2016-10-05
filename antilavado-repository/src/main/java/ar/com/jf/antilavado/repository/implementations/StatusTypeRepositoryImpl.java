package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.contant.StatusTypes;
import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.StatusTypeRepository;
import ar.com.jf.antilavado.repository.model.StatusType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * StatusTypeRepositoryImpl.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 13/01/2016.
 */
@Repository
public class StatusTypeRepositoryImpl extends AbstractGenericRepository<Long, StatusType> implements StatusTypeRepository {

    @Override
    public Class getDomainClass() {
        return StatusType.class;
    }

    @Override
    public List<StatusType> findAllByParent(StatusTypes statusTypes) {
        return (List<StatusType>) getSession().createQuery("FROM StatusType s WHERE s.parent.id = :parent")
                .setLong("parent", statusTypes.getCode()).list();
    }
}
