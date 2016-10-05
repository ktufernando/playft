/**
 * popup.service.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).service('ModalService', function ($modal) {
    this.showModal = function (type, title, message) {
        return $modal.open({
            templateUrl: 'modules/core/views/modal.html',
            controller: 'ModalInstanceController',
            resolve: {
                message: function () {
                    return message;
                },
                title: function () {
                    return title;
                },
                type: function () {
                    return type;
                }
            }
        });
    }
});


