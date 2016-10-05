/**
 * clients.list.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 24/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('MatrixClientsListController',
        function($scope, $state, GridClients) {

            $scope.clients = GridClients.query();

            $scope.matrix = function(index){
                var client = $scope.clients[index];
                $state.go("^.graph",{clientId:client.id});
            }

    }
);
