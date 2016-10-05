/**
 * configurations.service.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 11/11/2015.
 */

'use strict';

//Articles service used for communicating with the articles REST endpoints
angular.module(ApplicationConfiguration.applicationModuleName)

    .factory('ConfigurationsFactorLevels', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'admin/configuration/factorLevels/:id', {}, {
            query: {method: 'GET', isArray: true},
            add: {method: 'POST'},
            update: {method: 'PUT', params: {id: '@id'}},
            delete: {method: 'DELETE', params: {id: '@id'}},
            updateAll: {method: 'PUT'}
        })
    })
    .factory('ConfigurationsMasterFactors', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'admin/configuration/masterFactors/:id', {}, {
            query: {method: 'GET', isArray: true},
            add: {method: 'POST'},
            update: {method: 'PUT'},
            delete: {method: 'DELETE', params: {id: '@id'}}
        })
    })
    .factory('ConfigurationsDetailFactors', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'admin/configuration/detailFactors/:masterFactorId', {}, {
            query: {method: 'GET', params: {masterFactorId: '@masterFactorId'}, isArray: true},
            update: {method: 'PUT'}
        })
    })


;

