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

    $scope.buildings = {
        MTII: [5, 6, 7, 8, 9, 10, 11],
        MTIV: [6, 7, 8, 9, 10, 11, 12]
    };
});