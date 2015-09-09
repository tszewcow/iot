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
        	console.log("error2");
        	console.log(data);
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
    		resetValuesAfterLogout();
    	}).error(function(data) {
    		resetValuesAfterLogout();
			
			console.log(data);
    	});  
    }
    
    function resetValuesAfterLogout()
    {
		$rootScope.authenticated = false;
		$rootScope.loggedUserName = null;
		$location.path("/"); 
    }
    
    function authenticateAndRedirect()
    {
		$rootScope.authenticated = true;
		
		if($location.path() == '/main/login')
			$location.path("/main/welcome");
    }
    
    function userNotLoggedIn(showError)
    {
    	resetCredentials();
    	
    	$rootScope.authenticated = false;
		
		if(showError)
			$scope.error = true;
    }
});