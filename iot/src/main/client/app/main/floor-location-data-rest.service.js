angular.module('app.main').factory('floorLocationRestService', function ($http, currentContextPath) {
    'use strict';

    return {
        getFloorLocations: function () {
            return $http.get(currentContextPath.get() + 'services/locationfloor');
        }
    
    };
});