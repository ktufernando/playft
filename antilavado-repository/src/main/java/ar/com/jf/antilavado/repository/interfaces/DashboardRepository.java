package ar.com.jf.antilavado.repository.interfaces;

import ar.com.jf.antilavado.repository.model.view.TotalClientsByFactorLevelsView;

import java.util.List;

/**
 * DashboardRepository.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by fvaldes on 16/02/2016.
 */
public interface DashboardRepository {

    List<TotalClientsByFactorLevelsView> totalLevels();

}
