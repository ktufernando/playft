/**
 * levels.activations.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 11/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('ConfigurationsLevelsActivationsController',
        function($scope, $state, Authentication, CommonService, ConfigurationsFactorLevels,
                 ModalService, Messages, growl, $timeout, MatrixService) {

            $scope.active = CommonService.sino;

            $scope.onChangePercentageOrActive = function(){
                $scope.totalPercentage = 0;
                for(var i in $scope.levels){
                    var value = $scope.levels[i];
                    if(value.active){
                        $scope.totalPercentage = $scope.totalPercentage + (value.percentage?value.percentage:0);
                    }
                    $scope.changeTypeProgressBar();
                }
                /*angular.forEach($scope.levels, function(value, key) {
                    if(value.active){
                        $scope.totalPercentage = $scope.totalPercentage + (value.percentage?value.percentage:0);
                    }
                });*/
            };
            $scope.changeTypeProgressBar = function(){
                if($scope.totalPercentage !== 100){
                    $scope.typeProgress = "danger";
                }else{
                    $scope.typeProgress = "success";
                }
            };

            ConfigurationsFactorLevels.query(function(response){
                if(!response || !response.length){
                    $state.go("antilavado.authenticated.configuration.levels.list");
                    return;
                }
                $scope.levels = response;
                $scope.onChangePercentageOrActive();
            });;

            $scope.configurationLevelActivationsSubmit = function () {
                if($scope.configurationLevelActivationsForm.$valid) {
                    if($scope.totalPercentage !== 100){
                        growl.error("La s&uacute;ma de todos los porcentajes de los niveles activados debe ser 100%.");
                        return;
                    }
                    ConfigurationsFactorLevels.updateAll($scope.levels,
                        function (response) {
                            growl.success('Los porcentajes y activaciones se actualizaron con &eacute;xito');
                            $scope.modalUpdateMatrixs();
                            $timeout(function(){
                                $state.go("antilavado.authenticated.configuration.levels.list");
                            }, 1000);
                        },
                        function(error){
                            growl.error(error.data.statusMessage);
                        }
                    );
                } else {
                    $scope.configurationLevelActivationsForm.submitted = true;
                }
            }

            $scope.modalUpdateMatrixs = function(){
                ModalService.showModal('warning', Messages.WARNING,
                    'Modific&oacute; configuraciones que pueden cambiar el resultado de las matrices de los clientes. &iquest;Quiere ejecutar el c&aacute;lculo ahora? <br> <br> <strong>Aclaraci&oacute;n:</strong> Las matrices se calculan autom&aacute;ticamente todos los d&iacute;as.')
                    .result.then(function (a) {
                    MatrixService.matrixForAll(
                        function(resp){
                            growl.success('Las matrices se calcularon con &eacut;xito');
                        },
                        function(error){
                            growl.error(error.data.statusMessage);
                        });
                });
            };
    }
);
