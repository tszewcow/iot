angular.module('app.main').factory('floorAvailabilityService', ['floorLocationRestService', function (floorLocationRestService) {
    'use strict';
    var availableFloors = {};
    floorLocationRestService.getFloorLocations().then(function (response) {
        availableFloors = angular.copy(response.data);
    });

    return {
        checkAvailability: function (building, floor) {
            for (var i = 0; i < availableFloors.length; i++) {
                if (availableFloors[i].floor === floor && availableFloors[i].building === building) {
                    return true;
                }
            }
            return false;
        }

    };
}]);