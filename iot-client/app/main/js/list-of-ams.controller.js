angular.module('app.main').controller('listOfAmsCntl', function ($scope, $modal) {
    'use strict';


    $scope.amsmodel = [{
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
        var currentAmsNumber = $scope.amsmodel.length + 1;
        $scope.amsmodel.push({
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
    $scope.$watchCollection('mySelectedItems', function () {});

    $scope.deleteAms = function () {

        $scope.amsmodel.splice($scope.mySelectedItems[0].number - 1, 1);
    };

    $scope.deleteButtonDisabled = function () {

        return $scope.mySelectedItems.length === 0;
    };

    $scope.editButtonDisabled = function () {
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
            for (var index = 0; index < $scope.amsmodel.length; index = index + 1) {
                if (ams.number === $scope.amsmodel[index].number) {
                    $scope.amsmodel[index] = ams;
                }
            }
        });
    };
});