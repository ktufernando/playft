/**
 * levels.edit.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 17/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('ConfigurationsLevelsEditController',
    function ($scope, $state, ConfigurationsFactorLevels, CommonService, growl, $stateParams,
              Messages, $timeout, ModalService, MatrixService) {
        if(!$stateParams.level){
            $state.go("antilavado.authenticated.configuration.levels.list");
            return;
        }
        $scope.level = $stateParams.level;

        $scope.active = CommonService.sino;

        $scope.configurationLevelSubmit = function () {
            if($scope.configurationLevelForm.$valid) {
                $scope.configurationLevelForm.submitDisabled = true;
                ConfigurationsFactorLevels.update($scope.level,
                    function (response) {
                        growl.success('El Nivel de Riesgo se actualiz&oacute; con &eacute;xito');
                        $scope.modalUpdateMatrixs();
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

        $scope.modalUpdateMatrixs = function(){
            ModalService.showModal('warning', Messages.WARNING,
                'Modific&oacute; configuraciones que pueden cambiar el resultado de las matrices de los clientes. &iquest;Quiere ejecutar el c&aacute;lculo ahora? <br> <br> <strong>Aclaraci&oacute;n:</strong> Las matrices se calculan autom&aacute;ticamente todos los d&iacute;as.')
                .result.then(function (a) {
                MatrixService.matrixForAll(
                    function(resp){
                        growl.success('Las matrices se calcularon con &eacute;xito');
                    },
                    function(error){
                        growl.error(error.data.statusMessage);
                    });
            });
        };
    }
);