/**
 * users.list.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('UsersListController',
    ['$scope', '$state', 'Authentication', 'Users', 'ModalService', 'Messages', 'growl', '$timeout', 'Licenses',
        function($scope, $state, Authentication, Users, ModalService, Messages, growl, $timeout, Licenses) {

            $scope.users = Users.query();
            $scope.license = Licenses.get();

            $scope.delete = function (user) {
                ModalService.showModal('warning', Messages.WARNING, Messages.USER_DELETE_WARNING).result.then(function () {
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

    }
]);
