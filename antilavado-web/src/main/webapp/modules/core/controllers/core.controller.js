/**
 * core.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('CoreController', ['$scope', 'Authentication', 'eehNavigation', '$state', 'AlertCounts',
	function($scope, Authentication, eehNavigation, $state, AlertCounts) {

		var user = Authentication.getUser();

		$scope.userLogued = Authentication.isLogged();

		if(!$scope.userLogued && !$state.includes("**.license")){
			$state.go('antilavado.login');
		}

		if(!$state.includes("**.alerts.**")){
			AlertCounts.get(function(response){
				eehNavigation.menuItem('navbar.alert').countText = response.notTreatment
			});
		}

		eehNavigation
			.menuItem('navbar.alert', {
				text: 'Alertas',
				iconClass: 'fa-bell',
				countClass: 'count',
				isVisible : $scope.userLogued,
				click: function () {
					eehNavigation.menuItem('navbar.alert').countText = null;
					$state.go('antilavado.authenticated.alerts.list');
				}
			})
			.menuItem('navbar.user', {
				text: user ? user.completeName : null,
				iconClass: 'fa-user',
				isVisible : $scope.userLogued
			})
			.menuItem('navbar.user.license', {
				text: 'Licencia',
				iconClass: 'fa-certificate',
				isVisible : $scope.userLogued && Authentication.hasRoles(["ROLE_ADMIN"]),
				state: 'antilavado.authenticated.license'
			})
			.menuItem('navbar.user.logout', {
				text: 'Salir',
				iconClass: 'fa-sign-out',
				isVisible : $scope.userLogued,
				click: function () {
					Authentication.deleteData();
					$state.go('antilavado.login');
				}
			})


		;

		eehNavigation
			.menuItem('sidebar.dashboard', {
				text: 'Dashboard',
				iconClass: 'fa-dashboard',
				state: 'antilavado.authenticated.dashboard',
				isVisible: Authentication.hasRoles(["ROLE_USER", "ROLE_ADMIN"])
			})
			.menuItem('sidebar.matrix', {
				text: 'Matriz de Riesgo',
				iconClass: 'fa-bar-chart',
				state: 'antilavado.authenticated.matrix.clients',
				isVisible: Authentication.hasRoles(["ROLE_USER", "ROLE_ADMIN"])
			})
			.menuItem('sidebar.clients', {
				text: 'Gestión de Clientes',
				iconClass: 'fa-users',
				state: 'antilavado.authenticated.clients',
				isVisible: Authentication.hasRoles(["ROLE_USER", "ROLE_ADMIN"])
			})
			.menuItem('sidebar.users', {
				text: 'Gestión de Usuarios',
				iconClass: 'fa-user',
				state: 'antilavado.authenticated.users',
				isVisible: Authentication.hasRoles(["ROLE_ADMIN"])
			})
			.menuItem('sidebar.alerts', {
				text: 'Alertas',
				iconClass: 'fa-bell',
				state: 'antilavado.authenticated.alerts.list',
				isVisible: Authentication.hasRoles(["ROLE_USER"])
			})
			.menuItem('sidebar.handbooks', {
				text: 'Manuales',
				iconClass: 'fa-book',
				state: 'antilavado.authenticated.handbooks.list',
				isVisible: Authentication.hasRoles(["ROLE_USER"])
			})
			.menuItem('sidebar.configurations', {
				text: 'Configuraciones',
				iconClass: 'fa-gear',
				isCollapsed: true,
				isVisible: Authentication.hasRoles(["ROLE_ADMIN"])
			})
			.menuItem('sidebar.configurations.factors', {
				text: 'Factores de Riesgo',
				iconClass: 'fa-list-alt',
				state: 'antilavado.authenticated.configuration.factors.list',
				isVisible: Authentication.hasRoles(["ROLE_ADMIN"])
			})
			.menuItem('sidebar.configurations.levels', {
				text: 'Niveles de Riesgo',
				iconClass: 'fa-line-chart',
				state: 'antilavado.authenticated.configuration.levels.list',
				isVisible: Authentication.hasRoles(["ROLE_ADMIN"])
			})
			.menuItem('sidebar.files', {
				text: 'Archivos/Legajos',
				iconClass: 'fa-archive',
				isCollapsed: true,
				isVisible: Authentication.hasRoles(["ROLE_USER", "ROLE_ADMIN"])
			})
			.menuItem('sidebar.files.general', {
				text: 'Archivos Generales',
				iconClass: 'fa-files-o',
				state: 'antilavado.authenticated.generalFiles.list',
				isVisible: Authentication.hasRoles(["ROLE_USER", "ROLE_ADMIN"])
			})
			.menuItem('sidebar.files.movement', {
				text: 'Movimiento de Legajos',
				iconClass: 'fa-folder-open-o',
				state: 'antilavado.authenticated.fileMovement.list',
				isVisible: Authentication.hasRoles(["ROLE_USER", "ROLE_ADMIN"])
			})
		;

	}
]);
