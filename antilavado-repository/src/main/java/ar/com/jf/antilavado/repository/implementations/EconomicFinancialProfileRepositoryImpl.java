package ar.com.jf.antilavado.repository.implementations;

import ar.com.jf.antilavado.repository.generic.AbstractGenericRepository;
import ar.com.jf.antilavado.repository.interfaces.EconomicFinancialProfileRepository;
import ar.com.jf.antilavado.repository.model.EconomicFinancialProfile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * EconomicFinancialProfileRepositoryImpl.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 26/07/2015.
 */
@Repository
public class EconomicFinancialProfileRepositoryImpl extends AbstractGenericRepository<Long, EconomicFinancialProfile> implements EconomicFinancialProfileRepository {

    @Override
    public Class getDomainClass() {
        return EconomicFinancialProfile.class;
    }

    @Override
    public BigDecimal getMonthleEstimateBy(Long clientId) {
        return (BigDecimal)getSession().createQuery("SELECT e.monthlyEstimate FROM EconomicFinancialProfile e LEFT JOIN e.clients c WHERE c.id = :clientId")
                .setLong("clientId", clientId).uniqueResult();
    }

    @Override
    public Long getFiscaSituationBy(Long clientId) {
        return (Long)getSession().createQuery("SELECT f.id FROM EconomicFinancialProfile e JOIN e.fiscalSituation f LEFT JOIN e.clients c  WHERE c.id = :clientId")
                .setLong("clientId", clientId).uniqueResult();
    }
}
