/**
 * master.factor.add.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 19/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('ConfigurationsFactorsAddController',
    function ($scope, $state, ConfigurationsMasterFactors, ConfigurationsDetailFactors, CommonService, growl, Messages, $timeout, $stateParams) {

        $scope.active = CommonService.sino;
        $scope.tables = CommonService.tableNames.query();

        $scope.tableSelected = function(){
            if($scope.factor.mapTable){
                $scope.fields = CommonService.columnNames.query({tableName:$scope.factor.mapTable});
            }else{
                $scope.fields = [];
            }
        };

        $stateParams.factor ? $scope.factor = $stateParams.factor : $scope.factor = {};
        if(!$stateParams.factor){
            $scope.factor.detailRiskFactors = [];
        }

        $scope.configurationMasterFactorSubmit = function () {
            if($scope.configurationMasterFactorForm.$valid) {
                $scope.configurationMasterFactorForm.submitDisabled = true;
                if(!$scope.factor.detailRiskFactors || !$scope.factor.detailRiskFactors.length){
                    growl.error("Debe ingresar al menos un detalle de factor de riesgo.");
                    return;
                }
                ConfigurationsMasterFactors.add($scope.factor,
                    function (response) {
                        growl.success(Messages.OPERATION_OK);
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

        $scope.editDetail = function(index){
            $state.transitionTo("antilavado.authenticated.configuration.factors.detail",{factor:$scope.factor, detailIndex: index, action:'add'});
        };

        $scope.deleteDetail = function(index){
            $scope.factor.detailRiskFactors.splice(index, 1)
        };

        $scope.addDetail = function(){
            $state.transitionTo("antilavado.authenticated.configuration.factors.detail",{factor:$scope.factor, action:'add'});
        };

    }
);