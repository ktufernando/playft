/**
 * clients.create.step3.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('ClientsCreateStep3Controller',
    function ($scope, $state, CommonService, $stateParams, growl) {
        if(!$stateParams.client){
            $state.go("antilavado.authenticated.clients");
            return;
        }

        $scope.stage = "list";
        $scope.client = $stateParams.client;

        $scope.telephone = {};

        $scope.telephonesType = CommonService.telephonesType.query();

        if(!$scope.client.telephones){
            $scope.client.telephones = [];
        }

        $scope.remove = function(index){
            $scope.client.telephones.splice(index, 1);
        };

        $scope.add = function(){
            $scope.telephone = {};
            $scope.stage = "add";
        };

        $scope.confirmAdd = function(){
            if(this.formScope.addTelephoneForm.$valid) {
                $scope.client.telephones.push($scope.telephone);
                $scope.stage = "list";
            } else {
                this.formScope.addTelephoneForm.submitted = true;
            }
        };

        $scope.edit = function(index){
            $scope.editIndex = index;
            $scope.telephone = $.extend(true, {}, $scope.client.telephones[index]);
            $scope.stage = "edit";
        };

        $scope.confirmEdit = function(index){
            if(this.formScope.editTelephoneForm.$valid) {
                $scope.client.telephones[$scope.editIndex] = $.extend(true, {}, $scope.telephone);
                $scope.stage = "list";
            } else {
                this.formScope.editTelephoneForm.submitted = true;
            }
        };

        $scope.list = function(){
            $scope.stage = "list";
        };

        $scope.next = function(){
            if(!$scope.client.telephones.length){
                growl.error("Es necesario agregar un telefono");
            }else{
                $state.go("antilavado.authenticated.createclient.stepfive", {client: $scope.client});
            }
        };

        $scope.setFormScope= function(scope){
            this.formScope = scope;
        };

        $scope.previous = function(){
            $state.go("antilavado.authenticated.createclient.steptwo", {client: $scope.client});
        };

    }
);