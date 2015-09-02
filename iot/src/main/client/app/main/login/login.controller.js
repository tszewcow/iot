angular.module('app.main').controller('navigation', function ($rootScope, $scope, $http, $location, userDataRestService, globalSpinner) {
    'use strict';
    
    $scope.credentials = {};

      var authenticate = function(credentials, callback) {

        var headers = credentials ? {authorization : "Basic "
            + btoa(credentials.username + ":" + credentials.password)
        } : {};
        
        console.log(headers);
        console.log(" head");
           
        
        userDataRestService.userTest(headers).success(function(data) {
          if (data.name) {
            $rootScope.authenticated = true;
          } else {
            $rootScope.authenticated = false;
          }
          
          console.log(data + "  data");
          
          callback && callback();
        }).error(function() {
          $rootScope.authenticated = false;
          
          console.log('error');
          
          callback && callback();
        });

        console.log("koniec");
      }

      authenticate();
      
      $scope.login = function() {
    	      	  
          authenticate($scope.credentials, function() {
        	  
        	  console.log($scope.credentials);
        	  console.log("credentiols");
        	  
            if ($rootScope.authenticated) {
              $location.path("/DZIALA");
              $scope.error = false;
            } else {
              $scope.error = true;
            }
          });
      };
    });