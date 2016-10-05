/**
 * filesMovement.routes.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 14/01/2016.
 */

'use strict';

// Setting up route
angular.module(ApplicationConfiguration.applicationModuleName).config(['$stateProvider',
	function($stateProvider) {
		$stateProvider
			.state('antilavado.authenticated.fileMovement', {
				url: '/files/movement',
				abstract: true,
				templateUrl: 'modules/files/movement/views/fileMovement.parent.html',
				params: {
					file: null
				}
			})
			.state('antilavado.authenticated.fileMovement.list', {
				url: '/list',
				templateUrl: 'modules/files/movement/views/fileMovement.list.html',
				controller: 'FileMovementListController',
				parent: 'antilavado.authenticated.fileMovement'
			})
			.state('antilavado.authenticated.fileMovement.add', {
				url: '/add',
				templateUrl: 'modules/files/movement/views/fileMovement.add.html',
				controller: 'FileMovementAddController',
				parent: 'antilavado.authenticated.fileMovement'
			})
			.state('antilavado.authenticated.fileMovement.edit', {
				url: '/edit',
				templateUrl: 'modules/files/movement/views/fileMovement.edit.html',
				controller: 'FileMovementEditController',
				parent: 'antilavado.authenticated.fileMovement'
			});


	}
]);