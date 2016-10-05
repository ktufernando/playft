/**
 * modal.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $modal service used above.

angular.module(ApplicationConfiguration.applicationModuleName).controller('ModalInstanceController', function ($scope, $modalInstance, type, title, message) {
    $scope.message = message;
    $scope.title = title;
    $scope.type = type;

    $scope.ok = function () {
        $modalInstance.close();
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});