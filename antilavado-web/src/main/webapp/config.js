/**
 * config.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

// Init the application configuration module for AngularJS application
var ApplicationConfiguration = (function () {
    // Init module configuration options
    var applicationModuleName = 'antilavado';
    var applicationModuleVendorDependencies = ['eehNavigation', 'ngMessages', 'ngAria', 'ngAnimate', 'ngResource', 'ui.bootstrap', 'ui.router', 'ui.utils', 'angular-growl', 'datatables', 'colorpicker.module', 'autocomplete', 'ngSanitize', 'angular.morris-chart', 'ngFileUpload'];

    // Add a new vertical module
    var registerModule = function (moduleName, dependencies) {
        // Create angular module
        angular.module(moduleName, dependencies || []);

        // Add the module to the AngularJS configuration file
        angular.module(applicationModuleName).requires.push(moduleName);
    };

    return {
        applicationModuleName: applicationModuleName,
        applicationModuleVendorDependencies: applicationModuleVendorDependencies,
        registerModule: registerModule
    };
})();
