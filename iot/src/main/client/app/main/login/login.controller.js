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
	      
	      resetCredentials();
	    });
  };
  
  function resetCredentials()
  {
	  $scope.credentials.username = null;
      $scope.credentials.password = null;
  }
  
  //zrobic to ladniej
  function checkIfUserIsLoggedIn()
  {
	  if($rootScope.authenticated)
	  {
		  $rootScope.authenticated = true;
		  $location.path("/main/welcome");
	  }
	  else
	  {
		  $http.get('/services/user').success(function(data) {
		      		    	
			  if(data.name)
			  {
				  $rootScope.authenticated = true;
			      $location.path("/main/welcome");
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
});
