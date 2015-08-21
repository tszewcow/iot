angular.module('app.main').controller('addAmsCntl', function ($scope, $modalInstance) {
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
        $modalInstance.close($scope.newAms);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

});