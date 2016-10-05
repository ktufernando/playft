/**
 * matrix.service.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 01/12/2015.
 */

'use strict';

//Articles service used for communicating with the articles REST endpoints
angular.module(ApplicationConfiguration.applicationModuleName)

    .factory('MatrixService', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'user/matrix/:clientId', {}, {
            matrixBy: {method: 'GET', params: {clientId: '@clientId'}},
            matrixForAll: {method: 'POST', params: {clientId: 'all'}},
            matrixForClient: {method: 'PUT', params: {clientId: '@clientId'}}
        })
    })

;

