/**
 * handbooks.route.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

// Setting up route
angular.module(ApplicationConfiguration.applicationModuleName).config(['$stateProvider',
	function($stateProvider) {
		$stateProvider
			.state('antilavado.authenticated.handbooks', {
				url: '/handbooks',
				abstract: true,
				templateUrl: 'modules/handbooks/views/handbooks.parent.html'
			})
			.state('antilavado.authenticated.handbooks.list', {
				url: '/list',
				templateUrl: 'modules/handbooks/views/handbooks.list.html',
				controller: 'HandbooksListController'
			})
			.state('antilavado.authenticated.handbooks.add', {
				url: '/add',
				templateUrl: 'modules/handbooks/views/handbooks.add.html',
				controller: 'HandbooksAddController',
				parent: 'antilavado.authenticated.handbooks'
			})


		;
	}
]);