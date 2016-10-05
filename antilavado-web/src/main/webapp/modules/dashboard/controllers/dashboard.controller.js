/**
 * dashboard.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName)
    .controller('DashboardController', function ($scope, $state, TotalLevels, Licenses, AlertCounts) {

        //Numbers of clients by risk level *******************
        $scope.toListClientByLevel = function(level){
            $state.go("antilavado.authenticated.matrix.clientsByLevel",{level:level});
        }

        TotalLevels.query(function(response){
            $scope.totalLevels = response;
            $scope.morrisDonutConfig = {
                data: [],
                colors: []
            };
            angular.forEach($scope.totalLevels, function(v, k){
                $scope.morrisDonutConfig.data.push(
                    {label: v.level, value: v.total}
                );
                $scope.morrisDonutConfig.colors.push(v.color);
            })
        });
        //Numbers of clients by risk level *******************

        //Alerts **********************
        AlertCounts.get(function(response){
            $scope.morrisBarConfig = {
                data: [
                    { y: "Sin Tratamiento", a: response.notTreatment },
                    { y: "Asignadas", a: response.assigned },
                    { y: "Sospechosas", a: response.isSuspicious },
                    { y: "Cerradas", a: response.closed }
                ],
                xkey: 'y',
                ykeys: ['a'],
                labels: ['Alertas'],
                hideHover: 'auto',
                resize: true,
                barColors: function (row, series, type) {
                    if(row.label == "Sin Tratamiento") return "#b93333";
                    else if(row.label == "Asignadas") return "#e6e633";
                    else if(row.label == "Cerradas") return "#33b933";
                    else if(row.label == "Sospechosas") return "#333333";
                }
            };
        });
        //Alerts **********************


        //License **********************
            $scope.license = Licenses.get()
        //License **********************

        /*$scope.morrisAreaConfig = {
            data: [{
                period: '2010 Q1',
                iphone: 2666,
                ipad: null,
                itouch: 2647
            }, {
                period: '2010 Q2',
                iphone: 2778,
                ipad: 2294,
                itouch: 2441
            }, {
                period: '2010 Q3',
                iphone: 4912,
                ipad: 1969,
                itouch: 2501
            }, {
                period: '2010 Q4',
                iphone: 3767,
                ipad: 3597,
                itouch: 5689
            }, {
                period: '2011 Q1',
                iphone: 6810,
                ipad: 1914,
                itouch: 2293
            }, {
                period: '2011 Q2',
                iphone: 5670,
                ipad: 4293,
                itouch: 1881
            }, {
                period: '2011 Q3',
                iphone: 4820,
                ipad: 3795,
                itouch: 1588
            }, {
                period: '2011 Q4',
                iphone: 15073,
                ipad: 5967,
                itouch: 5175
            }, {
                period: '2012 Q1',
                iphone: 10687,
                ipad: 4460,
                itouch: 2028
            }, {
                period: '2012 Q2',
                iphone: 8432,
                ipad: 5713,
                itouch: 1791
            }],
            xkey: 'period',
            ykeys: ['iphone', 'ipad', 'itouch'],
            labels: ['Alto', 'Medio', 'Bajo'],
            pointSize: 2,
            hideHover: 'auto',
            resize: true
        };*/

});
