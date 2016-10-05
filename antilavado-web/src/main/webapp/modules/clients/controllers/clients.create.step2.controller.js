/**
 * clients.create.step2.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('ClientsCreateStep2Controller',
    function ($scope, $state, CommonService, $stateParams, growl) {
        if(!$stateParams.client){
            $state.go("antilavado.authenticated.clients");
            return;
        }

        $scope.client = $stateParams.client;
        $scope.stage = "list";

        $scope.domicile = {};

        $scope.active = CommonService.sino;
        $scope.countries = CommonService.countries.query();
        $scope.countrySelected = function(){
            if($scope.domicile.country.id){
                $scope.provinces = CommonService.provinces.query({countryId:$scope.domicile.country.id});
            }else{
                $scope.provinces = [];
            }
        };
        $scope.provinceSelected = function(){
            if($scope.domicile.province.id){
                $scope.districts = CommonService.districts.query({provinceId:$scope.domicile.province.id});
            }else{
                $scope.district = [];
            }
        };
        $scope.districtSelected = function(){
            if($scope.domicile.district.id){
                $scope.localities = CommonService.localities.query({districtId:$scope.domicile.district.id});
            }else{
                $scope.localities = [];
            }
        };


        if(!$scope.client.domiciles){
            $scope.client.domiciles = [];
        }

        $scope.remove = function(index){
            $scope.client.domiciles.splice(index, 1);
        };

        $scope.add = function(){
            $scope.domicile = {};
            $scope.stage = "add";
        };

        $scope.confirmAdd = function(){
            if(this.formScope.addDomicileForm.$valid) {
                $scope.client.domiciles.push($scope.domicile);
                $scope.stage = "list";
            } else {
                this.formScope.addDomicileForm.submitted = true;
            }
        };

        $scope.edit = function(index){
            $scope.editIndex = index;
            $scope.domicile = $.extend(true, {}, $scope.client.domiciles[index]);
            $scope.stage = "edit";
        };

        $scope.confirmEdit = function(index){
            if(this.formScope.editDomicileForm.$valid) {
                $scope.client.domiciles[$scope.editIndex] = $.extend(true, {}, $scope.domicile);
                $scope.stage = "list";
            } else {
                this.formScope.editDomicileForm.submitted = true;
            }
        };

        $scope.list = function(){
            $scope.stage = "list";
        };

        $scope.next = function(){
            if(!$scope.client.domiciles.length){
                growl.error("Es necesario agregar un domicilio");
            }else{
                $state.go("antilavado.authenticated.createclient.stepthree", {client: $scope.client});
            }
        };

        $scope.setFormScope= function(scope){
            this.formScope = scope;
        };

        $scope.previous = function(){
            $state.go("antilavado.authenticated.createclient.stepone", {client: $scope.client});
        };

    }
);