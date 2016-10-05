/**
 * login.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('LoginController',
	['$scope', '$filter', 'UserService','$rootScope', '$location', 'Authentication', 'Messages', 'growl',
	function($scope, $filter, UserService, $rootScope, $location, Authentication, Messages, growl) {

		// If user is signed in then redirect back home
		if (Authentication.getUser()) $location.path('/');

		$scope.login = {};

		$scope.signin = function(){
			if($scope.loginForm.$valid) {
				$scope.loginForm.submitDisabled = true;
				var data = 'username=' + $scope.login.username + '&password=' + $scope.login.password;
				UserService.login.post(data,
					function (authenticationResult) {
						if (authenticationResult) {
							if (authenticationResult && authenticationResult.result) {
								Authentication.setToken(authenticationResult.result);
								UserService.load.get(function (user) {
									Authentication.setUser(user);
									Authentication.redirectToAttemptedUrl();
								});
							}
						}
					}, function (error) {
						growl.error(Messages.USER_NOT_AUTHORIZED);
						$scope.loginForm.submitDisabled = false;
					}
				);
			} else {
				$scope.loginForm.submitted = true;
			}
		};

	}
]);