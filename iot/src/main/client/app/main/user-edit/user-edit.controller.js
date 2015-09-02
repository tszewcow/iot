angular.module('app.main').controller('UserEditCntl', function ($scope, $modalInstance, user) {
    'use strict';

    $scope.newUser = user;


    $scope.ok = function () {
        if ($scope.userEditForm.$valid) {
            $modalInstance.close($scope.newUser);
        }
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});