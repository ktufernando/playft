package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.generic.GenericRepository;
import ar.com.jf.antilavado.repository.model.Province;

import java.util.List;

/**
 * ProvinceRepository.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 14/09/2015.
 */
public interface ProvinceRepository extends GenericRepository<Long, Province> {

    List<Province> findBy(Long countryId);

}
