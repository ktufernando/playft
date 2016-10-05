package ar.com.jf.antilavado.service.dashboard;

import ar.com.jf.antilavado.repository.interfaces.DashboardRepository;
import ar.com.jf.antilavado.repository.model.view.TotalClientsByFactorLevelsView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * DashboardServiceImpl.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by ktufernando on 16/02/2016.
 */
@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private DashboardRepository dashboardRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TotalClientsByFactorLevelsView> totalLevels() {
        return dashboardRepository.totalLevels();
    }
}
