angular.module('app.main').controller('amsManagementCntl', function ($scope, $modal) {
    'use strict';


    $scope.amsModel = [{
        number: 1,
        project: 'some project 1',
        guardian: 'normal guardian 1',
        building: 'some building number 1',
        stock: 'stock number 1',
        room: 'room number 1',
        coordinates: 'x y 1'
    }, {
        number: 2,
        project: 'some project 2',
        guardian: 'normal guardian 2',
        building: 'some building number 2',
        stock: 'stock number 2',
        room: 'room number 2',
        coordinates: 'x y 2'
    }];


    $scope.addAms = function () {
        var currentAmsNumber = $scope.amsModel.length + 1;
        $scope.amsModel.push({
            number: currentAmsNumber,
            project: 'some project ' + currentAmsNumber,
            guardian: 'some guardian' + currentAmsNumber,
            building: 'some building number ' + currentAmsNumber,
            stock: 'stock number ' + currentAmsNumber,
            room: 'room number ' + currentAmsNumber,
            coordinates: 'x y ' + currentAmsNumber
        });
    };

    $scope.mySelectedItems = [];

    $scope.deleteAms = function () {

        $scope.amsModel.splice($scope.mySelectedItems[0].number - 1, 1);
    };

    $scope.controlButtonDisabled = function () {

        return $scope.mySelectedItems.length === 0;
    };

    $scope.editAms = function () {

        var modalInstance = $modal.open({
            templateUrl: '/main/html/edit-list-of-ams.html',
            controller: 'editlistOfAmsCntl',
            animation: true,
            resolve: {
                ams: function () {
                    return $scope.mySelectedItems[0];
                }
            }
        });

        modalInstance.result.then(function (ams) {
            for (var index = 0; index < $scope.amsModel.length; index = index + 1) {
                if (ams.number === $scope.amsModel[index].number) {
                    $scope.amsModel[index] = ams;
                }
            }
        });
    };
});