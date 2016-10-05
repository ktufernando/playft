package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.interfaces.DashboardRepository;
import ar.com.jf.antilavado.repository.model.view.TotalClientsByFactorLevelsView;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClientRepositoryImpl.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 26/07/2015.
 */
@Repository
public class DashboardRepositoryImpl implements DashboardRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TotalClientsByFactorLevelsView> totalLevels() {
        return (List<TotalClientsByFactorLevelsView>) sessionFactory.getCurrentSession().createQuery(
                "FROM TotalClientsByFactorLevelsView")
                .list();
    }

}
