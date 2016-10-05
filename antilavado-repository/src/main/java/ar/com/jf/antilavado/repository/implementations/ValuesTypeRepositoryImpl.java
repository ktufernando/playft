package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.ValuesTypeRepository;
import ar.com.jf.antilavado.repository.model.ValuesType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ValuesTypeRepositoryImpl.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 04/02/2016.
 */
@Repository
public class ValuesTypeRepositoryImpl extends AbstractGenericRepository<Long, ValuesType> implements ValuesTypeRepository {

    @Override
    public Class getDomainClass() {
        return ValuesType.class;
    }

    @Override
    public List<ValuesType> findAllByParent(Long parentId) {
        return (List<ValuesType>) getSession().createQuery("FROM ValuesType s WHERE s.parent.id = :parent")
                .setLong("parent", parentId).list();
    }
}
