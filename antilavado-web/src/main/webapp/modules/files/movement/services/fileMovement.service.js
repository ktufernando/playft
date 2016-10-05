/**
 * fileMovement.service.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 14/01/2016.
 */

'use strict';

//Articles service used for communicating with the articles REST endpoints
angular.module(ApplicationConfiguration.applicationModuleName)

    .factory('FileMovement', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'user/files/movement/:id', {}, {
            query: {method: 'GET', isArray: true},
            add: {method: 'POST'},
            update: {method: 'PUT', params: {id: '@id'}}
        })
    })

    .factory('ClientsForFileMovement', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'user/files/movement/clients/:name')
    })



;

