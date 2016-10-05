/**
 * core.routes.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

// Setting up route
angular.module(ApplicationConfiguration.applicationModuleName).config(['$stateProvider', '$urlRouterProvider', 'eehNavigationProvider',
    function ($stateProvider, $urlRouterProvider, eehNavigationProvider) {
        // Redirect to home view when route not found
        $urlRouterProvider.otherwise('/');

        $stateProvider
            .state('antilavado', {
                abstract: true,
                templateUrl: 'modules/core/views/app.html'
            })
            .state('antilavado.authenticated', {
                abstract: true,
                templateUrl: 'modules/core/views/authenticated.html',
                controller: 'CoreController'
            });

        eehNavigationProvider.iconBaseClass('fa');

        eehNavigationProvider
            .menuItem('navbar.alert', {
                text: 'Alertas',
                iconClass: 'fa-bell'
            })
            .menuItem('navbar.user', {
                text: 'me',
                iconClass: 'fa-user'
            })
            .menuItem('navbar.user.license', {
                text: 'License',
                iconClass: 'fa-certificate'
            })
            .menuItem('navbar.user.logout', {
                text: 'Salir',
                iconClass: 'fa-sign-out'
            })
        ;

    }
]);
