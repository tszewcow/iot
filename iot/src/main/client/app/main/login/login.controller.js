angular.module('app.main').controller('LoginCntl', function ($rootScope, $scope, $modal, $http, $location, userDataRestService) {
    'use strict';

    
    checkIfUserIsLoggedIn();
    

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
			    $rootScope.loggedUserName = data.name;
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
    		
    		if($location.path() == '/main/login')
    			$location.path("/main/welcome");
    	}
    	else
    	{
    		$http.get('/services/user', {}).success(function(data) {
  		    	
    			if(data.name)
    			{
    				$rootScope.authenticated = true;
    				$rootScope.loggedUserName = data.name;
//    				$location.path("/main/welcome");
    			}
    			else
    			{
    				$rootScope.authenticated = false;
    			}
    		}).error(function() {
    			$rootScope.authenticated = false;
    		});
    	}
    }

    $scope.logout = function()
    {
    	$http.get('/services/logout', {}).success(function() {
    		$rootScope.authenticated = false;
    		$rootScope.loggedUserName = null;
			$location.path("/"); 
    	}).error(function(data) {
    		$rootScope.authenticated = false;
    		$rootScope.loggedUserName = null;
			$location.path("/");
    	});  
    }
});