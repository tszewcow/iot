angular.module('app.main').controller('AddAmsCntl', function ($scope, $modalInstance) {
    'use strict';

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
        ipAutomaticMobileSet: ''
    };


    $scope.ok = function () {
        if ($scope.amsAddForm.$valid) {
            $modalInstance.close($scope.newAms);
        }
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

    $scope.buildings = {
        mt2: [5, 6, 7, 8, 9, 10, 11],
        mt4: [6, 7, 8, 9, 10, 11, 12]
    };

    $scope.disableGuardianInput = function () {
        if ($scope.newAms.project === '<none>') {
            return true;
        } else {
            return false;
        }
    };
});