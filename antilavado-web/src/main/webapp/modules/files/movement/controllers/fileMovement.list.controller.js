/**
 * fileMovement.list.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 14/01/2016.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('FileMovementListController',
        function($scope, $state, FileMovement, ModalService, Messages) {

            $scope.files = FileMovement.query();

            $scope.edit = function(file){
                $state.go("^.edit",{file:file});
            }

            $scope.add = function(){
                $state.go("^.add",{file:null});
            }

            $scope.showComment = function(comment){
                ModalService.showModal('info', Messages.INFO, comment);
            }

    }
);
