/**
 * generalFiles.edit.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 13/01/2016.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('GeneralFilesEditController',
    function ($scope, $state, GeneralFiles, CommonService, growl, $stateParams, Messages, $timeout) {
        if(!$stateParams.file){
            $state.go("^.list");
            return;
        }
        $scope.file = $stateParams.file;

        $scope.statusTypes = CommonService.statusTypes.query({"type":"GENERAL_FILE"});

        $scope.finalName = $scope.file.client.individual ? $scope.file.client.names + " " + $scope.file.client.lastNames : $scope.file.client.businessName

        $scope.generalFileSubmit = function () {
            if($scope.generalFileForm.$valid) {
                $scope.generalFileForm.submitDisabled = true;
                GeneralFiles.update($scope.file,
                    function (response) {
                        growl.success(Messages.OPERATION_OK);
                        $timeout(function(){
                            $state.go("^.list");
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