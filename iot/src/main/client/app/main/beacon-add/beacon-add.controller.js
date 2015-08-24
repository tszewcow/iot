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
        $modalInstance.close($scope.newBeacon);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };

});