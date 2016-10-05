/**
 * handbooks.service.js
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

    .factory('Handbooks', function ($resource, BASE_CONFIG) {
        return {
            list : $resource(BASE_CONFIG.basePath + 'user/handbook/list'),
            download : $resource(BASE_CONFIG.basePath + 'anonymous/handbook/:name/download'),
            delete : $resource(BASE_CONFIG.basePath + 'admin/handbook/delete')
        }
    })

;

