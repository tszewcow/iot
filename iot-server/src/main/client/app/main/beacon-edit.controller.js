angular.module('app.main').controller('EditBeaconCntl', function ($scope, $modalInstance, beacon) {
    'use strict';

    $scope.ok = function () {
        $modalInstance.close($scope.data);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };


    $scope.data = angular.copy(beacon);
});