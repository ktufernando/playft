/**
 * general.files.list.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 11/01/2016.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('GeneralFilesListController',
        function($scope, $state, GeneralFiles) {

            $scope.files = GeneralFiles.query();

            $scope.edit = function(file){
                $state.go("^.edit",{file:file});
            };

            $scope.add = function(){
                $state.go("^.add",{file:null});
            };

            $scope.digitalFiles = function(clientId){
                $state.go("^.clientDigitalFiles.list",{clientId:clientId});
            };



    }
);
