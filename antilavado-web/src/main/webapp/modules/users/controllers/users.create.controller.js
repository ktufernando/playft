/**
 * users.create.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('UsersCreateController',
    function ($scope, $state, Users, growl, Messages, $timeout) {
        $scope.user = new Users();

        $scope.addUser = function () {
            if($scope.createUserForm.$valid) {
                $scope.createUserForm.submitDisabled = true;
                $scope.user.userAuthorities = [];
                for(var i in $scope.authorities){
                    $scope.user.userAuthorities.push({name: $scope.authorities[i]})
                }
                $scope.user.$save(
                    function (user) {
                        growl.success(Messages.OPERATION_OK);
                        $timeout(function(){
                            $state.go("antilavado.authenticated.users");
                        }, 1000);
                    },
                    function(error){
                        growl.error(error.data.statusMessage);
                        $scope.createUserForm.submitDisabled = false;
                    }
                );
            } else {
                $scope.createUserForm.submitted = true;
            }
        }
    }
);