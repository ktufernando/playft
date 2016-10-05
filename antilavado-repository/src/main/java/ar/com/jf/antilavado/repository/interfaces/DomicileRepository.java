package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.generic.GenericRepository;
import ar.com.jf.antilavado.repository.model.Domicile;

import java.util.Collection;
import java.util.List;

/**
 * DomicileRepository.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 14/09/2015.
 */
public interface DomicileRepository extends GenericRepository<Long, Domicile> {

    Collection<Domicile> findBy(Long clientId);
}
