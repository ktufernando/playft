/**
 * alerts.edit.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 13/01/2016.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('AlertsEditController',
    function ($scope, $state, Alerts, CommonService, BasicUsers, growl, $stateParams, Messages, $timeout, eehNavigation) {

        eehNavigation.menuItem('navbar.alert').countText = null;

        if(!$stateParams.alertId){
            $state.go("^.list");
            return;
        }
        $scope.alertId = $stateParams.alertId;

        $scope.alert = Alerts.get({id: $scope.alertId});

        $scope.unusualOperation = CommonService.statusTypes.query({"type":"ALERT"});
        $scope.users = BasicUsers.query();


        $scope.alertFormSubmit = function () {
            if($scope.alertForm.$valid) {
                $scope.alertForm.submitDisabled = true;

                Alerts.update($scope.alert,
                    function (response) {
                        growl.success(Messages.OPERATION_OK);
                        $timeout(function(){
                            $state.go("^.list");
                        }, 1000);
                    },
                    function(error){
                        growl.error(error.data.statusMessage);
                        $scope.alertForm.submitDisabled = false;
                    }
                );
            } else {
                $scope.alertForm.submitted = true;
            }
        }

        $scope.uifReport = {
            opened: false
        };

        $scope.$watch('alert.assignedUser', function(NewValue, OldValue) {
            if($scope.alert.assignedUser && $scope.alert.unusualOperation.id === 5){
                for(var i in $scope.unusualOperation){
                    if($scope.unusualOperation[i].id === 6){
                        $scope.alert.unusualOperation = $scope.unusualOperation[i];
                    }
                }
            }
        }, true);

    }
);