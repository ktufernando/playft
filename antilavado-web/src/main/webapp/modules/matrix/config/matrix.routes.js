/**
 * matrix.routes.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 24/11/2015.
 */

'use strict';

// Setting up route
angular.module(ApplicationConfiguration.applicationModuleName).config(['$stateProvider',
	function($stateProvider) {
		$stateProvider
			.state('antilavado.authenticated.matrix', {
				url: '/matrix',
				abstract: true,
				templateUrl: 'modules/matrix/views/matrix.parent.html'
			})
			.state('antilavado.authenticated.matrix.clients', {
				url: '/clients',
				templateUrl: 'modules/matrix/views/clients.list.html',
				controller: 'MatrixClientsListController',
				parent: 'antilavado.authenticated.matrix'
			})
			.state('antilavado.authenticated.matrix.clientsByLevel', {
				url: '/clients/:level',
				templateUrl: 'modules/matrix/views/clients.list.html',
				controller: 'MatrixClientsListByLevelController',
				parent: 'antilavado.authenticated.matrix'
			})
			.state('antilavado.authenticated.matrix.graph', {
				url: '/graph/:clientId',
				templateUrl: 'modules/matrix/views/matrix.graph.html',
				controller: 'MatrixGraphController',
				parent: 'antilavado.authenticated.matrix'
			})


		;
	}
]);