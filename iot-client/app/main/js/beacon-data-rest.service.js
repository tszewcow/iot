angular.module('app.main').factory('beaconDataRestService', function ($http) {
    'use strict';

    var helper;




    return {
        getBeaconsData: function () {
            return $http.get('http://localhost:8888/iot/beacons');
        }
    };

});