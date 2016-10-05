package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.generic.GenericRepository;
import ar.com.jf.antilavado.repository.model.ValuesType;

import java.util.List;

/**
 * ValuesTypeRepository.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 04/02/2016.
 */
public interface ValuesTypeRepository extends GenericRepository<Long, ValuesType> {

    List<ValuesType> findAllByParent(Long parentId);
}
