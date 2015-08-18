angular.module('app.main').controller('editBeaconCntl', function ($scope, $modalInstance, beacon) {
    'use strict';

    $scope.ok = function () {
        $modalInstance.close($scope.data);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };


    $scope.data = angular.copy(beacon);
});