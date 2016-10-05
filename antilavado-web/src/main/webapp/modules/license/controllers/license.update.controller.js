/**
 * license.update.controller.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 29/02/2016.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).controller('LicenseUpdateController',
	['$scope', 'Licenses','$state', 'Authentication', 'Messages', 'growl', '$timeout',
	function($scope, Licenses, $state, Authentication, Messages, growl, $timeout) {

		$scope.license = Licenses.get();
		$scope.licenseRequest = {};

		$scope.updateLicense = function(){
			if($scope.licenseUpdateForm.$valid) {
				$scope.licenseUpdateForm.submitDisabled = true;
				Licenses.save($scope.licenseRequest,
					function (response) {
						if (response) {
							growl.success(Messages.OPERATION_OK);
							$timeout(function(){
								$state.reload();
							}, 1000);
						}
					}, function (error) {
						growl.error(error.data.statusMessage);
						$scope.licenseUpdateForm.submitDisabled = false;
					}
				);
			} else {
				$scope.licenseUpdateForm.submitted = true;
			}
		};

	}
]);