package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.generic.GenericRepository;
import ar.com.jf.antilavado.repository.model.DetailRiskFactor;

import java.util.List;

/**
 * DetailRiskFactorRepository.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 17/11/2015.
 */
public interface DetailRiskFactorRepository extends GenericRepository<Long, DetailRiskFactor> {

    List<DetailRiskFactor> findBy(Long masterFactorId);

    DetailRiskFactor loadBy(Long masterFactorId, String description);

    DetailRiskFactor loadBy(Long masterFactorId, Long detailFactorId);

    DetailRiskFactor loadBy(String masterDescription, String detailDescription);

}
