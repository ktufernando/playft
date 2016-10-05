/**
 * clientsByLevel.list.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 16/02/2016.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('MatrixClientsListByLevelController',
        function($scope, $state, $stateParams, GridClients) {

            if(!$stateParams.level) {
                $state.go("antilavado.authenticated.dashboard");
            }

            $scope.clients = GridClients.queryByLevel({level:$stateParams.level});

            $scope.matrix = function(index){
                var client = $scope.clients[index];
                $state.go("^.graph",{clientId:client.id});
            }

    }
);
