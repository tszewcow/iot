angular.module('app.main').controller('LocationMapCntl', function ($scope, $interval, amsDataRestService, beaconDataRestService, $routeParams) {
    'use strict';

    $scope.locationModel = [];
    $scope.beacons = [];

    var building = $routeParams['building'];
    var floor = $routeParams['floor'];
    $scope.imageUrl = '/main/img/'+building+'_' + floor+ '.png';
    
    var getAmsRest = function () {
        amsDataRestService.getAmsDataOnGivenFloor(building, floor).then(function (response) {
            $scope.locationModel.length = 0;
            angular.forEach(response.data, function (elem) {
                $scope.locationModel.push(transformToPoint(elem));
            });
        });
    };
    
    var intervalPromise = $interval(function () {
        getAmsRest();
    }, 5000);
    getAmsRest();
    $scope.$on('$destroy', function () { $interval.cancel(intervalPromise); });
    
    beaconDataRestService.getBeaconsDataOnGivenFloor(building, floor).then(function (response) {
        angular.forEach(response.data, function (elem) {
            $scope.beacons.push(transformToPointForBeacons(elem));
        });
    });

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

    $scope.showWarning = function () {

        if ($scope.locationModel[0].isActual === true) {
            return false;
        }
        else {
            return true;
        }
    };

});