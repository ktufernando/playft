/**
 * clients.directive.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName)

    .directive('cuit', function() {
        return {
            restrict: 'AE',
            require: 'ngModel',
            link: function (scope, elem, attrs, ctrl) {
                ctrl.$parsers.unshift(function(value) {
                    var vec = new Array(10);
                    var cuit = value;
                    var esCuit = false;
                    var cuit_rearmado = "";
                    for (var i=0; i < cuit.length; i++){
                        var caracter = cuit.charAt( i);
                        if ( caracter.charCodeAt(0) >= 48 && caracter.charCodeAt(0) <= 57 ) {
                            cuit_rearmado +=caracter;
                        }
                    }
                    cuit = cuit_rearmado;
                    if(cuit.length == 11) {
                        var x = 0;
                        // Multiplico los dígitos.
                        vec[0] = cuit.charAt(  0) * 5;
                        vec[1] = cuit.charAt(  1) * 4;
                        vec[2] = cuit.charAt(  2) * 3;
                        vec[3] = cuit.charAt(  3) * 2;
                        vec[4] = cuit.charAt(  4) * 7;
                        vec[5] = cuit.charAt(  5) * 6;
                        vec[6] = cuit.charAt(  6) * 5;
                        vec[7] = cuit.charAt(  7) * 4;
                        vec[8] = cuit.charAt(  8) * 3;
                        vec[9] = cuit.charAt(  9) * 2;

                        // Suma cada uno de los resultado.
                        for(var i = 0;i<=9; i++){
                            x += vec[i];
                        }
                        var dv = (11 - (x % 11)) % 11;
                        if ( dv == cuit.charAt( 10) ){
                            esCuit=true;
                        }
                    }
                    if ( !esCuit ){
                        ctrl.$setValidity('cuit', false);
                    }else{
                        ctrl.$setValidity('cuit', true);
                    }
                    return value;
                });
            }
        }
    })

;