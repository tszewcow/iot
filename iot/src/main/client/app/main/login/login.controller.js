angular.module('app.main').controller('navigation', function ($rootScope, $scope, $modal, $http, $location, userDataRestService, globalSpinner) {
    'use strict';


//  authenticate();
  $scope.credentials = {};
  $scope.login = function()
  {
	  var headers = $scope.credentials ? {authorization : "Basic "
	        + btoa($scope.credentials.username + ":" + $scope.credentials.password)
	    } : {};
	    
	    $rootScope.error = true;
	    
	    $http.get('/services/user', {headers : headers}).success(function(data) {
	      
		    if(data.name)
		    {
		        $rootScope.authenticated = true;
			    $rootScope.error = false;
		        $location.path("/");
		    }
		    else
		    {
		        $rootScope.authenticated = false;
		    }
	    }).error(function() {
	      $rootScope.authenticated = false;
	    });
  };
});
