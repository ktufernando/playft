/**
 * detail.factor.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 19/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('ConfigurationsDetailFactorsEditController',
    function ($scope, $state, CommonService, growl, Messages, $timeout, $stateParams, ConfigurationsDetailFactors, ModalService, MatrixService) {

        if(!$stateParams.factor || !$stateParams.detailIndex){
            $state.go("antilavado.authenticated.configuration.factors.list");
            return;
        }
        $scope.factor = $stateParams.factor;
        $scope.active = CommonService.sino;
        $scope.detail = $scope.factor.detailRiskFactors[$stateParams.detailIndex];

        $scope.configurationDetailFactorSubmit = function () {
            if($scope.configurationDetailFactorForm.$valid) {
                $scope.configurationDetailFactorForm.submitDisabled = true;
                $scope.factor.detailRiskFactors[$stateParams.detailIndex] = $scope.detail
                ConfigurationsDetailFactors.update($scope.detail,
                    function (response) {
                        growl.success('El Impacto se actualiz&oacute; con &eacute;xito');
                        $scope.modalUpdateMatrixs();
                        $timeout(function(){
                            $state.go("antilavado.authenticated.configuration.factors.edit",{factor:$scope.factor});
                        }, 1000);
                    },
                    function(error){
                        growl.error(error.data.statusMessage);
                        $scope.configurationDetailFactorForm.submitDisabled = false;
                    });
            } else {
                $scope.configurationDetailFactorForm.submitted = true;
            }

        };

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

        $scope.back = function(){
            $state.go("antilavado.authenticated.configuration.factors.edit",{factor:$scope.factor});
        };

    }
);