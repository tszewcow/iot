angular.module('app.main').controller('UserEditCntl', function ($scope, $modalInstance, user) {
    'use strict';

    $scope.newUser = user;

    $scope.$watch("newUser.passwordConfirmation", function() {
        if ($scope.newUser.password == $scope.newUser.passwordConfirmation)
        {
        	$scope.userEditForm.passwordConfirmInput.$setValidity("passwordConfirmInput", true);
        }
        else
        {
        	$scope.userEditForm.passwordConfirmInput.$setValidity("passwordConfirmInput", false);
        }
    });

    $scope.ok = function () {
        if ($scope.userEditForm.$valid) {
            $modalInstance.close($scope.newUser);
        }
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});