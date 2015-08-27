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
        macAutomaticMobileSet: ''
    };


    $scope.ok = function () {
        if ($scope.amsAddForm.$valid) {
            $modalInstance.close($scope.newAms);
        }
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

});