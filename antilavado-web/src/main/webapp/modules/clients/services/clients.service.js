/**
 * clients.service.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

//Articles service used for communicating with the articles REST endpoints
angular.module(ApplicationConfiguration.applicationModuleName)

    .factory('Clients', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'user/clients/:id', {}, {
            query: {method: 'GET', isArray: true},
            create: {method: 'POST'},
            show: {method: 'GET', params: {id: '@id'}},
            update: {method: 'PUT', params: {id: '@id'}},
            delete: {method: 'DELETE', params: {id: '@id'}}
        })
    })

    .factory('BasicClientsBy', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'user/clients/by/:name')
    })

    .factory('BasicClientBy', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'user/client/by/:id')
    })

    .factory('GridClients', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'user/clients/grid/:level', {}, {
            query: {method: 'GET', isArray: true},
            queryByLevel: {method: 'GET', isArray: true, params: {level: '@level'}}
        })
    })

    .factory('Telephones', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'user/clients/:id/telephones', {}, {
            query: {method: 'GET', params: {id: '@id'}, isArray: true}
        })
    })

    .factory('Domiciles', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'user/clients/:id/domiciles', {}, {
            query: {method: 'GET', params: {id: '@id'}, isArray: true}
        })
    })

    .factory('BankAccounts', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'user/clients/:id/accounts', {}, {
            query: {method: 'GET', params: {id: '@id'}, isArray: true}
        })
    })

;

