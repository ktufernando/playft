/**
 * clients.edit.step1.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('ClientsEditStep1Controller',
    function ($scope, $state, CommonService, Clients, $stateParams, growl, ModalService, Messages, MatrixService, $timeout) {
        if(!$stateParams.clientId || !$stateParams.client){
            $state.go("antilavado.authenticated.clients");
            return;
        }
        $scope.clientId = $stateParams.clientId;
        $scope.client = $stateParams.client;

        $scope.sino = CommonService.sino;
        $scope.specialClient = CommonService.valuesTypes.query({parentId:59});
        $scope.lut = CommonService.valuesTypes.query({parentId:56});
        $scope.ros = CommonService.valuesTypes.query({parentId:52});
        $scope.soi = CommonService.valuesTypes.query({parentId:1});
        $scope.pep = CommonService.valuesTypes.query({parentId:29});
        $scope.societiesType = CommonService.valuesTypes.query({parentId:62});
        $scope.operationFrequency = CommonService.valuesTypes.query({parentId:44});
        $scope.marketExperience = CommonService.valuesTypes.query({parentId:48});
        $scope.documentsType = CommonService.documentsType.query();
        $scope.countries = CommonService.countries.query();
        $scope.activities = {
            degree1List: CommonService.parentAFIPActivities.query()
        };

        $scope.afipActivityDegree1Selected = function(){
            if($scope.activities.degree1){
                $scope.activities["degree2List"] = CommonService.childrenAFIPActivities.query({parent:$scope.activities.degree1});
            }else{
                $scope.activities["degree2List"] = [];
            }
        };
        $scope.afipActivityDegree2Selected = function(){
            if($scope.activities.degree2){
                $scope.activities["degree3List"] = CommonService.childrenAFIPActivities.query({parent:$scope.activities.degree2});
            }else{
                $scope.activities["degree3List"] = [];
            }
        };

        if ($scope.client.afipActivity) {
            $scope.activities.degree1 = $scope.client.afipActivity.substring(0, 1);
            $scope.activities.degree2 = $scope.client.afipActivity.substring(1, 4);
            $scope.activities.degree3 = $scope.client.afipActivity.substring(1);
            $scope.afipActivityDegree1Selected();
            $scope.afipActivityDegree2Selected();
        }

        $scope.birthday = {
            opened: false
        };

        $scope.clientDetailSubmit = function () {
            if($scope.clientDetailForm.$valid) {
                if((!$scope.client.economicFinancialProfile || !$scope.client.economicFinancialProfile.validated) &&
                    ($scope.client.economicFinancialProfile &&
                    (!$scope.client.economicFinancialProfile.monthlyEstimate ||
                    !$scope.client.economicFinancialProfile.fiscalSituation ||
                    !$scope.client.economicFinancialProfile.monthlyIncomeDeclared ||
                    !$scope.client.economicFinancialProfile.monthlyIncomeAsEECC ||
                    !$scope.client.economicFinancialProfile.monthlyIncomeAsDDJJ))){
                    ModalService.showModal('warning', Messages.WARNING, Messages.FILL_ECONOMIC_PROFILE_WARNING).result.then(function () {
                        $scope.economicProfile();
                    });
                    return;
                }
                $scope.client.afipActivity = $scope.activities.degree1 + $scope.activities.degree3;
                $state.go("antilavado.authenticated.editclient.steptwo", {clientId: $scope.clientId, client: $scope.client});
            } else {
                $scope.clientDetailForm.submitted = true;
            }
        };

        $scope.save = function () {
            if($scope.clientDetailForm.$valid) {

                if((!$scope.client.economicFinancialProfile || !$scope.client.economicFinancialProfile.validated) &&
                    ($scope.client.economicFinancialProfile &&
                    (!$scope.client.economicFinancialProfile.monthlyEstimate ||
                    !$scope.client.economicFinancialProfile.fiscalSituation ||
                    !$scope.client.economicFinancialProfile.monthlyIncomeDeclared ||
                    !$scope.client.economicFinancialProfile.monthlyIncomeAsEECC ||
                    !$scope.client.economicFinancialProfile.monthlyIncomeAsDDJJ))){
                    ModalService.showModal('warning', Messages.WARNING, Messages.FILL_ECONOMIC_PROFILE_WARNING).result.then(function () {
                        $scope.economicProfile();
                    });
                    return;
                }

                $scope.submitDisabled = true;

                $scope.client.afipActivity = $scope.activities.degree1 + $scope.activities.degree3;

                Clients.update($scope.client, function (r) {
                        growl.success(Messages.OPERATION_OK);
                        $scope.modalUpdateMatrix();
                        $timeout(function(){
                            $state.go("antilavado.authenticated.clients");
                        },1000);
                    },
                    function(error){
                        growl.error(error.data.statusMessage);
                        $scope.submitDisabled = false;
                    });
            } else {
                $scope.clientDetailForm.submitted = true;
            }
        };

        $scope.economicProfile = function(){
            if($scope.clientDetailForm.$valid) {
                $scope.client.afipActivity = $scope.activities.degree1 + $scope.activities.degree3;
            }
            $state.go("antilavado.authenticated.editclient.stepfour", {client: $scope.client});
        };

        $scope.modalUpdateMatrix = function(){
            ModalService.showModal('warning', Messages.WARNING,
                'Modific&oacute; datos del cliente que pueden cambiar el resultado de la matriz. &iquest;Quiere ejecutar el c&aacute;lculo ahora? <br> <br> <strong>Aclaraci&oacute;n:</strong> Las matrices se calculan autom&aacute;ticamente todos los d&iacute;as.')
                .result.then(function (a) {
                MatrixService.matrixForClient({clientId:$scope.client.id},
                    function(resp){
                        growl.success('Las matriz se calcul&oacute; con &eacute;xito');
                    },
                    function(error){
                        growl.error(error.data.statusMessage);
                    });
            });
        };
    }
);