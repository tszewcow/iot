angular.module('app.main').controller('navigationCtrl', function ($rootScope, $scope, $http, $location, $modal, userDataRestService, globalSpinner) {
    'use strict';
    
    $rootScope.authenticated = false;

    $rootScope.loginResult = {};

    $scope.login = function() {
	  
	  globalSpinner.decorateCallOfFunctionReturningPromise(function () {
	        return userDataRestService.getUser($scope.credentials.username, $scope.credentials.password).then(function (response)
	        {
	        	$rootScope.loginResult = angular.copy(response.data);
	      	  
	        	if($rootScope.loginResult.firstName == "qwertyuiop")
	        	{
	        		  $rootScope.authenticated = true;
	        		  $location.path("/login");
	        	}
	        	else
	        	{
	        		$rootScope.authenticated = false;
	        		$location.path("/login");
	        	}
	        });
	    });
  	};
});