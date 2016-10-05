/**
 * clients.list.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('ClientsListController',
        function($scope, $state, Authentication, GridClients, Clients, ModalService, Messages, growl, $timeout) {

            $scope.clients = GridClients.query();

            $scope.delete = function (user) {
                ModalService.showModal('warning', Messages.WARNING, Messages.CLIENT_DELETE_WARNING).result.then(function () {
                    user.$delete(function () {
                        growl.success(Messages.OPERATION_OK);
                        $timeout(function(){
                            $state.reload();
                        }, 1000);

                    }, function () {
                        growl.error(Messages.OPERATION_ERROR);
                    });
                });

            };

            $scope.edit = function(index){
                var client = $scope.clients[index];
                Clients.get({id: client.id}, function(response){
                    $state.go("antilavado.authenticated.editclient.stepone",{clientId:client.id, client: response});
                })

            }

    }
);
