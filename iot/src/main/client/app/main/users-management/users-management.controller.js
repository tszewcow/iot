angular.module('app.main').controller('UsersManagementCntl', function ($scope, $modal, userDataRestService, globalSpinner) {
    'use strict';

    $scope.users = [];

    globalSpinner.decorateCallOfFunctionReturningPromise(function () {
        return userDataRestService.getAllUsers().then(function (response) {
            $scope.users = angular.copy(response.data);
        });
    });

    $scope.mySelectedItems = [];


    $scope.addUser = function () {

        var modalInstance = $modal.open({
            templateUrl: '/main/user-add/user-add.tpl.html',
            controller: 'UserAddCntl',
            animation: true,
            size: 'md'
        });

        modalInstance.result.then(function (user) {
            globalSpinner.decorateCallOfFunctionReturningPromise(function () {
                return userDataRestService.addUserData(user).then(function (response) {
                    $scope.users.push(response.data);
                });
            }, user);
        });
    };
    
    $scope.deleteUser = function () {
        globalSpinner.decorateCallOfFunctionReturningPromise(function () {
        	
        	console.log($scope.mySelectedItems.length);
        	console.log($scope.mySelectedItems[0].id);
        	
            var callResult = userDataRestService.deleteUserData($scope.mySelectedItems[0].id);
            
            for (var index = 0; index < $scope.users.length; index = index + 1) {
                if ($scope.mySelectedItems[0].id === $scope.users[index].id) {
                    $scope.users.splice(index, 1);
                }
            }
            return callResult;
        });
    };

    $scope.controlButtonDisabled = function () {
        return $scope.mySelectedItems.length === 0;
    };
    
    
    
    $scope.editUser = function () {

        var modalInstance = $modal.open({
            templateUrl: '/main/user-edit/user-edit.tpl.html',
            controller: 'UserEditCntl',
            animation: true,
            size: 'md',
            resolve: {
                user: function () {
                    return $scope.mySelectedItems[0];
                }
            }
        });

        modalInstance.result.then(function (user) {
            globalSpinner.decorateCallOfFunctionReturningPromise(function () {
                return userDataRestService.updateUserData(user).then(function (response) {
                    for (var index = 0; index < $scope.users.length; index = index + 1) {
                        if (response.data.id === $scope.users[index].id) {
                            $scope.users[index] = response.data;
                        }
                    }
                });
            }, user);
        });
    };
});