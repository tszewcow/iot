angular.module('app.main').controller('locationMapCntl', function ($scope) {
    'use strict';

    $scope.someValue = 'some value';
    $scope.doSomething = function () {
        alert($scope.someValue);
    };

});