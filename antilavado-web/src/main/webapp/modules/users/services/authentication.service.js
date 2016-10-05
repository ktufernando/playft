/**
 * authentication.service.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).factory('AuthInterceptor', function ($q, Authentication, $location) {
	return {
		request: function (config) {
			config.headers = config.headers || {};
			if (Authentication.getToken()) {
				config.headers['X-Auth-Token'] = Authentication.getToken();
			}
			return config || $q.when(config);
		},
		response: function (response) {
			if (response.status === 401) {
				Authentication.deleteData();
				$location.path("/login");
			}
			return response || $q.when(response);
		},
		'responseError': function(rejection) {
			var status = rejection.status;
			if (status == 401) {
				Authentication.deleteData();
				$location.path("/login");
			}
			return $q.reject(rejection);
		}
	};
});

angular.module(ApplicationConfiguration.applicationModuleName).config(function ($httpProvider) {
	$httpProvider.interceptors.push('AuthInterceptor');
});

//where we will store the attempted url
angular.module(ApplicationConfiguration.applicationModuleName).value('redirectToUrlAfterLogin', { url: '/' });

angular.module(ApplicationConfiguration.applicationModuleName).factory('Authentication', function ($rootScope, $q, $window, $location, redirectToUrlAfterLogin) {
	return {
		setData: function (data) {
			$window.sessionStorage.token = data.token;
			$window.sessionStorage.user = JSON.stringify(data.user);
			$rootScope.$broadcast('userLoaded');
		},
		deleteData: function(){
			delete $window.sessionStorage.token;
			delete $window.sessionStorage.user;
			$rootScope.$broadcast('userDeleted');
		},
		getUser: function(){
			return $window.sessionStorage.user ? JSON.parse($window.sessionStorage.user) : null;
		},
		setUser: function(user){
			$window.sessionStorage.user = JSON.stringify(user);
			$rootScope.$broadcast('userLoaded');
		},
		setToken: function(token){
			$window.sessionStorage.token = token;
		},
		getToken: function(){
			return $window.sessionStorage.token;
		},
		isLogged: function(){
			return $window.sessionStorage.user ? true : false;
		},
		hasRoles: function (roles) {
			if(this.isLogged()){
				var user = this.getUser();
				for (var roleIndex in roles) {
					for (var userRoleIndex in user.userAuthorities) {
						if (roles[roleIndex] === user.userAuthorities[userRoleIndex].name) {
							return true;
						}
					}
				}
			}
			return false;
		},
		saveAttemptUrl: function() {
			if($location.path().toLowerCase() != '/login') {
				redirectToUrlAfterLogin.url = $location.path();
			}
			else {
				redirectToUrlAfterLogin.url = '/';
			}
		},
		redirectToAttemptedUrl: function() {
			$location.path(redirectToUrlAfterLogin.url);
		}
	};
});

angular.module(ApplicationConfiguration.applicationModuleName).run(function ($location, Authentication) {
	if (!Authentication.isLogged() && $location.path() != '/license') {
		Authentication.saveAttemptUrl();
		$location.path('/login');
	}
});