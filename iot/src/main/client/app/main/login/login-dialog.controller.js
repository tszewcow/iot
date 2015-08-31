angular.module('app.main').controller('LoginDialogCntl', function ($scope, $modalInstance) {
    'use strict';

    $scope.credentials = {
        username: 'zzz',
        password: ''
    };


    $scope.authenticate = function () {
        $modalInstance.close($scope.credentials);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});