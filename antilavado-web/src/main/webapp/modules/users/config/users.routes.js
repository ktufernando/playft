/**
 * users.route.js
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
			.state('antilavado.login', {
				url: '/login',
				templateUrl: 'modules/users/views/login.html',
				controller: 'LoginController'
			})

			.state('antilavado.authenticated.users', {
				url: '/users',
				templateUrl: 'modules/users/views/users.list.html',
				controller: 'UsersListController'
			})

			.state('antilavado.authenticated.createusers', {
				url: '/users/create',
				templateUrl: 'modules/users/views/users.create.html',
				controller: 'UsersCreateController'
			})

			.state('antilavado.authenticated.editusers', {
				url: '/users/edit/:userId',
				templateUrl: 'modules/users/views/users.edit.html',
				controller: 'UsersEditController'
			})
		;
	}
]);