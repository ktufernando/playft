/**
 * application.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

//Start by defining the main module and adding the module dependencies
angular.module(ApplicationConfiguration.applicationModuleName, ApplicationConfiguration.applicationModuleVendorDependencies);

// Setting HTML5 Location Mode
angular.module(ApplicationConfiguration.applicationModuleName).config(['$locationProvider',
    function ($locationProvider) {
        $locationProvider.hashPrefix('!');
    }
]);

angular.module(ApplicationConfiguration.applicationModuleName).config(['growlProvider', function (growlProvider) {
    growlProvider.globalTimeToLive({success: 4000});
    growlProvider.globalDisableCountDown(true);
    growlProvider.globalPosition('top-center');
}]);

angular.module(ApplicationConfiguration.applicationModuleName).constant('Messages', {
    'INFO': 'Informaci&oacute;n!',
    'WARNING': 'Cuidado!',
    'OPERATION_OK': 'La operaci&oacute;n se realiz&oacute; con &eacute;xito',
    'OPERATION_ERROR': 'Ocurri&oacute; un error al realizar la operaci&oacute;n',
    'USERNAME_PASSWORD_INCORRECT': 'Usuario y/o Contrase&ntilde;a incorrecta',
    'USER_NOT_AUTHORIZED': 'Credenciales invalidas, usuario bloqueado o la licencia esta vencida. Contactese con el Administrador',
    'USER_DELETE_WARNING': 'Se eliminar&aacute; el Usuario seleccionado. &iquest;Est&aacute; seguro?',
    'CLIENT_DELETE_WARNING': 'Se eliminar&aacute; el Cliente seleccionado. &iquest;Est&aacute; seguro?',
    'LEVEL_DELETE_WARNING': 'Se eliminar&aacute; el Nivel seleccionado. &iquest;Est&aacute; seguro?',
    'FACTOR_DELETE_WARNING': 'Se eliminar&aacute; el Factor de Riesgo seleccionado. &iquest;Est&aacute; seguro?',
    'HANDBOOK_DELETE_WARNING': 'Se eliminar&aacute; el Manual seleccionado. &iquest;Est&aacute; seguro?',
    'FILL_ECONOMIC_PROFILE_WARNING': 'Debe completar el perfil econ&oacute;mico antes de avanzar. &iquest;Quiere hacerlo ahora?',
    'DIGITAL_FILE_DELETE_WARNING': 'Se eliminar&aacute; el archivo digital seleccionado. &iquest;Est&aacute; seguro?',
});

angular.module(ApplicationConfiguration.applicationModuleName).constant('BASE_CONFIG', {
    baseHost: '',
    basePath: '/antilavado-rest/'
});

//Then define the init function for starting up the application
angular.element(document).ready(function () {
    //Fixing facebook bug with redirect
    if (window.location.hash === '#_=_') window.location.hash = '#!';

    //Then init the app
    angular.bootstrap(document, [ApplicationConfiguration.applicationModuleName]);

});
angular.module(ApplicationConfiguration.applicationModuleName).run(function (DTDefaultOptions) {
    DTDefaultOptions.setLanguageSource('//cdn.datatables.net/plug-ins/1.10.10/i18n/Spanish.json');
});