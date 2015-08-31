angular.module('app.main').controller('AmsManagementCntl', function ($scope, $modal, amsDataRestService, floorAvailabilityService, globalSpinner) {
    'use strict';

    $scope.amsModel = [];

    globalSpinner.decorateCallOfFunctionReturningPromise(function () {
        return amsDataRestService.getAmsData().then(function (response) {
            $scope.amsModel = angular.copy(response.data);
        });
    });

    $scope.mySelectedItems = [];

    $scope.deleteAms = function () {
        globalSpinner.decorateCallOfFunctionReturningPromise(function () {
            var callResult = amsDataRestService.deleteAmsData($scope.mySelectedItems[0].id);
            for (var index = 0; index < $scope.amsModel.length; index = index + 1) {
                if ($scope.mySelectedItems[0].id === $scope.amsModel[index].id) {
                    $scope.amsModel.splice(index, 1);
                }
            }
            return callResult;
        });
    };

    $scope.controlButtonDisabled = function () {
        return $scope.mySelectedItems.length === 0;
    };

    $scope.editAms = function () {

        var modalInstance = $modal.open({
            templateUrl: '/main/ams-edit/ams-edit.tpl.html',
            controller: 'EditAmsCntl',
            animation: true,
            size: 'lg',
            resolve: {
                ams: function () {
                    return $scope.mySelectedItems[0];
                }
            }
        });

        modalInstance.result.then(function (ams) {
            globalSpinner.decorateCallOfFunctionReturningPromise(function () {
                return amsDataRestService.updateAmsData(ams).then(function (response) {
                    for (var index = 0; index < $scope.amsModel.length; index = index + 1) {
                        if (response.data.id === $scope.amsModel[index].id) {
                            $scope.amsModel[index] = response.data;
                        }
                    }
                });
            }, ams);
        });
    };

    $scope.addAms = function () {

        var modalInstance = $modal.open({
            templateUrl: '/main/ams-add/ams-add.tpl.html',
            controller: 'AddAmsCntl',
            animation: true,
            size: 'lg'
        });

        modalInstance.result.then(function (ams) {
            globalSpinner.decorateCallOfFunctionReturningPromise(function () {
                return amsDataRestService.addAmsData(ams).then(function (response) {
                    $scope.amsModel.push(response.data);
                });
            }, ams);
        });
    };

    $scope.checkAvailability = function (building, floor) {
        return floorAvailabilityService.checkAvailability(building, floor);
    };

});