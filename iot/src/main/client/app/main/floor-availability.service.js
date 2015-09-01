angular.module('app.main').factory('floorAvailabilityService', function (floorLocationRestService, $q) {
    'use strict';
    var availableFloors = {};
    var init = floorLocationRestService.getFloorLocations().then(function (response) {
        availableFloors = angular.copy(response.data);
    });

    return {
    	init: init,
        checkAvailability: function (building, floor) {
            for (var i = 0; i < availableFloors.length; i++) {
                if (availableFloors[i].floor === floor && availableFloors[i].building === building) {
                    return true;
                }
            }
            return false;
        }
    };
});