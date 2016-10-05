/**
 * license.service.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 29/02/2016.
 */

'use strict';

//Articles service used for communicating with the articles REST endpoints
angular.module(ApplicationConfiguration.applicationModuleName)

    .factory('Licenses', function ($resource, BASE_CONFIG) {
        return $resource(BASE_CONFIG.basePath + 'anonymous/license')
    });

