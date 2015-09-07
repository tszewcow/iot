angular.module('app.main').controller('AddAmsCntl', function ($scope, $modalInstance, allBuildings) {
    'use strict';

    $scope.buildings = [{}];

    $scope.buildings = angular.copy(allBuildings.data);

    $scope.newAms = {
        id: null,
        project: '',
        guardian: '',
        building: '',
        floor: '',
        room: '',
        xAutomaticMobileSet: '',
        yAutomaticMobileSet: '',
        macAutomaticMobileSet: '',
        ipAutomaticMobileSet: '',
        userAutomaticMobileSet: '',
        passwordAutomaticMobileSet: ''
    };


    $scope.ok = function () {
        if ($scope.amsAddForm.$valid) {
            $modalInstance.close($scope.newAms);
        }
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

    $scope.getFloors = function () {
        var building = _.findWhere($scope.buildings, {
            buildingNumber: $scope.newAms.building
        });
        return building ? building.floors : undefined;
    };

    $scope.floorSelectionDisabled = function () {
        return !$scope.newAms.building;
    };

    $scope.disableGuardianInput = function () {
        if ($scope.newAms.project === '<none>') {
            return true;
        } else {
            return false;
        }
    };

});
