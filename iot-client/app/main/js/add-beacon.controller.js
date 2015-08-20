angular.module('app.main').controller('addBeaconCntl', function ($scope, $modalInstance) {
    'use strict';

    $scope.newBeacon = {
        number: '',
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