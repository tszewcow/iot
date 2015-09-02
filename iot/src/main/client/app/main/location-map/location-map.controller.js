angular.module('app.main').controller('LocationMapCntl', function ($scope, $interval, amsDataRestService, beaconDataRestService, floorLocationRestService, globalSpinner, $location) {
    'use strict';

    var building = $location.search().building;
    var floor = $location.search().floor;

    var getAmsRest = function () {
        amsDataRestService.getAmsDataOnGivenFloor(building, floor).then(function (response) {
            $scope.locationModel.length = 0;
            angular.forEach(response.data, function (elem) {
                $scope.locationModel.push(transformToPoint(elem));
            });
        });
    };

    var transformToPointForBeacons = function (beacon) {
        var beaconPoint = {};

        beaconPoint.xPos = translateXCoordToMap(beacon.xBeacon);
        beaconPoint.yPos = translateYCoordToMap(beacon.yBeacon);
        beaconPoint.name = beacon.name;
        return beaconPoint;
    };

    var transformToPoint = function (ams) {
        var point = {};
        point.xPos = translateXCoordToMap(ams.xAutomaticMobileSet);
        point.yPos = translateYCoordToMap(ams.yAutomaticMobileSet);
        point.project = ams.project;
        point.isActual = ams.isActual;
        return point;
    };

    var translateXCoordToMap = function (x) {
        return Math.floor(x);
    };

    var translateYCoordToMap = function (y) {
        return Math.floor(y);
    };

    var buildLink = function (loc) {
        var url = '/main/location-map?building=' + loc.building + '&floor=' + loc.floor;
        var text = loc.building + ' floor ' + loc.floor;
        return {
            url: url,
            text: text
        };
    };

    $scope.locationModel = [];
    $scope.beacons = [];
    $scope.imageUrl = '/main/img/' + building + '_' + floor + '.png';
    $scope.locations = [];
    $scope.newLocation = {};

    $scope.showWarning = function () {
        if ($scope.locationModel[0].isActual === true) {
            return false;
        } else {
            return true;
        }
    };

    $scope.$on('$destroy', function () {
        $interval.cancel(intervalPromise);
    });
    $scope.navigate = function () {
        $location.url($scope.currentLocation.url);
    };



    var intervalPromise = $interval(function () {
        getAmsRest();
    }, 1000);
    getAmsRest();
    beaconDataRestService.getBeaconsDataOnGivenFloor(building, floor).then(function (response) {
        angular.forEach(response.data, function (elem) {
            $scope.beacons.push(transformToPointForBeacons(elem));
        });
    });
    globalSpinner.decorateCallOfFunctionReturningPromise(function () {
        return floorLocationRestService.getFloorLocations().then(function (response) {
            $scope.currentLocation = buildLink({
                building: building,
                floor: floor
            });
            angular.forEach(response.data, function (elem) {
                $scope.locations.push(buildLink(elem));
            });
        });
    });

});