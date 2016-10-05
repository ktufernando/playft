/**
 * generalFiles.service.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 11/01/2016.
 */

'use strict';

//Articles service used for communicating with the articles REST endpoints
angular.module(ApplicationConfiguration.applicationModuleName)

    .factory('GeneralFiles', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'user/files/general/:id', {}, {
            query: {method: 'GET', isArray: true},
            add: {method: 'POST'},
            update: {method: 'PUT', params: {id: '@id'}}
        })
    })

    .factory('ClientsForGeneralFiles', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'user/files/general/clients/:name')
    })

    .factory('DigitalFiles', function ($resource, BASE_CONFIG) {
        return {
            list : $resource(BASE_CONFIG.basePath + 'user/files/:clientId/list'),
            download : $resource(BASE_CONFIG.basePath + 'user/files/:clientId/:name/download'),
            delete : $resource(BASE_CONFIG.basePath + 'user/files/:clientId/delete')
        }
    })

;

