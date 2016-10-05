package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.contant.StatusTypes;
import ar.com.jf.antilavado.repository.dto.response.alert.GridAlert;
import ar.com.jf.antilavado.repository.dto.response.client.transaction.SumOfTransaccions;
import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.AlertManagementRepository;
import ar.com.jf.antilavado.repository.model.AlertManagement;
import ar.com.jf.antilavado.repository.model.StatusType;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * AlertManagementRepositoryImpl.java
 * <p>
 * PLAYFT.
 * <p>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p>
 * Created by fvaldes on 17/03/2016.
 */
@Repository
public class AlertManagementRepositoryImpl extends AbstractGenericRepository<Long, AlertManagement> implements AlertManagementRepository {

    @Override
    public Class getDomainClass() {
        return AlertManagement.class;
    }


    @Override
    public List<GridAlert> findAllGrid() {
        StringBuilder q = new StringBuilder("SELECT ");
        q.append("a.id as id, c.names as clientNames, c.lastNames as clientLastNames, ");
        q.append("c.businessName as clientBusinessName, c.individual as clientIsIndividual, ");
        q.append("e.monthlyEstimate as estimatedIncome, ");
        q.append("a.periodAmount as periodAmount, a.difference as difference, ");
        q.append("stuo.description as unusualOperation, ");
        q.append("a.uifReport as uifReport, ");
        q.append("u.firstName as userFirstName, u.lastName as userLastName,");
        q.append("a.periodDescription as periodDescription, a.monitoring as monitoring ");
        q.append("FROM AlertManagement a JOIN a.client c JOIN c.economicFinancialProfile e ");
        q.append("LEFT JOIN a.assignedUser u LEFT JOIN a.unusualOperation stuo ");
        return getSession().createQuery(q.toString())
                .setResultTransformer(Transformers.aliasToBean(GridAlert.class))
                .list();
    }

    @Override
    public List<GridAlert> findAlertsByStatus(Long code) {
        StringBuilder q = new StringBuilder("SELECT ");
        q.append("a.id as id, c.names as clientNames, c.lastNames as clientLastNames, ");
        q.append("c.businessName as clientBusinessName, c.individual as clientIsIndividual, ");
        q.append("e.monthlyEstimate as estimatedIncome, ");
        q.append("a.periodAmount as periodAmount, a.difference as difference, ");
        q.append("stuo.description as unusualOperation, ");
        q.append("a.uifReport as uifReport, ");
        q.append("u.firstName as userFirstName, u.lastName as userLastName,");
        q.append("a.periodDescription as periodDescription, a.monitoring as monitoring ");
        q.append("FROM AlertManagement a JOIN a.client c JOIN c.economicFinancialProfile e ");
        q.append("LEFT JOIN a.assignedUser u LEFT JOIN a.unusualOperation stuo ");
        q.append("WHERE stuo.id = :code");
        return getSession().createQuery(q.toString())
                .setLong("code", code)
                .setResultTransformer(Transformers.aliasToBean(GridAlert.class))
                .list();
    }

    @Override
    public Long countAlertsByStatus(Long code) {
        return (Long)getSession().createQuery(
                "SELECT count(a) FROM AlertManagement a WHERE a.unusualOperation.id = :id")
                .setLong("id", code)
                .uniqueResult();
    }
}
