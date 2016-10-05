/**
 * core.filter.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName)

    .filter('yesno', function() {
        return function(input) {
            return input ? 'SI' : 'NO';
        };
    })
    .filter('status', function() {
        return function(input) {
            if(input){
                input = input.toString();
            }
            return input && input === '1' ? 'Activo' : 'Inactivo';
        };
    })
    .filter('truncate', function () {
        return function (text, length, end) {
            if(!text)
                return text;

            if (isNaN(length))
                length = 10;

            if (end === undefined)
                end = "...";

            if (text.length <= length || text.length - end.length <= length) {
                return text;
            }
            else {
                return String(text).substring(0, length-end.length) + end;
            }

        };
    })

    .filter('split', function() {
        return function(input, splitChar, splitIndex) {
            // do some bounds checking here to ensure it has that index
            if(!input) return "";
            return input.split(splitChar)[splitIndex];
        }
    })

    .filter('displayname', function() {
        return function(input) {
            // do some bounds checking here to ensure it has that index
            if(!input) return "";
            if(input.clientIsIndividual){
                return input.clientNames + " " + input.clientLastNames;
            }else{
                return input.clientBusinessName;
            }
        }
    })

;