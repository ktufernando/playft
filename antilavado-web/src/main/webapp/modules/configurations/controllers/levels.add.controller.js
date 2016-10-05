/**
 * levels.add.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 17/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('ConfigurationsLevelsAddController',
    function ($scope, $state, ConfigurationsFactorLevels, CommonService, growl, Messages, $timeout) {

        $scope.active = CommonService.sino;
        $scope.level = {};

        $scope.configurationLevelSubmit = function () {
            if($scope.configurationLevelForm.$valid) {
                $scope.configurationLevelForm.submitDisabled = true;
                $scope.level.active = false;
                $scope.level.percentage = 0;
                ConfigurationsFactorLevels.add($scope.level,
                    function (response) {
                        growl.success(Messages.OPERATION_OK);
                        $timeout(function(){
                            $state.go("antilavado.authenticated.configuration.levels.list");
                        }, 1000);
                    },
                    function(error){
                        growl.error(error.data.statusMessage);
                        $scope.configurationLevelForm.submitDisabled = false;
                    }
                );
            } else {
                $scope.configurationLevelForm.submitted = true;
            }
        }

    }
);