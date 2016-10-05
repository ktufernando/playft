/**
 * generalFiles.add.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 13/01/2016.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('GeneralFilesAddController',
    function ($scope, $state, GeneralFiles, CommonService, ClientsForGeneralFiles, growl, Messages, $timeout) {

        $scope.file = {};
        $scope.clients = [];

        $scope.statusTypes = CommonService.statusTypes.query({"type":"GENERAL_FILE"});

        $scope.updateClients = function(name){
            if(name && name.length > 0) {
                ClientsForGeneralFiles.query({name: name}, function (response) {
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

        $scope.generalFileSubmit = function () {
            if($scope.generalFileForm.$valid) {
                for(var i in $scope.clientsResponse){
                    var o = $scope.clientsResponse[i];
                    if(o.finalName === $scope.clientSelected){
                        $scope.file.client = {id:o.id};
                    }
                };
                if(!$scope.file.client){
                    growl.error("Cliente Invalido: Debe elejir un cliente de la lista que se despliga mientras escribe.");
                    return;
                }
                $scope.generalFileForm.submitDisabled = true;
                $scope.file.id=null;
                GeneralFiles.add($scope.file,
                    function (response) {
                        growl.success(Messages.OPERATION_OK);
                        $timeout(function(){
                            $state.go("antilavado.authenticated.generalFiles.list");
                        }, 1000);
                    },
                    function(error){
                        growl.error(error.data.statusMessage);
                        $scope.generalFileForm.submitDisabled = false;
                    }
                );
            } else {
                $scope.generalFileForm.submitted = true;
            }
        }

    }
);