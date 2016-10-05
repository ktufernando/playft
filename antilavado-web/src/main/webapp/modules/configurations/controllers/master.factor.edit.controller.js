/**
 * master.factor.edit.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 19/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('ConfigurationsFactorsEditController',
    function ($scope, $state, ConfigurationsMasterFactors, ConfigurationsDetailFactors,
              CommonService, growl, Messages, $timeout, $stateParams, ModalService, MatrixService) {

        if(!$stateParams.factor){
            $state.go("antilavado.authenticated.configuration.factors.list");
            return;
        }
        $scope.factor = $stateParams.factor;
        if(!$scope.factor.detailRiskFactors || !$scope.factor.detailRiskFactors.length){
            $scope.factor.detailRiskFactors = ConfigurationsDetailFactors.query({masterFactorId:$scope.factor.id});
        }

        $scope.active = CommonService.sino;

        /*
        $scope.tables = CommonService.tableNames.query();
        $scope.tableSelected = function(){
            if($scope.factor.mapTable){
                $scope.fields = CommonService.columnNames.query({tableName:$scope.factor.mapTable});
            }else{
                $scope.fields = [];
            }
        };
        $scope.tableSelected();
        */

        $scope.configurationMasterFactorSubmit = function () {
            if($scope.configurationMasterFactorForm.$valid) {
                $scope.configurationMasterFactorForm.submitDisabled = true;
                ConfigurationsMasterFactors.update($scope.factor,
                    function (response) {
                        growl.success('El Factor de Riesgo se actualiz&oacute; con &eacute;xito');
                        $scope.modalUpdateMatrixs();
                        $timeout(function(){
                            $state.go("antilavado.authenticated.configuration.factors.list");
                        }, 1000);
                    },
                    function(error){
                        growl.error(error.data.statusMessage);
                        $scope.configurationMasterFactorForm.submitDisabled = false;
                    }
                );
            } else {
                $scope.configurationMasterFactorForm.submitted = true;
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

        $scope.editDetail = function(index){
            $state.transitionTo("antilavado.authenticated.configuration.factors.detail",{factor:$scope.factor, detailIndex: index});
        };

        $scope.deleteDetail = function(index){
            $scope.factor.detailRiskFactors.splice(index, 1)
        };

        /*$scope.addDetail = function(){
            $state.transitionTo("antilavado.authenticated.configuration.factors.detail",{factor:$scope.factor, action:'update'});
        };*/

    }
);