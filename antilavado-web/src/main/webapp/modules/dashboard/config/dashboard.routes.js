/**
 * dashboard.route.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

// Setting up route
angular.module(ApplicationConfiguration.applicationModuleName).config(['$stateProvider',
    function ($stateProvider) {
        // Redirect to home view when route not found

        $stateProvider
            .state('antilavado.authenticated.dashboard', {
                url: '/',
                templateUrl: 'modules/dashboard/views/dashboard.html',
                controller: 'DashboardController'
            });
    }
]);
