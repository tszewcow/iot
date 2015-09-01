angular.module('app.main').controller('LocationsCntl', function ($scope, floorLocationRestService, globalSpinner) {
    'use strict';

    var buildLink = function (location) {
    	var url = '#/main/location-map?building=' + location.building + '&floor=' + location.floor;
    	var text = location.building + ' floor ' + location.floor;
    	return {url: url, text: text};
    }

    $scope.locationLinks = [];

    globalSpinner.decorateCallOfFunctionReturningPromise(function () {
    	return floorLocationRestService.getFloorLocations().then(function (response) {
            angular.forEach(response.data, function (elem) {
                $scope.locationLinks.push(buildLink(elem));
            });
        });
    });

});