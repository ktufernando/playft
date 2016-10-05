/**
 * alerts.service.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 06/04/2016.
 */

'use strict';

//Articles service used for communicating with the articles REST endpoints
angular.module(ApplicationConfiguration.applicationModuleName)

    .factory('Alerts', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'user/alerts/:id', {}, {
                show: {method: 'GET', params: {id: '@id'}},
                query: {method: 'GET', isArray: true},
                update: {method: 'PUT', params: {id: '@id'}},
                assigned: {method: 'GET', isArray: true, params: {id: 'assigned'}},
                noTreatment: {method: 'GET', isArray: true, params: {id: 'nottreatment'}},
                closed: {method: 'GET', isArray: true, params: {id: 'closed'}},
                suspicious: {method: 'GET', isArray: true, params: {id: 'suspicious'}}
            })
    })

    .factory('AlertCounts', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'user/alerts/count')
    })

    .factory('AlertCounts', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'user/alerts/count')
    })

;

