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
			.state('antilavado.license', {
				url: '/license',
				templateUrl: 'modules/license/views/license.add.html',
				controller: 'LicenseAddController'
			})
			.state('antilavado.authenticated.license', {
				url: '/license/manage',
				templateUrl: 'modules/license/views/license.update.html',
				controller: 'LicenseUpdateController'
			})

		;
	}
]);