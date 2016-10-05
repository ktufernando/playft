/**
 * factors.list.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 11/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('ConfigurationsFactorsListController',
        function($scope, $state, Authentication, ConfigurationsMasterFactors, ModalService, Messages, growl, $timeout) {

            $scope.factors = ConfigurationsMasterFactors.query();

            $scope.delete = function (level) {
                ModalService.showModal('warning', Messages.WARNING, Messages.FACTOR_DELETE_WARNING).result.then(function () {
                    ConfigurationsMasterFactors.delete({id:level.id}, function () {
                        growl.success(Messages.OPERATION_OK);
                        $timeout(function(){
                            $state.reload();
                        }, 1000);

                    }, function () {
                        growl.error(Messages.OPERATION_ERROR);
                    });
                });

            };

            $scope.edit = function(factor){
                $state.go("antilavado.authenticated.configuration.factors.edit",{factor:factor});
            }

            $scope.add = function(factor){
                $state.go("antilavado.authenticated.configuration.factors.add",{factor:null});
            }

    }
);
