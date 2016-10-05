package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.DetailRiskFactorRepository;
import ar.com.jf.antilavado.repository.model.DetailRiskFactor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * DetailRiskFactorRepositoryImpl.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 17/11/2015.
 */
@Repository
public class DetailRiskFactorRepositoryImpl extends AbstractGenericRepository<Long, DetailRiskFactor> implements DetailRiskFactorRepository {

    @Override
    public Class getDomainClass() {
        return DetailRiskFactor.class;
    }

    @Override
    public List<DetailRiskFactor> findBy(Long masterFactorId) {
        return getSession().createQuery("from DetailRiskFactor d where d.masterRiskFactor.id = :id").setLong("id", masterFactorId).list();
    }

    @Override
    public DetailRiskFactor loadBy(Long masterFactorId, String description) {
        return (DetailRiskFactor) getSession().createQuery(
                "from DetailRiskFactor d where d.masterRiskFactor.id = :id and lower(d.description) like lower(:description)")
                .setLong("id", masterFactorId)
                .setString("description", "%" + description + "%")
                .uniqueResult();
    }

    @Override
    public DetailRiskFactor loadBy(Long masterFactorId, Long valueTypeId) {
        return (DetailRiskFactor) getSession().createQuery(
                "from DetailRiskFactor d where d.masterRiskFactor.id = :masterFactorId and d.valuesType.id = :valueTypeId")
                .setLong("masterFactorId", masterFactorId)
                .setLong("valueTypeId", valueTypeId)
                .uniqueResult();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public DetailRiskFactor loadBy(String masterDescription, String detailDescription) {
        return (DetailRiskFactor) getSession().createQuery(
                "from DetailRiskFactor d where " +
                        "d.masterRiskFactor.description = :masterDescription and " +
                        "d.description = :detailDescription")
                .setString("masterDescription", masterDescription)
                .setString("detailDescription", detailDescription)
                .uniqueResult();
    }

}
