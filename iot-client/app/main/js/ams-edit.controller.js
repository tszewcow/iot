angular.module('app.main').controller('editAmsCntl', function ($scope, $modalInstance, ams) {
    'use strict';

    $scope.ok = function () {
        $modalInstance.close($scope.data);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

    $scope.data = angular.copy(ams);
});