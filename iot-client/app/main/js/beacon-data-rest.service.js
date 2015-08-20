angular.module('app.main').factory('beaconDataRestService', function ($http, currentContextPath) {
    'use strict';

    return {
        getBeaconsData: function () {
            return $http.get(currentContextPath.get() + 'services/beacons');

        },
        addBeaconData: function (data) {
            return $http.post(currentContextPath.get() + 'services/beacon-add', data);
        }
    };
});