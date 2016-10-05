package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.generic.GenericRepository;
import ar.com.jf.antilavado.repository.model.Client;
import ar.com.jf.antilavado.repository.model.Country;

/**
 * CountryRepository.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 14/09/2015.
 */
public interface CountryRepository extends GenericRepository<Long, Country> {

    Integer getTaxationBy(Long clientId);

}
