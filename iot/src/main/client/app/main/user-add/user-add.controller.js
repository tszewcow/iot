angular.module('app.main').controller('UserAddCntl', function ($scope, $modalInstance) {
    'use strict';

    $scope.newUser = {
        id: null,
        firstName: '',
        lastName: '',
        userName: '',
        password: '',
        passwordConfirmation: '',
    	salt: '',
		createdOn: null,
		lastLogin: null,
		active: true
    };


    $scope.ok = function () {
        if ($scope.userAddForm.$valid) {
            $modalInstance.close($scope.newUser);
        }
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});