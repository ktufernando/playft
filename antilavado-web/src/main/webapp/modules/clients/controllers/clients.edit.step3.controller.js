/**
 * clients.edit.step3.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('ClientsEditStep3Controller',
    function ($scope, $state, CommonService, Clients, Telephones, $stateParams, growl, ModalService, Messages, MatrixService, $timeout) {
        if(!$stateParams.clientId || !$stateParams.client){
            $state.go("antilavado.authenticated.clients");
            return;
        }

        $scope.stage = "list";
        $scope.clientId = $stateParams.clientId;
        $scope.client = $stateParams.client;

        $scope.telephone = {};

        $scope.telephonesType = CommonService.telephonesType.query();

        Telephones.query({id : $scope.clientId}, function(response){
            $scope.client.telephones = response;
        }, function(error){
            $scope.client.telephones = [];
        });

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
                $state.go("antilavado.authenticated.editclient.stepfive", {clientId: $scope.clientId, client: $scope.client});
            }
        };

        $scope.setFormScope= function(scope){
            this.formScope = scope;
        };

        $scope.previous = function(){
            $state.go("antilavado.authenticated.editclient.steptwo", {clientId: $scope.clientId, client: $scope.client});
        };

        $scope.save = function(){
            if(!$scope.client.telephones.length){
                growl.error("Es necesario agregar un telefono");
            }else{
                $scope.submitDisabled = true;

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