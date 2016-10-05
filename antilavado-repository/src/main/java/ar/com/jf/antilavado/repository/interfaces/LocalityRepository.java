package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.generic.GenericRepository;
import ar.com.jf.antilavado.repository.model.District;
import ar.com.jf.antilavado.repository.model.Locality;

import java.util.List;

/**
 * LocalityRepository.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 14/09/2015.
 */
public interface LocalityRepository extends GenericRepository<Long, Locality> {

    List<Locality> findBy(Long districtId);

}
