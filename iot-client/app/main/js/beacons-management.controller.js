angular.module('app.main').controller('beaconsManagementCntl', function ($scope, $modal, beaconDataRestService) {
    'use strict';

//    beaconDataRestService.getBeaconsData().then(function (response) {
//        $scope.test = response;
//    }, function () {
//        alert('something went wrong');
//    });

    $scope.beaconModel = [{
        number: 1,
        name: 'some name 1',
        minor: 189,
        mac: 'D6:90:A8:08:F0:E0',
        uuidNormal: 'normal uuid 1',
        uuidSecure: 'secure uuid 1',
        uuidService: 'service uuid 1',
        building: 'some building number 1',
        floor: 'floor number 1',
        room: 'room number 1',
        xBeacon: 'x1',
        yBeacon: 'y1'
    }, {
        number: 2,
        name: 'some name 2',
        minor: 190,
        mac: 'D6:90:A8:08:F0:E0',
        uuidNormal: 'normal uuid 2',
        uuidSecure: 'secure uuid 2',
        uuidService: 'service uuid 2',
        building: 'some building number 2',
        floor: 'floor number 2',
        room: 'room number 2',
        xBeacon: 'x2',
        yBeacon: 'y2'
    }];

    $scope.mySelectedItems = [];

    $scope.addBeacon = function () {
        var currentBeaconNumber = $scope.beaconModel.length + 1;
        $scope.beaconModel.push({
            number: currentBeaconNumber,
            name: 'some name ' + currentBeaconNumber,
            minor: '19' + currentBeaconNumber,
            mac: 'D6:90:A8:08:F0:E0',
            uuidNormal: 'normal uuid ' + currentBeaconNumber,
            uuidSecure: 'secure uuid ' + currentBeaconNumber,
            uuidService: 'service uuid ' + currentBeaconNumber,
            building: 'some building number ' + currentBeaconNumber,
            floor: 'stock number ' + currentBeaconNumber,
            room: 'room number ' + currentBeaconNumber,
            xBeacon: 'x' + currentBeaconNumber,
            yBeacon: 'y' + currentBeaconNumber
        });
    };


    $scope.deleteBeacon = function () {
        $scope.beaconModel.splice($scope.mySelectedItems[0].number - 1, 1);
    };

    $scope.controlButtonDisabled = function () {
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
            for (var index = 0; index < $scope.beaconModel.length; index = index + 1) {
                if (beacon.number === $scope.beaconModel[index].number) {
                    $scope.beaconModel[index] = beacon;
                }
            }
        });
    };
});