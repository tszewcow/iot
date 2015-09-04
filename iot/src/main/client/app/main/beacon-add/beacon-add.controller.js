angular.module('app.main').controller('BeaconAddCntl', function ($scope, $modalInstance, allBuildings) {
    'use strict';

    $scope.newBeacon = {
        id: null,
        name: '',
        mac: '',
        uuidNormal: '',
        uuidSecure: '',
        uuidService: '',
        building: '',
        floor: '',
        room: '',
        xBeacon: '',
        yBeacon: ''
    };

    $scope.buildings = [{}];

    $scope.buildings = angular.copy(allBuildings.data);


    $scope.ok = function () {
        if ($scope.beaconAddForm.$valid) {
            $modalInstance.close($scope.newBeacon);
        }
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

    $scope.getFloors = function () {
        var building = _.findWhere($scope.buildings, {
            buildingNumber: $scope.newBeacon.building
        });
        return building ? building.floors : undefined;
    };

    $scope.floorSelectionDisabled = function () {
        return !$scope.newBeacon.building;
    };


});