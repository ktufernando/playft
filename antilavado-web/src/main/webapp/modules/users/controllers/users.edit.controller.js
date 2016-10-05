/**
 * users.edit.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('UsersEditController',
    function ($scope, $state, $stateParams, Users, growl, Messages, $timeout) {
        if(!$stateParams.userId){
            $state.go("antilavado.authenticated.users");
            return;
        }

        $scope.userId = $stateParams.userId;
        $scope.user = {};
        Users.show({id: $scope.userId}, function(res) {
            $scope.user = res;
            $scope.user.password = null;

            $scope.authorities = [];
            for (var i in $scope.user.userAuthorities) {
                $scope.authorities.push($scope.user.userAuthorities[i].name)
            }
        });

        $scope.editUser = function () {
            if($scope.editUserForm.$valid) {
                $scope.editUserForm.submitDisabled = true;
                $scope.user.userAuthorities = [];
                for(var i in $scope.authorities){
                    $scope.user.userAuthorities.push({name: $scope.authorities[i]})
                }
                $scope.user.$update(
                    function (user) {
                        growl.success(Messages.OPERATION_OK);
                        $timeout(function(){
                            $state.go("antilavado.authenticated.users");
                        }, 1000);
                    },
                    function(error){
                        growl.error(error.data.statusMessage);
                        $scope.editUserForm.submitDisabled = false;
                    }
                );
            } else {
                $scope.editUserForm.submitted = true;
            }
        }
    }
);