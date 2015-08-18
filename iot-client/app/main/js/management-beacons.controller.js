angular.module('app.main').controller('managementBeaconCntl', function ($scope, $modal, beaconDataRestService) {
    'use strict';

    beaconDataRestService.getBeaconsData().then(function (response) {
        $scope.test = response;
    }, function () {
        alert('something went wrong');
    });

    $scope.beaconmodel = [{
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
        var currentBeaconNumber = $scope.beaconmodel.length + 1;
        $scope.beaconmodel.push({
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
    $scope.$watchCollection('mySelectedItems', function () {});

    $scope.deleteBeacon = function () {

        $scope.beaconmodel.splice($scope.mySelectedItems[0].number - 1, 1);
    };

    $scope.deleteButtonDisabled = function () {

        return $scope.mySelectedItems.length === 0;
    };

    $scope.editButtonDisabled = function () {
        return $scope.mySelectedItems.length === 0;
    };

    $scope.editBeacon = function () {

        var modalInstance = $modal.open({
            templateUrl: '/main/html/edit-beacon.html',
            controller: 'editBeaconCntl',
            animation: true,
            resolve: {
                beacon: function () {
                    return $scope.mySelectedItems[0];
                }
            }
        });

        modalInstance.result.then(function (beacon) {
            for (var index = 0; index < $scope.beaconmodel.length; index = index + 1) {
                if (beacon.number === $scope.beaconmodel[index].number) {
                    $scope.beaconmodel[index] = beacon;
                }
            }
        });
    };
});