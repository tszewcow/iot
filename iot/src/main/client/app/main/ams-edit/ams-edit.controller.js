angular.module('app.main').controller('EditAmsCntl', function ($scope, $modalInstance, ams, allBuildings) {
    'use strict';

    $scope.buildings = [{}];

    $scope.buildings = angular.copy(allBuildings.data);

    $scope.ok = function () {
        if ($scope.amsEditForm.$valid) {
            $modalInstance.close($scope.data);
        }
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

    $scope.data = angular.copy(ams);

    $scope.getFloors = function () {
        var building = _.findWhere($scope.buildings, {
            buildingNumber: $scope.data.building
        });
        return building ? building.floors : undefined;
    };

    $scope.floorSelectionDisabled = function () {
        return !$scope.data.building;
    };

    $scope.disableGuardianInput = function () {
        return $scope.data.project === '<none>';
    };
});