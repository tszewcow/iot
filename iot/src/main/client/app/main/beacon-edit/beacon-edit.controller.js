angular.module('app.main').controller('BeaconEditCntl', function ($scope, $modalInstance, beacon, allBuildings) {
    'use strict';

    $scope.ok = function () {
        if ($scope.beaconEditForm.$valid) {
            $modalInstance.close($scope.data);
        }
    };

    $scope.buildings = [{}];

    $scope.buildings = angular.copy(allBuildings.data);

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

    $scope.data = angular.copy(beacon);

    $scope.getFloors = function () {
        var building = _.findWhere($scope.buildings, {
            buildingNumber: $scope.newAms.building
        });
        return building ? building.floors : undefined;
    };

    $scope.floorSelectionDisabled = function () {
        return !$scope.data.building;
    };
});