/**
 * client.digitalFiles.list.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 13/04/2016.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('ClientDigitalFilesListController',
        function($scope, $state, $stateParams, DigitalFiles, BasicClientBy, BASE_CONFIG, growl, ModalService, Messages, $timeout) {

            if(!$stateParams.clientId){
                $state.go("^.list");
                return;
            }
            $scope.clientId = $stateParams.clientId;
            BasicClientBy.get({id:$scope.clientId},
                function(response){
                    $scope.basicClient = response;
                }, function(error){
                $state.go("antilavado.authenticated.generalFiles.list");
                return;
            });

            $scope.basePath = BASE_CONFIG.basePath;
            DigitalFiles.list.get({clientId: $scope.clientId}, function(r){
                $scope.files = r.result;
            });

            $scope.delete = function(name){
                ModalService.showModal('warning', Messages.WARNING, Messages.DIGITAL_FILE_DELETE_WARNING).result.then(function () {
                    DigitalFiles.delete.remove({'fileName': name, 'clientId': $scope.clientId}, function () {
                        growl.success(Messages.OPERATION_OK);
                        $timeout(function(){
                            $state.reload();
                        }, 1000);

                    }, function () {
                        growl.error(Messages.OPERATION_ERROR);
                    });
                });
            };
    }
);
