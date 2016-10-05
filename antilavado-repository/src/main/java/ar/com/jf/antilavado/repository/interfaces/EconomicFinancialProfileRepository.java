package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.generic.GenericRepository;
import ar.com.jf.antilavado.repository.model.EconomicFinancialProfile;

import java.math.BigDecimal;

/**
 * EconomicFinancialProfileRepository.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 14/09/2015.
 */
public interface EconomicFinancialProfileRepository extends GenericRepository<Long, EconomicFinancialProfile> {

    BigDecimal getMonthleEstimateBy(Long clientId);

    Long getFiscaSituationBy(Long clientId);
}
