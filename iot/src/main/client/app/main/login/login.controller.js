angular.module('app.main').controller('LoginCntl', function ($rootScope, $scope, $modal, $http, $location, userDataRestService) {
    'use strict';

    
//    checkIfUserIsLoggedIn();
    

    $scope.credentials = {};
    $scope.login = function()
    {
    	var headers = $scope.credentials ? {authorization : "Basic "
	        + btoa($scope.credentials.username + ":" + $scope.credentials.password)
	    } : {};
	    	    
	    userDataRestService.getLoggedUserWithLoggingIn({headers : headers}).success(function(data) {
	      
		    if(data.name)
		    {
		        $rootScope.authenticated = true;
			    $rootScope.error = false;
		        $location.path("/main/welcome");
		    }
		    else
		    {
		        $rootScope.authenticated = false;
		        $rootScope.error = true;
		    }
		    resetCredentials();
	    }).error(function() {
	      $rootScope.authenticated = false;
	      $rootScope.error = true;
	      
	      console.log(error);
	      
	      resetCredentials();
	    });
    };
  
    function resetCredentials()
    {
		$scope.credentials.username = null;
		$scope.credentials.password = null;
    }
  

    function checkIfUserIsLoggedIn()
    {
    	if($rootScope.authenticated)
    	{
    		$rootScope.authenticated = true;
    	}
    	else
    	{
    		$http.get('/services/user', {}).success(function(data) {
  		    	
    			if(data.name)
    			{
    				$rootScope.authenticated = true;
    			}
    			else
    			{
    				$rootScope.authenticated = false;
    				$location.path("/");
    			}
    		}).error(function() {
    			$rootScope.authenticated = false;
    			$location.path("/");
    		});
    	}
    }

    $scope.logout = function()
    {
    	console.log("start");
    	
    	$http.get('/services/logout', {}).success(function() {
    		$rootScope.authenticated = false;
			$location.path("/"); 
    	}).error(function(data) {
    		$rootScope.authenticated = false;
			$location.path("/");
    	});  
    }
});