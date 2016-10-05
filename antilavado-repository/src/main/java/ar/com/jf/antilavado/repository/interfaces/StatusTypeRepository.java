package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.contant.StatusTypes;
import ar.com.jf.antilavado.repository.generic.GenericRepository;
import ar.com.jf.antilavado.repository.model.StatusType;

import java.util.List;

/**
 * StatusTypeRepository.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 13/01/2016.
 */
public interface StatusTypeRepository extends GenericRepository<Long, StatusType> {

    List<StatusType> findAllByParent(StatusTypes statusTypes);
}
