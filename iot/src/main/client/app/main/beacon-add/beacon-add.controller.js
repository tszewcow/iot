angular.module('app.main').controller('BeaconAddCntl', function ($scope, $modalInstance) {
    'use strict';

    $scope.newBeacon = {
        id: null,
        name: '',
        mac: '',
        uuidNormal: '',
        uuidSecure: '',
        uuidService: '',
        building: '',
        floor: '',
        room: '',
        xBeacon: '',
        yBeacon: ''
    };


    $scope.ok = function () {
        if ($scope.beaconAddForm.$valid) {
            $modalInstance.close($scope.newBeacon);
        }
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

    $scope.buildings = {
        MT2: [5, 6, 7, 8, 9, 10, 11],
        MT4: [6, 7, 8, 9, 10, 11, 12]
    };

});