/**
 * clients.edit.step5.controllers.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('ClientsEditStep5Controller',
    function ($scope, $state, CommonService, $stateParams, growl, Messages, $timeout,
                Clients, BankAccounts, ModalService, MatrixService) {
        if(!$stateParams.clientId || !$stateParams.client){
            $state.go("antilavado.authenticated.clients");
            return;
        }

        $scope.stage = "list";
        $scope.clientId = $stateParams.clientId;
        $scope.client = $stateParams.client;

        $scope.account = {};

        $scope.active = CommonService.sino;
        $scope.banks = CommonService.banks.query();
        $scope.accountsType = CommonService.accountsTypes.query();

        BankAccounts.query({id : $scope.clientId}, function(response){
            $scope.client.bankAccounts = response;
        }, function(error){
            $scope.client.bankAccounts = [];
        });

        $scope.add = function(){
            $scope.account = {};
            $scope.stage = "add";
        };

        $scope.confirmAdd = function(){
            if(this.formScope.addAccountForm.$valid) {
                $scope.client.bankAccounts.push($scope.account);
                $scope.stage = "list";
            } else {
                this.formScope.addAccountForm.submitted = true;
            }
        };

        $scope.edit = function(index){
            $scope.editIndex = index;
            $scope.account = $.extend(true, {}, $scope.client.bankAccounts[index]);
            $scope.stage = "edit";
        };

        $scope.confirmEdit = function(index){
            if(this.formScope.editAccountForm.$valid) {
                $scope.client.bankAccounts[$scope.editIndex] = $.extend(true, {}, $scope.account);
                $scope.stage = "list";
            } else {
                this.formScope.editAccountForm.submitted = true;
            }
        };

        $scope.list = function(){
            $scope.stage = "list";
        };

        $scope.next = function(){
            if(!$scope.client.bankAccounts.length){
                growl.error("Es necesario agregar una Cuenta Bancaria");
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
                MatrixService.matrixForAll(
                    function(resp){
                        growl.success('Las matriz se calcul&oacute; con &eacute;xito');
                    },
                    function(error){
                        growl.error(error.data.statusMessage);
                    });
            });
        };


        $scope.setFormScope= function(scope){
            this.formScope = scope;
        };

        $scope.previous = function(){
            $state.go("antilavado.authenticated.editclient.stepthree", {clientId: $scope.clientId, client: $scope.client});
        };

    }
);