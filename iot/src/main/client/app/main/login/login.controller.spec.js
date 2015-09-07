describe('LoginCntl', function() {
	beforeEach(module('app.main'));

  var $controller;

  beforeEach(inject(function(_$controller_, userDataRestService){
    
    $controller = _$controller_;
  }));

  
  
  describe('login', function() {
	  
    it('should login', inject(function(userDataRestService) {
      var $scope = {};
      var controller = $controller('LoginCntl', { $scope: $scope, userDataRestService: userDataRestService });
      
      $scope.credentials.username = 'name';
      $scope.credentials.password = 'pswd';
      

      $scope.login();
      
      //TODO
    }));
    
    
    it('should logout', inject(function(userDataRestService) {
        var $scope = {};
        var controller = $controller('LoginCntl', { $scope: $scope, userDataRestService: userDataRestService });
        

        $scope.logout();
        
        //TODO
      }));
  });
});