/**
 * generalFiles.routes.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 11/01/2016.
 */

'use strict';

// Setting up route
angular.module(ApplicationConfiguration.applicationModuleName).config(['$stateProvider',
	function($stateProvider) {
		$stateProvider
			.state('antilavado.authenticated.generalFiles', {
				url: '/files/general',
				abstract: true,
				templateUrl: 'modules/files/general/views/generalFiles.parent.html',
				params: {
					file: null
				}
			})
			.state('antilavado.authenticated.generalFiles.list', {
				url: '/list',
				templateUrl: 'modules/files/general/views/generalFiles.list.html',
				controller: 'GeneralFilesListController',
				parent: 'antilavado.authenticated.generalFiles'

			})
			.state('antilavado.authenticated.generalFiles.add', {
				url: '/add',
				templateUrl: 'modules/files/general/views/generalFiles.add.html',
				controller: 'GeneralFilesAddController',
				parent: 'antilavado.authenticated.generalFiles'
			})
			.state('antilavado.authenticated.generalFiles.edit', {
				url: '/edit',
				templateUrl: 'modules/files/general/views/generalFiles.edit.html',
				controller: 'GeneralFilesEditController',
				parent: 'antilavado.authenticated.generalFiles'
			})

			.state('antilavado.authenticated.generalFiles.clientDigitalFiles', {
				url: '/digital',
				abstract: true,
				templateUrl: 'modules/files/general/views/clientDigitalFiles.parent.html'
			})
			.state('antilavado.authenticated.generalFiles.clientDigitalFiles.list', {
				url: '/:clientId/list',
				templateUrl: 'modules/files/general/views/clientDigitalFiles.list.html',
				controller: 'ClientDigitalFilesListController',
				parent: 'antilavado.authenticated.generalFiles.clientDigitalFiles'
			})
			.state('antilavado.authenticated.generalFiles.clientDigitalFiles.add', {
				url: '/:clientId/add',
				templateUrl: 'modules/files/general/views/clientDigitalFiles.add.html',
				controller: 'ClientDigitalFilesAddController',
				parent: 'antilavado.authenticated.generalFiles.clientDigitalFiles'
			})

		;


	}
]);