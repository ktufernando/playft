/**
 * levels.list.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 11/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('ConfigurationsLevelsListController',
        function($scope, $state, Authentication, ConfigurationsFactorLevels, ModalService, Messages, growl, $timeout) {

            $scope.levels = ConfigurationsFactorLevels.query();

            $scope.delete = function (level) {
                if(level.active){
                    growl.error('No puede eliminar un Nivel de Riesgo "Activo".<br>Desactive el Nivel y vuelva a intentarlo.');
                    return;
                }
                ModalService.showModal('warning', Messages.WARNING, Messages.LEVEL_DELETE_WARNING).result.then(function () {
                    ConfigurationsFactorLevels.delete({id:level.id}, function () {
                        growl.success(Messages.OPERATION_OK);
                        $timeout(function(){
                            $state.reload();
                        }, 1000);

                    }, function () {
                        growl.error(Messages.OPERATION_ERROR);
                    });
                });

            };

            $scope.edit = function(level){
                $state.go("antilavado.authenticated.configuration.levels.edit",{level:level});
            }

        }
);
