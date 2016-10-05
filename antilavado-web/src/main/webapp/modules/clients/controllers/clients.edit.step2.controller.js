/**
 * clients.edit.step2.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('ClientsEditStep2Controller',
    function ($scope, $state, CommonService, Clients, Domiciles, $stateParams, growl, ModalService, Messages, MatrixService, $timeout) {
        if(!$stateParams.clientId || !$stateParams.client){
            $state.go("antilavado.authenticated.clients");
            return;
        }

        $scope.stage = "list";
        $scope.clientId = $stateParams.clientId;
        $scope.client = $stateParams.client;

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

        Domiciles.query({id : $scope.clientId}, function(response){
            $scope.client.domiciles = response;
        }, function(error){
            $scope.client.domiciles = [];
        });


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
            $scope.stage = "edit";
            $scope.domicile = $.extend(true, {}, $scope.client.domiciles[index]);
            $scope.countrySelected();
            $scope.provinceSelected();
            $scope.districtSelected();
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
                $state.go("antilavado.authenticated.editclient.stepthree", {clientId: $scope.clientId, client: $scope.client});
            }
        };

        $scope.setFormScope= function(scope){
            this.formScope = scope;
        };

        $scope.previous = function(){
            $state.go("antilavado.authenticated.editclient.stepone", {clientId: $scope.clientId, client: $scope.client});
        };

        $scope.save = function(){
            if(!$scope.client.domiciles.length){
                growl.error("Es necesario agregar un domicilio");
            }else{
                Clients.update($scope.client, function (r) {
                        growl.success(Messages.OPERATION_OK);
                        $scope.modalUpdateMatrix();
                        $timeout(function(){
                            $state.go("antilavado.authenticated.clients");
                        },1000);
                    },
                    function(error){
                        growl.error(error.data.statusMessage);
                        $scope.submitDisabled = false;
                    });
            }
        };

        $scope.modalUpdateMatrix = function(){
            ModalService.showModal('warning', Messages.WARNING,
                'Modific&oacute; datos del cliente que pueden cambiar el resultado de la matriz. &iquest;Quiere ejecutar el c&aacute;lculo ahora? <br> <br> <strong>Aclaraci&oacute;n:</strong> Las matrices se calculan autom&aacute;ticamente todos los d&iacute;as.')
                .result.then(function (a) {
                MatrixService.matrixForClient({clientId:$scope.client.id},
                    function(resp){
                        growl.success('Las matriz se calcul&oacute; con &eacute;xito');
                    },
                    function(error){
                        growl.error(error.data.statusMessage);
                    });
            });
        };

    }
);