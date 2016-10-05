package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.generic.GenericRepository;
import ar.com.jf.antilavado.repository.model.Country;
import ar.com.jf.antilavado.repository.model.FactorLevel;

import java.util.List;

/**
 * FactorLevelRepository.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 17/11/2015.
 */
public interface FactorLevelRepository extends GenericRepository<Long, FactorLevel> {

    List<FactorLevel> factorLevelsWithout(Long id);

}
