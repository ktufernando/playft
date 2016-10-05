/**
 * license.add.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 29/02/2016.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('LicenseAddController',
	['$scope', 'Licenses','$state', 'Authentication', 'Messages', 'growl', '$timeout',
	function($scope, Licenses, $state, Authentication, Messages, growl, $timeout) {

		$scope.licenseRequest = {};

		$scope.addLicense = function(){
			if($scope.licenseAddForm.$valid) {
				$scope.licenseAddForm.submitDisabled = true;
				Licenses.save($scope.licenseRequest,
					function (response) {
						if (response) {
							growl.success(Messages.OPERATION_OK);
							$timeout(function(){
								$state.go("antilavado.login");
							}, 1000);
						}
					}, function (error) {
						growl.error(error.data.statusMessage);
						$scope.licenseAddForm.submitDisabled = false;
					}
				);
			} else {
				$scope.licenseAddForm.submitted = true;
			}
		};

	}
]);