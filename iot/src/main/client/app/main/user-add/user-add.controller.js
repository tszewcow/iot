angular.module('app.main').controller('UserAddCntl', function ($scope, $modalInstance) {
    'use strict';


    $scope.$watch("newUser.passwordConfirmation", function() {
        if ($scope.newUser.password == $scope.newUser.passwordConfirmation)
        {
        	$scope.userAddForm.passwordConfirmInput.$setValidity("passwordConfirmInput", true);
        }
        else
        {
        	$scope.userAddForm.passwordConfirmInput.$setValidity("passwordConfirmInput", false);
        }
      }); 
    
    $scope.newUser = {
        id: null,
        firstName: '',
        lastName: '',
        email: '',
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