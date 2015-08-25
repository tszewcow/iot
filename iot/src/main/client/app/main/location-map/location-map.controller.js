angular.module('app.main').controller('LocationMapCntl', function ($scope, $interval, amsDataRestService, beaconDataRestService) {
    'use strict';

    $scope.locationModel = [];
    $scope.beacons = [];


    $interval(function () {
        getAmsRest();
    }, 1000);

    var getAmsRest = function () {
        amsDataRestService.getAmsData().then(function (response) {
            $scope.locationModel.length = 0;
            angular.forEach(response.data, function (elem) {
                $scope.locationModel.push(transformToPoint(elem));
            });
        });
    };
    
    getAmsRest();
    
    beaconDataRestService.getBeaconsData().then(function (response) {
        angular.forEach(response.data, function (elem) {
            this.push(transformToPointForBeacons(elem));
        }, $scope.beacons);
    });

    var transformToPointForBeacons = function (beacon) {
        var beaconPoint = {};

        beaconPoint.xPos = translateXCoordToMap(beacon.xBeacon);
        beaconPoint.yPos = translateXCoordToMap(beacon.yBeacon);
        beaconPoint.beaconName = beacon.name;
        return beaconPoint;
    }


    var transformToPoint = function (ams) {
        var point = {};

        point.xPos = translateXCoordToMap(ams.xAutomaticMobileSet);
        point.yPos = translateXCoordToMap(ams.yAutomaticMobileSet);
        point.name = ams.project;
        return point;
    };

    var translateXCoordToMap = function (x) {
        return Math.floor(x);
    };

    var translateYCoordToMap = function (y) {
        return Math.floor(y);
    };

    $scope.showWarning = function () {
        return true;
    }

});