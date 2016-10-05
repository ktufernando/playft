/**
 * users.directive.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName)

    .directive('pwCheck', function() {
        return {
            require: 'ngModel',
            link: function (scope, elem, attrs, ctrl) {
                var firstPassword = '#' + attrs.pwCheck;
                elem.add(firstPassword).on('keyup', function () {
                    scope.$apply(function () {
                        var v = elem.val()===$(firstPassword).val();
                        ctrl.$setValidity('pwmatch', v);
                    });
                });
            }
        }
    })

    .directive('usernameAvailable', function($timeout, $q, $http, BASE_CONFIG) {
        return {
            restrict: 'AE',
            require: 'ngModel',
            link: function(scope, elm, attr, model) {
                var val = elm.val();
                if(val.length < 4 && val.length > 50){
                    return;
                }
                model.$asyncValidators.usernameExists = function() {
                    return $http.get(BASE_CONFIG.basePath + "admin/users/username/" + elm.val()).then(function(res){
                        $timeout(function(){
                            if(res.data){
                                model.$setValidity('usernameExists', !!res.data.result);
                            }else{
                                model.$setValidity('usernameExists', true);
                            }
                        }, 1000);
                    });
                };
            }
        }
    })

;