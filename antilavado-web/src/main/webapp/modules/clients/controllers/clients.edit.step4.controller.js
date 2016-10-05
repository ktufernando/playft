/**
 * clients.edit.step4.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('ClientsEditStep4Controller',
    function ($scope, $state, CommonService, Clients, $stateParams, growl) {
        if(!$stateParams.clientId || !$stateParams.client){
            $state.go("antilavado.authenticated.clients");
            return;
        }

        $scope.clientId = $stateParams.clientId;
        $scope.client = $stateParams.client;

        CommonService.frequencyLabel.get(function(response){
            $scope.frequencyLabel = response.result;
        });

        $scope.fs = CommonService.valuesTypes.query({parentId:5});
        $scope.mc = CommonService.valuesTypes.query({parentId:9});
        $scope.mid = CommonService.valuesTypes.query({parentId:13});
        $scope.mieecc = CommonService.valuesTypes.query({parentId:18});
        $scope.middjj = CommonService.valuesTypes.query({parentId:23});
        $scope.middjj2 = CommonService.valuesTypes.query({parentId:33});

        $scope.next = function(){
            if($scope.economicProfileForm.$valid) {
                $scope.client.economicFinancialProfile.validated = true;
                $state.go("antilavado.authenticated.editclient.stepone", {clientId: $scope.clientId, client: $scope.client});
            }else {
                $scope.economicProfileForm.submitted = true;
            }
        };

        $scope.previous = function(){
            $state.go("antilavado.authenticated.editclient.stepone", {clientId: $scope.clientId, client: $scope.client});
        };

    }
);