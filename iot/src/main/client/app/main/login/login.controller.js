angular.module('app.main').controller('LoginCntl', function ($rootScope, $scope, $modal, $http, $location, userDataRestService, globalSpinner) {
    'use strict';

    
    checkIfUserIsLoggedIn();
    
//  authenticate();
  $scope.credentials = {};
  $scope.login = function()
  {
	  var headers = $scope.credentials ? {authorization : "Basic "
	        + btoa($scope.credentials.username + ":" + $scope.credentials.password)
	    } : {};
	    
	    $http.get('/services/user', {headers : headers}).success(function(data) {
	      
	    	console.log(data);
	    	
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
	    }).error(function() {
	      $rootScope.authenticated = false;
	      $rootScope.error = true;
	    });
  };
  
  
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
		  $rootScope.authenticated = false;
	  }
  }
});
