angular.module('app.main').controller('beaconsManagementCntl', function ($scope, $modal, beaconDataRestService) {
    'use strict';

    $scope.beaconModel = [];

    beaconDataRestService.getBeaconsData().then(function (response) {
        $scope.beaconModel = angular.copy(response.data);
    });

    $scope.mySelectedItems = [];

    $scope.deleteBeacon = function () {
        beaconDataRestService.deleteBeaconData($scope.mySelectedItems[0].id);
        for (var index = 0; index < $scope.beaconModel.length; index = index + 1) {
            if ($scope.mySelectedItems[0].id === $scope.beaconModel[index].id) {
                $scope.beaconModel.splice(index, 1);
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
                for (var index = 0; index < $scope.beaconModel.length; index = index + 1) {
                    if (beacon.id === $scope.beaconModel[index].id) {
                        $scope.beaconModel[index] = beacon;
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
                $scope.beaconModel.push(response.data);
            });
        });
    };
});