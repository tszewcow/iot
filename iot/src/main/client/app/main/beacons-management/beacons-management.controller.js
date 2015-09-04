angular.module('app.main').controller('BeaconsManagementCntl', function ($scope, $modal, beaconDataRestService, globalSpinner, floorLocationRestService) {
    'use strict';

    $scope.beacons = [];

    globalSpinner.decorateCallOfFunctionReturningPromise(function () {
        return beaconDataRestService.getBeaconsData().then(function (response) {
            $scope.beacons = angular.copy(response.data);
        });
    });

    $scope.mySelectedItems = [];

    $scope.deleteBeacon = function () {
        globalSpinner.decorateCallOfFunctionReturningPromise(function () {
            var callResult = beaconDataRestService.deleteBeaconData($scope.mySelectedItems[0].id);
            for (var index = 0; index < $scope.beacons.length; index = index + 1) {
                if ($scope.mySelectedItems[0].id === $scope.beacons[index].id) {
                    $scope.beacons.splice(index, 1);
                }
            }
            return callResult;
        });
    };

    $scope.controlButtonDisabled = function () {
        return $scope.mySelectedItems.length === 0;
    };

    $scope.editBeacon = function () {

        var modalInstance = $modal.open({
            templateUrl: '/main/beacon-edit/beacon-edit.tpl.html',
            controller: 'BeaconEditCntl',
            animation: true,
            size: 'lg',
            resolve: {
                beacon: function () {
                    return $scope.mySelectedItems[0];
                },
                allBuildings: function () {
                    return floorLocationRestService.getAllBuildings();
                }
            }
        });

        modalInstance.result.then(function (beacon) {
            globalSpinner.decorateCallOfFunctionReturningPromise(function () {
                return beaconDataRestService.updateBeaconData(beacon).then(function (response) {
                    for (var index = 0; index < $scope.beacons.length; index = index + 1) {
                        if (response.data.id === $scope.beacons[index].id) {
                            $scope.beacons[index] = response.data;
                        }
                    }
                });
            }, beacon);
        });
    };

    $scope.addBeacon = function () {

        var modalInstance = $modal.open({
            templateUrl: '/main/beacon-add/beacon-add.tpl.html',
            controller: 'BeaconAddCntl',
            animation: true,
            size: 'lg',
            resolve: {
                allBuildings: function () {
                    return floorLocationRestService.getAllBuildings();
                }
            }
        });

        modalInstance.result.then(function (beacon) {
            globalSpinner.decorateCallOfFunctionReturningPromise(function () {
                return beaconDataRestService.addBeaconData(beacon).then(function (response) {
                    $scope.beacons.push(response.data);
                });
            }, beacon);
        });
    };
});