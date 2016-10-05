/**
 * common.service.js
 *
 * PLAYFT.
 *
 * Copyright (c) 2015 ****Fernando Valdes <fervaldes11@gmail.com>****
 *
 * Created by ktufernando on 10/11/2015.
 */

'use strict';

angular.module(ApplicationConfiguration.applicationModuleName).factory('CommonService', function ($resource, BASE_CONFIG) {
    return {
        banks : $resource(BASE_CONFIG.basePath + 'user/common/banks'),
        accountsTypes : $resource(BASE_CONFIG.basePath + 'user/common/accountsTypes'),
        documentsType : $resource(BASE_CONFIG.basePath + 'user/common/documentsType'),
        taxpayersType : $resource(BASE_CONFIG.basePath + 'user/common/taxpayersType'),
        telephonesType : $resource(BASE_CONFIG.basePath + 'user/common/telephonesType'),
        countries : $resource(BASE_CONFIG.basePath + 'user/common/countries'),
        provinces : $resource(BASE_CONFIG.basePath + 'user/common/:countryId/provinces'),
        districts : $resource(BASE_CONFIG.basePath + 'user/common/:provinceId/districts'),
        localities : $resource(BASE_CONFIG.basePath + 'user/common/:districtId/localities'),
        parentAFIPActivities : $resource(BASE_CONFIG.basePath + 'user/common/parentAFIPActivities'),
        childrenAFIPActivities : $resource(BASE_CONFIG.basePath + 'user/common/childrenAFIPActivities/:parent'),
        marketsBackground : $resource(BASE_CONFIG.basePath + 'user/common/marketsBackground'),
        statusTypes : $resource(BASE_CONFIG.basePath + 'user/common/statusTypes/:type'),
        valuesTypes : $resource(BASE_CONFIG.basePath + 'user/common/valuesTypes/:parentId'),
        /*tableNames : $resource(BASE_CONFIG.basePath + 'user/common/tableNames'),
        columnNames : $resource(BASE_CONFIG.basePath + 'user/common/columnNames/:tableName'),*/
        sino : [{value:true, description:"SI"}, {value:false, description:"NO"}],
        status : [{value:1, description:"ACTIVO"}, {value:0, description:"INACTIVO"}],
        frequencyLabel : $resource(BASE_CONFIG.basePath + 'user/common/frequency/label')
    }
});


