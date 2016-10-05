/**
 * clients.routes.js
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
			.state('antilavado.authenticated.clients', {
				url: '/clients',
				templateUrl: 'modules/clients/views/clients.list.html',
				controller: 'ClientsListController'
			})

			.state('antilavado.authenticated.createclient', {
				url: '/clients/create',
				abstract: true,
				templateUrl: 'modules/clients/views/clients.parent.html',
				params: {
					client: null
				}
			})
			.state('antilavado.authenticated.createclient.stepone', {
				url: '/detail',
				templateUrl: 'modules/clients/views/clients.step1.html',
				controller: 'ClientsCreateStep1Controller',
				parent: 'antilavado.authenticated.createclient'
			})
			.state('antilavado.authenticated.createclient.steptwo', {
				url: '/domiciles',
				templateUrl: 'modules/clients/views/clients.step2.html',
				controller: 'ClientsCreateStep2Controller',
				parent: 'antilavado.authenticated.createclient'
			})
			.state('antilavado.authenticated.createclient.stepthree', {
				url: '/telephones',
				templateUrl: 'modules/clients/views/clients.step3.html',
				controller: 'ClientsCreateStep3Controller',
				parent: 'antilavado.authenticated.createclient'
			})
			.state('antilavado.authenticated.createclient.stepfour', {
				url: '/economic-profile',
				templateUrl: 'modules/clients/views/clients.step4.html',
				controller: 'ClientsCreateStep4Controller',
				parent: 'antilavado.authenticated.createclient'
			})
			.state('antilavado.authenticated.createclient.stepfive', {
				url: '/bank-accounts',
				templateUrl: 'modules/clients/views/clients.step5.html',
				controller: 'ClientsCreateStep5Controller',
				parent: 'antilavado.authenticated.createclient'
			})

			.state('antilavado.authenticated.editclient', {
				url: '/clients/edit/:clientId',
				abstract: true,
				templateUrl: 'modules/clients/views/clients.parent.html',
				params: {
					client: null
				}
			})
			.state('antilavado.authenticated.editclient.stepone', {
				url: '/detail',
				templateUrl: 'modules/clients/views/clients.step1.html',
				controller: 'ClientsEditStep1Controller',
				parent: 'antilavado.authenticated.editclient'
			})
			.state('antilavado.authenticated.editclient.steptwo', {
				url: '/domiciles',
				templateUrl: 'modules/clients/views/clients.step2.html',
				controller: 'ClientsEditStep2Controller',
				parent: 'antilavado.authenticated.editclient'
			})
			.state('antilavado.authenticated.editclient.stepthree', {
				url: '/telephones',
				templateUrl: 'modules/clients/views/clients.step3.html',
				controller: 'ClientsEditStep3Controller',
				parent: 'antilavado.authenticated.editclient'
			})
			.state('antilavado.authenticated.editclient.stepfour', {
				url: '/economic-profile',
				templateUrl: 'modules/clients/views/clients.step4.html',
				controller: 'ClientsEditStep4Controller',
				parent: 'antilavado.authenticated.editclient'
			})
			.state('antilavado.authenticated.editclient.stepfive', {
				url: '/bank-accounts',
				templateUrl: 'modules/clients/views/clients.step5.html',
				controller: 'ClientsEditStep5Controller',
				parent: 'antilavado.authenticated.editclient'
			})
		;
	}
]);