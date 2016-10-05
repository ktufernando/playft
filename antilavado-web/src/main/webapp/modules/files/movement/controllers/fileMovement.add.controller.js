/**
 * fileMovement.add.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 14/01/2016.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('FileMovementAddController',
    function ($scope, $state, FileMovement, ClientsForFileMovement, growl, Messages, $timeout) {

        $scope.returnDateMaxDate = new Date();

        $scope.file = {};
        $scope.clients = [];

        $scope.updateClients = function(name){
            if(name && name.length > 0) {
                ClientsForFileMovement.query({name: name}, function (response) {
                    if(response && response.length > 0){
                        $scope.clientsResponse = response;
                        $scope.clients = [];
                        angular.forEach(response, function (o) {
                            $scope.clients.push(o.finalName);
                        });
                    }
                });
            }
        };

        $scope.fileMovementSubmit = function () {
            if($scope.fileMovementForm.$valid) {
                $scope.fileMovementForm.submitDisabled = true;
                for(var i in $scope.clientsResponse){
                    var o = $scope.clientsResponse[i];
                    if(o.finalName === $scope.clientSelected){
                        $scope.file.client = {id:o.id};
                    }
                };
                FileMovement.add($scope.file,
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