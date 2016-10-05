package ar.com.jf.antilavado.service.dashboard;

import ar.com.jf.antilavado.repository.model.view.TotalClientsByFactorLevelsView;

import java.util.List;

/**
 * DashboardService.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by ktufernando on 16/02/2016.
 */
public interface DashboardService {

    List<TotalClientsByFactorLevelsView> totalLevels();
}
