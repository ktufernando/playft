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

angular.module(ApplicationConfiguration.applicationModuleName).controller('MatrixGraphController',
        function($scope, $state, Clients, MatrixService, $stateParams, ModalService, Messages, growl, $timeout) {

            if(!$stateParams.clientId) {
                $state.go("^.clients");
            }
            $scope.client = Clients.show({id:$stateParams.clientId});

            MatrixService.matrixBy({clientId:$stateParams.clientId},
                function(resp){
                    $scope.riskMatrix = resp;
                    $scope.graph(resp.factors);
                },
                function(error){
                    growl.error(error.data.statusMessage);
                    $state.go("^.clients");
                });

            $scope.graph = function(list){
                var labels = [];
                var data = [];
                angular.forEach(list, function(e){
                    labels.push(e.factor);
                    data.push(e.ponderation);
                })
                var lineChartData = {
                    labels: labels,
                    datasets: [
                        {
                            label: "Matriz de Riesgo",
                            fillColor: "rgba(220,220,220,0.2)",
                            strokeColor: "rgba(220,220,220,1)",
                            pointColor: "rgba(220,220,220,1)",
                            pointStrokeColor: "#fff",
                            pointHighlightFill: "#fff",
                            pointHighlightStroke: "rgba(220,220,220,1)",
                            data: data
                        }
                    ]
                };
                var ctx = document.getElementById("riskFactorLineGraph").getContext("2d");
                window.myLine = new Chart(ctx).Line(lineChartData, {
                    responsive: true,
                    tooltipXPadding: 40
                })
            };

            $scope.previous = function(){
                $state.go("^.clients");
            }

    }
);
