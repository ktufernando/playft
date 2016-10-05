/**
 * configurations.routes.js
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
			.state('antilavado.authenticated.configuration', {
				url: '/configurations',
				abstract: true,
				templateUrl: 'modules/configurations/views/configurations.parent.html'
			})

			.state('antilavado.authenticated.configuration.factors', {
				url: '/factors',
				abstract: true,
				templateUrl: 'modules/configurations/views/configurations.factors.html',
				parent: 'antilavado.authenticated.configuration',
				params: {
					factor: null
				}
			})
			.state('antilavado.authenticated.configuration.factors.list', {
				url: '/list',
				templateUrl: 'modules/configurations/views/configurations.factors.list.html',
				controller: 'ConfigurationsFactorsListController',
				parent: 'antilavado.authenticated.configuration.factors'
			})
			.state('antilavado.authenticated.configuration.factors.add', {
				url: '/add',
				templateUrl: 'modules/configurations/views/configurations.factors.add.html',
				controller: 'ConfigurationsFactorsAddController',
				parent: 'antilavado.authenticated.configuration.factors'
			})
			.state('antilavado.authenticated.configuration.factors.edit', {
				url: '/edit',
				templateUrl: 'modules/configurations/views/configurations.factors.edit.html',
				controller: 'ConfigurationsFactorsEditController',
				parent: 'antilavado.authenticated.configuration.factors'
			})

			.state('antilavado.authenticated.configuration.factors.detail', {
				url: '/detail/edit',
				templateUrl: 'modules/configurations/views/configurations.detail.factors.edit.html',
				controller: 'ConfigurationsDetailFactorsEditController',
				parent: 'antilavado.authenticated.configuration.factors',
				params: {
					factor: null,
					detailIndex: null
				}
			})

			.state('antilavado.authenticated.configuration.levels', {
				url: '/levels',
				abstract: true,
				templateUrl: 'modules/configurations/views/configurations.levels.html',
				parent: 'antilavado.authenticated.configuration',
				params: {
					level: null
				}
			})
			.state('antilavado.authenticated.configuration.levels.list', {
				url: '/list',
				templateUrl: 'modules/configurations/views/configurations.levels.list.html',
				controller: 'ConfigurationsLevelsListController',
				parent: 'antilavado.authenticated.configuration.levels'
			})
			.state('antilavado.authenticated.configuration.levels.add', {
				url: '/add',
				templateUrl: 'modules/configurations/views/configurations.levels.add.html',
				controller: 'ConfigurationsLevelsAddController',
				parent: 'antilavado.authenticated.configuration.levels'
			})
			.state('antilavado.authenticated.configuration.levels.edit', {
				url: '/edit',
				templateUrl: 'modules/configurations/views/configurations.levels.edit.html',
				controller: 'ConfigurationsLevelsEditController',
				parent: 'antilavado.authenticated.configuration.levels'
			})
			.state('antilavado.authenticated.configuration.levels.activations', {
				url: '/activations',
				templateUrl: 'modules/configurations/views/configurations.levels.activations.html',
				controller: 'ConfigurationsLevelsActivationsController',
				parent: 'antilavado.authenticated.configuration.levels'
			})
		;
	}
]);