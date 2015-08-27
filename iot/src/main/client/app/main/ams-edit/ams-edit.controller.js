angular.module('app.main').controller('EditAmsCntl', function ($scope, $modalInstance, ams) {
    'use strict';

    $scope.ok = function () {
        if ($scope.amsEditForm.$valid) {
            $modalInstance.close($scope.data);
        }
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

    $scope.data = angular.copy(ams);
});