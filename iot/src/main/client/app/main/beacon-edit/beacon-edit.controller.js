angular.module('app.main').controller('BeaconEditCntl', function ($scope, $modalInstance, beacon) {
    'use strict';

    $scope.ok = function () {
        if ($scope.beaconEditForm.$valid) {
            $modalInstance.close($scope.data);
        }
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };


    $scope.data = angular.copy(beacon);
});