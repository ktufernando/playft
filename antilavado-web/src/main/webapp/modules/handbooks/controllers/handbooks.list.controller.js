/**
 * handbooks.list.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('HandbooksListController',
        function($scope, $state, Authentication, Handbooks, BASE_CONFIG, growl, ModalService, Messages, $timeout) {


            $scope.isAdmin = Authentication.hasRoles(["ROLE_ADMIN"]);

            $scope.basePath = BASE_CONFIG.basePath;
            Handbooks.list.get(function(r){
                $scope.books = r.result;
            });

            $scope.delete = function(name){
                ModalService.showModal('warning', Messages.WARNING, Messages.HANDBOOK_DELETE_WARNING).result.then(function () {
                    Handbooks.delete.remove({'fileName': name}, function () {
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
