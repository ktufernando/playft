package ar.com.jf.antilavado.rest.endpoint;

import ar.com.jf.antilavado.repository.model.view.TotalClientsByFactorLevelsView;
import ar.com.jf.antilavado.service.dashboard.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UsersRest.java
 * <p/>
 * PLAYFT.
 * <p/>
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 * <p/>
 * Created by ktufernando on 30/10/2015.
 */
@RestController
@RequestMapping(value = "/user/dashboard")
public class DashboardRest {

    @Autowired
    private DashboardService dashboardService;

    @RequestMapping(value = "/totalLevels", method = RequestMethod.GET)
    @ResponseBody
    public List<TotalClientsByFactorLevelsView> totalLevels() {
        return dashboardService.totalLevels();
    }

}
