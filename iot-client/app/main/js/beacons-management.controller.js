angular.module('app.main').controller('beaconsManagementCntl', function ($scope, $modal, beaconDataRestService) {
    'use strict';

    $scope.beacons = [];

    beaconDataRestService.getBeaconsData().then(function (response) {
        $scope.beacons = angular.copy(response.data);
    });

    $scope.mySelectedItems = [];

    $scope.deleteBeacon = function () {
        beaconDataRestService.deleteBeaconData($scope.mySelectedItems[0].id);
        for (var index = 0; index < $scope.beacons.length; index = index + 1) {
            if ($scope.mySelectedItems[0].id === $scope.beacons[index].id) {
                $scope.beacons.splice(index, 1);
            }
        }
    };

    $scope.controlButtonDisabled = function () {
        return $scope.mySelectedItems.length === 0;
    };

    $scope.editBeacon = function () {

        var modalInstance = $modal.open({
            templateUrl: '/main/html/beacon-edit.html',
            controller: 'editBeaconCntl',
            animation: true,
            resolve: {
                beacon: function () {
                    return $scope.mySelectedItems[0];
                }
            }
        });

        modalInstance.result.then(function (beacon) {
            beaconDataRestService.updateBeaconData(beacon).then(function (response) {
                for (var index = 0; index < $scope.beacons.length; index = index + 1) {
                    if (beacon.id === $scope.beacons[index].id) {
                        $scope.beacons[index] = beacon;
                    }
                }
            });
        });
    };

    $scope.addBeacon = function () {

        var modalInstance = $modal.open({
            templateUrl: '/main/html/beacon-add.html',
            controller: 'addBeaconCntl',
            animation: true
        });

        modalInstance.result.then(function (beacon) {
            beaconDataRestService.addBeaconData(beacon).then(function (response) {
                $scope.beacons.push(response.data);
            });
        });
    };
});