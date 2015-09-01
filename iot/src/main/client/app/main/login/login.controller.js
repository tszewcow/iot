angular.module('app.main').controller('loginController', function($rootScope, $scope, $modal, $http, $location, userDataRestService)
{
	  
	$scope.loggedUser = {};
	
    $scope.initLogin = function () {

        var modalInstance = $modal.open({
            templateUrl: '/main/login/loginDialog.tpl.html',
            animation: true,
            controller: 'LoginDialogCntl',
            size: 'sm'
        });
        
        modalInstance.result.then(function (credentials)
        {
        	loggedUser = userDataRestService.getUser(credentials.username, credentials.password);
        	
        	if(loggedUser.firstName != 'qwertyuiop')
        	{
        		$rootScope.authenticated = true;
        		redirect($rootScope.authenticated);
        	}
        });
    };
    
    $scope.initLogout = function () {
    	$rootScope.authenticated = false;
    	
    	redirect($rootScope.authenticated);
    }
    
    function redirect(value)
    {
    	if (value)
    	{
			$location.path("/main/welcome");
    	}
    	else
    	{
    		$location.path("/main/hello");
    	}
    }
});