angular.module('app.component1').controller('editBeaconCntl', function ($scope, $modalInstance, testData) {
    'use strict';

    $scope.ok = function () {
        $modalInstance.close();
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

    $scope.data = testData;

});