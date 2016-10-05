/**
 * fileMovement.edit.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 14/01/2016.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('FileMovementEditController',
    function ($scope, $state, FileMovement, growl, $stateParams, Messages, $timeout) {
        if(!$stateParams.file){
            $state.go("^.list");
            return;
        }
        $scope.file = $stateParams.file;

        $scope.returnDateMaxDate = new Date();

        $scope.finalName = $scope.file.client.individual ? $scope.file.client.names + " " + $scope.file.client.lastNames : $scope.file.client.businessName

        $scope.fileMovementSubmit = function () {
            if($scope.fileMovementForm.$valid) {
                $scope.fileMovementForm.submitDisabled = true;
                FileMovement.update($scope.file,
                    function (response) {
                        growl.success(Messages.OPERATION_OK);
                        $timeout(function(){
                            $state.go("^.list");
                        }, 1000);
                    },
                    function(error){
                        growl.error(error.data.statusMessage);
                        $scope.fileMovementForm.submitDisabled = false;
                    }
                );
            } else {
                $scope.fileMovementForm.submitted = true;
            }
        }
    }
);