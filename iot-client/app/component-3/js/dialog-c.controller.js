angular.module('app.component3').controller('dialogCCntl', function($scope){
 'use strict';
    
    $scope.someValue = 'some value';
    $scope.doSomething = function(){
      alert($scope.someValue);  
    };

});