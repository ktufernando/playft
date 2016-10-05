/**
 * alerts.list.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 06/04/2016.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('AlertsListController',
        function($scope, $state, Alerts, eehNavigation, $modal) {

            eehNavigation.menuItem('navbar.alert').countText = null;

            var current = $state.$current.url.source;
            if(current === '/alerts/list'){
                $scope.alerts = Alerts.query();
            }else if(current === '/alerts/assigned'){
                $scope.alerts = Alerts.assigned();
            }else if(current === '/alerts/closed'){
                $scope.alerts = Alerts.closed();
            }else if(current === '/alerts/notreatment'){
                $scope.alerts = Alerts.noTreatment();
            }else if(current === '/alerts/suspicious'){
                $scope.alerts = Alerts.suspicious();
            }

            $scope.edit = function(alert){
                $state.go("antilavado.authenticated.alerts.edit",{alertId:alert.id});
            };

            $scope.view = function(alert){
                $modal.open({
                    templateUrl: 'modules/alerts/views/alerts.view.modal.html',
                    controller: 'ModalInstanceController',
                    size: 'lg',
                    resolve: {
                        message: function () {
                            return alert;
                        },
                        title: function () {
                            return '';
                        },
                        type: function () {
                            return '';
                        }
                    }
                });
            };

    }
);
