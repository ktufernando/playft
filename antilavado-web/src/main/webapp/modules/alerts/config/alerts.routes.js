/**
 * alerts.routes.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 06/04/2016.
 */

'use strict';

// Setting up route
angular.module(ApplicationConfiguration.applicationModuleName).config(['$stateProvider',
	function($stateProvider) {
		$stateProvider
			.state('antilavado.authenticated.alerts', {
				url: '/alerts',
				abstract: true,
				templateUrl: 'modules/alerts/views/alerts.parent.html'
			})

			.state('antilavado.authenticated.alerts.list', {
				url: '/list',
				templateUrl: 'modules/alerts/views/alerts.list.html',
				controller: 'AlertsListController',
				parent: 'antilavado.authenticated.alerts'
			})

			.state('antilavado.authenticated.alerts.closed', {
				url: '/closed',
				templateUrl: 'modules/alerts/views/alerts.list.html',
				controller: 'AlertsListController',
				parent: 'antilavado.authenticated.alerts'
			})

			.state('antilavado.authenticated.alerts.assigned', {
				url: '/assigned',
				templateUrl: 'modules/alerts/views/alerts.list.html',
				controller: 'AlertsListController',
				parent: 'antilavado.authenticated.alerts'
			})

			.state('antilavado.authenticated.alerts.notreatment', {
				url: '/notreatment',
				templateUrl: 'modules/alerts/views/alerts.list.html',
				controller: 'AlertsListController',
				parent: 'antilavado.authenticated.alerts'
			})

			.state('antilavado.authenticated.alerts.suspicious', {
				url: '/suspicious',
				templateUrl: 'modules/alerts/views/alerts.list.html',
				controller: 'AlertsListController',
				parent: 'antilavado.authenticated.alerts'
			})

			.state('antilavado.authenticated.alerts.edit', {
				url: '/edit/:alertId',
				templateUrl: 'modules/alerts/views/alerts.edit.html',
				controller: 'AlertsEditController',
				parent: 'antilavado.authenticated.alerts'
			})
		;
	}
]);