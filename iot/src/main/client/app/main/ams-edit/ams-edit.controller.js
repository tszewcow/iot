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

    $scope.buildings = {
        mt2: [5, 6, 7, 8, 9, 10, 11],
        mt4: [6, 7, 8, 9, 10, 11, 12]
    };

    $scope.disableGuardianInput = function () {
        if ($scope.data.project === '<none>') {
            return $scope.data.guardian = '', true;
        } else {
            return false;
        }
    };
});