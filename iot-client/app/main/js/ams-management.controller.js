angular.module('app.main').controller('amsManagementCntl', function ($scope, $modal, amsDataRestService) {
    'use strict';

    $scope.amsModel = [];

    amsDataRestService.getAmsData().then(function (response) {
        $scope.amsModel = angular.copy(response.data);
    });

    $scope.mySelectedItems = [];

    $scope.deleteAms = function () {
        amsDataRestService.deleteAmsData($scope.mySelectedItems[0].id);
        for (var index = 0; index < $scope.amsModel.length; index = index + 1) {
            if ($scope.mySelectedItems[0].id === $scope.amsModel[index].id) {
                $scope.amsModel.splice(index, 1);
            }
        }
    };

    $scope.controlButtonDisabled = function () {
        return $scope.mySelectedItems.length === 0;
    };

    $scope.editAms = function () {

        var modalInstance = $modal.open({
            templateUrl: '/main/html/ams-edit.html',
            controller: 'editAmsCntl',
            animation: true,
            resolve: {
                ams: function () {
                    return $scope.mySelectedItems[0];
                }
            }
        });

        modalInstance.result.then(function (ams) {
            amsDataRestService.updateAmsData(ams).then(function (response) {
                for (var index = 0; index < $scope.amsModel.length; index = index + 1) {
                    if (ams.id === $scope.amsModel[index].id) {
                        $scope.amsModel[index] = ams;
                    }
                }
            });
        });
    };

    $scope.addAms = function () {

        var modalInstance = $modal.open({
            templateUrl: '/main/html/ams-add.html',
            controller: 'addAmsCntl',
            animation: true
        });

        modalInstance.result.then(function (ams) {
            amsDataRestService.addAmsData(ams).then(function (response) {
                $scope.amsModel.push(response.data);
            });
        });
    };
});