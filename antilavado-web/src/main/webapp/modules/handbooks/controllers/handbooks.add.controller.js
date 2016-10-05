/**
 * handbooks.add.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 18/02/2016.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('HandbooksAddController',
        function($scope, $state, Authentication, Upload, BASE_CONFIG, growl, Messages, $timeout) {

            if(!Authentication.hasRoles(["ROLE_ADMIN"])){
                $state.go("^.list");
            };

            $scope.handbookFormSubmit = function () {
                if($scope.handbookForm.$valid) {
                    $scope.handbookForm.submitDisabled = true;
                        if ($scope.picFile != null) {
                            Upload.upload({
                                url:BASE_CONFIG.basePath + 'admin/handbook/upload',
                                file: $scope.picFile
                            }).success(function(){
                                growl.success(Messages.OPERATION_OK);
                                $timeout(function(){
                                    $state.go("antilavado.authenticated.handbooks.list");
                                }, 1000);
                            }).error(function(data){
                                growl.error(Messages.OPERATION_ERROR);
                                $scope.handbookForm.submitDisabled = false;
                            });
                        }
                } else {
                    $scope.handbookForm.submitted = true;
                }
            }



    }
);
