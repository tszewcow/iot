angular.module('app.main').factory('beaconDataRestService', function ($http, currentContextPath) {
    'use strict';

    return {
        getBeaconsData: function () {
            return $http.get(currentContextPath.get() + 'services/beacons');

        },
        getBeaconsDataOnGivenFloor: function (building, floor) {
            return $http.get(currentContextPath.get() + 'services/beacons/' + building + '/' + floor);
        },
        addBeaconData: function (data) {
            return $http.post(currentContextPath.get() + 'services/beacon', data);
        },
        deleteBeaconData: function (id) {
            return $http.delete(currentContextPath.get() + 'services/beacon/' + id);
        },
        updateBeaconData: function (data) {
            return $http.put(currentContextPath.get() + 'services/beacon', data);
        }
    };
});