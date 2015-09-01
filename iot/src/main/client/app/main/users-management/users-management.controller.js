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
});