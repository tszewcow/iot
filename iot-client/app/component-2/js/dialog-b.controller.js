angular.module('app.component2').controller('dialogBCntl', function($scope){
 'use strict';
    
    $scope.someValue = 'some value';
    $scope.doSomething = function(){
      alert($scope.someValue);  
    };

});