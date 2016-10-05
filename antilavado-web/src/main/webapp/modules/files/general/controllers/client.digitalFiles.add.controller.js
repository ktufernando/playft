/**
 * client.digitalFiles.add.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 18/02/2016.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('ClientDigitalFilesAddController',
        function($scope, $state, $stateParams, BasicClientBy, Upload, BASE_CONFIG, growl, Messages, $timeout) {

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

            $scope.digitalFileFormSubmit = function () {
                if($scope.digitalFileForm.$valid) {
                    $scope.digitalFileForm.submitDisabled = true;
                        if ($scope.picFile != null) {
                            Upload.upload({
                                url:BASE_CONFIG.basePath + '/user/files/'+ $scope.clientId +'/upload',
                                file: $scope.picFile
                            }).success(function(){
                                growl.success(Messages.OPERATION_OK);
                                $timeout(function(){
                                    $state.go("antilavado.authenticated.generalFiles.clientDigitalFiles.list",{clientId:$scope.clientId});
                                }, 1000);
                            }).error(function(data){
                                growl.error(Messages.OPERATION_ERROR);
                                $scope.digitalFileForm.submitDisabled = false;
                            });
                        }
                } else {
                    $scope.digitalFileForm.submitted = true;
                }
            }



    }
);
