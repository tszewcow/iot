angular.module('app.component1').controller('dialogACntl', function ($scope) {
    'use strict';


    $scope.model = [{
        number: 1,
        name: 'some name 1',
        uuidNormal: 'normal uuid 1',
        uuidSecure: 'secure uuid 1',
        uuidService: 'service uuid 1',
        building: 'some building number 1',
        stock: 'stock number 1',
        room: 'room number 1',
        coordinates: 'x y 1'
    }, {
        number: 2,
        name: 'some name 2',
        uuidNormal: 'normal uuid 2',
        uuidSecure: 'secure uuid 2',
        uuidService: 'service uuid 2',
        building: 'some building number 2',
        stock: 'stock number 2',
        room: 'room number 2',
        coordinates: 'x y 2'
    }];


    $scope.addBeacon = function () {
        var currentBeaconNumber = $scope.model.length + 1;
        $scope.model.push({
            number: currentBeaconNumber,
            name: 'some name ' + currentBeaconNumber,
            uuidNormal: 'normal uuid ' + currentBeaconNumber,
            uuidSecure: 'secure uuid ' + currentBeaconNumber,
            uuidService: 'service uuid ' + currentBeaconNumber,
            building: 'some building number ' + currentBeaconNumber,
            stock: 'stock number ' + currentBeaconNumber,
            room: 'room number ' + currentBeaconNumber,
            coordinates: 'x y ' + currentBeaconNumber
        });
    };

    $scope.mySelectedItems = [];
    $scope.$watchCollection('mySelectedItems', function () {

    });

    $scope.deleteBeacon = function () {
        $scope.model.splice($scope.mySelectedItems[0].number - 1, 1);
    };

    $scope.deleteButtonDisabled = function () {
        return $scope.mySelectedItems.length === 0;
    };
});