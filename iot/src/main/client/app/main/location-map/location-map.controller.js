angular.module('app.main').controller('LocationMapCntl', function ($scope, $modal, amsDataRestService, $interval) {
    'use strict';

    $scope.locationModel = [];

    // call rest for coordinates in 5s interval
    $interval(function () {
        // call rest here and on resolve assign response to the $scope
    }, 5000);

    amsDataRestService.getAmsData().then(function (response) {
        angular.forEach(response.data, function (elem) {
            this.push(transformToPoint(elem));
        }, $scope.locationModel);
    });

    function transformToPoint(ams) {
        var point = {};
        point.xPos = Math.floor(translateXCoordToMap(ams.xAutomaticMobileSet));
        point.yPos = Math.floor(translateXCoordToMap(ams.yAutomaticMobileSet));
        point.name = ams.project;
        return point;
    };

    function translateXCoordToMap(x) {
        return x;
    };

    function translateYCoordToMap(y) {
        return y;
    };

});