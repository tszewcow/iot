angular.module('app.main').factory('beaconDataRestService', function ($http, currentContextPath) {
    'use strict';


    return {
        getBeaconsData: function () {
            return $http.get(currentContextPath.get() + 'beacons');
        }
    };

});