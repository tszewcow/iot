angular.module('app.main').controller('LoginCntl', function ($rootScope, $scope, $modal, $http, $location, userDataRestService) {
    'use strict';

    
    checkIfUserIsLoggedIn(false);
    

    $scope.credentials = {};
    

    $scope.login = function() {
        $http.post('services/login', $.param($scope.credentials), {
          headers : {
            "content-type" : "application/x-www-form-urlencoded"
          }
        }).success(function(data) {
            checkIfUserIsLoggedIn(true);
            console.log("OK");
        }).error(function(data) {
        	checkIfUserIsLoggedIn(true);
//          $scope.error = true;
//          $rootScope.authenticated = false;
//          console.log("error2");
//          console.log(data);
          console.log("error2");
        });
    };
    
  
    function resetCredentials()
    {
		$scope.credentials.username = null;
		$scope.credentials.password = null;
    }
  

    function checkIfUserIsLoggedIn(showError)
    {
    	if($rootScope.authenticated)
    	{
    		authenticateAndRedirect();
    	}
    	else
    	{
    		$http.get('/services/user', {}).success(function(data) {
  		    	
    			if(data.name)
    			{
    				authenticateAndRedirect();
    				
    				$rootScope.loggedUserName = data.name;
    			}
    			else
    			{
    				userNotLoggedIn(showError);
    			}
    		}).error(function() {
    			userNotLoggedIn(showError);
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
			
			
			console.log(data);
    	});  
    }
    
    function authenticateAndRedirect()
    {
		$rootScope.authenticated = true;
		
		if($location.path() == '/main/login')
			$location.path("/main/welcome");
    }
    
    function userNotLoggedIn(showError)
    {
    	$rootScope.authenticated = false;
		
		if(showError)
			$scope.error = true;
    }
});