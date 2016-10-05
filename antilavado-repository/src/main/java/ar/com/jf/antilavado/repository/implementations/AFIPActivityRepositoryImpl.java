package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.AFIPActivityRepository;
import ar.com.jf.antilavado.repository.model.AFIPActivity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AFIPActivityRepositoryImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 26/07/2015.
 */
@Repository
public class AFIPActivityRepositoryImpl extends AbstractGenericRepository<Long, AFIPActivity> implements AFIPActivityRepository {

    @Override
    public Class getDomainClass() {
        return AFIPActivity.class;
    }

    @Override
    public List<AFIPActivity> getParentActivities() {
        return getSession().createQuery("from AFIPActivity a where a.parent is null").list();
    }

    @Override
    public List<AFIPActivity> getChildremActivities(String parent) {
        return getSession().createQuery("from AFIPActivity a where a.parent = :parent").setString("parent", parent).list();
    }

    @Override
    public String getActivityDescriptionBy(String code) {
        return (String) getSession().createQuery("select a.description from AFIPActivity a where a.code = :code").setString("code", code).uniqueResult();
    }
}
