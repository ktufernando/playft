package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.generic.GenericRepository;
import ar.com.jf.antilavado.repository.model.AFIPActivity;

import java.util.List;

/**
 * AFIPActivityRepository.java
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by fvaldes on 14/09/2015.
 */
public interface AFIPActivityRepository extends GenericRepository<Long, AFIPActivity> {

    List<AFIPActivity> getParentActivities();

    List<AFIPActivity> getChildremActivities(String parent);

    String getActivityDescriptionBy(String code);

}
