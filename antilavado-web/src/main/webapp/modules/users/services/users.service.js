/**
 * users.service.js
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

    .factory('UserService',
    function ($resource, BASE_CONFIG) {
        return {
            login: $resource(BASE_CONFIG.basePath + 'login', {},
                {
                    post: {
                        method: 'POST',
                        headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                    }
                }
            ),
            load: $resource(BASE_CONFIG.basePath + 'user')
        };
    })

    .factory('Users', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'admin/users/:id', {}, {
            query: {method: 'GET', isArray: true},
            create: {method: 'POST'},
            show: {method: 'GET', params: {id: '@id'}},
            update: {method: 'PUT'},
            delete: {method: 'DELETE', params: {id: '@id'}}
        })
    })

    .factory('BasicUsers', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'user/users')
    })
;

