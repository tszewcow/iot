describe('Location map tests', function () {
    'use strict';

    var $scope;
    var deferred;
    
    beforeEach(module('app.main'));
    
    beforeEach(inject(function($controller, $rootScope, amsDataRestService, $interval, $q){
    	deferred = $q.defer();
    	$scope = $rootScope.$new();
    	
    	spyOn(amsDataRestService, 'getAmsData').and.returnValue(deferred.promise);
        
    	$controller('LocationMapCntl', {$scope: $scope})
    }));


    describe('scope model initialization', function () {
        it('should initialize all elements', inject(function ($q, amsDataRestService) {
        	// given when 
        	deferred.resolve({
                data: [
                    {	project: 'ams-1', xAutomaticMobileSet: 100, yAutomaticMobileSet: 350 },
                    {	project: 'ams-2', xAutomaticMobileSet: 5, 	yAutomaticMobileSet: 202 },
                    {	project: 'ams-3', xAutomaticMobileSet: 1280, yAutomaticMobileSet: 393 }
                ]
            });
        	$scope.$digest();
        	
        	// then
            expect(amsDataRestService.getAmsData).toHaveBeenCalled();
            expect($scope.locationModel.length).toEqual(3);
            expect($scope.locationModel[0].name).toEqual('ams-1');
            expect($scope.locationModel[0].xPos).toEqual(100);
            expect($scope.locationModel[0].yPos).toEqual(350);
            expect($scope.locationModel[1].name).toEqual('ams-2');
            expect($scope.locationModel[1].xPos).toEqual(5);
            expect($scope.locationModel[1].yPos).toEqual(202);
            expect($scope.locationModel[2].name).toEqual('ams-3');
            expect($scope.locationModel[2].xPos).toEqual(1280);
            expect($scope.locationModel[2].yPos).toEqual(393);
        }));
        
        it('should floor floats to ints', inject(function ($q, amsDataRestService) {
        	// given when 
        	deferred.resolve({
                data: [{	
                    	project: 'mobile_set', 
                    	xAutomaticMobileSet: 850.5, 
                    	yAutomaticMobileSet: 1001.9009 
                    }]
            });
        	$scope.$digest();
        	
        	// then
            expect(amsDataRestService.getAmsData).toHaveBeenCalled();
            expect($scope.locationModel.length).toEqual(1);
            expect($scope.locationModel[0].name).toEqual('mobile_set');
            expect($scope.locationModel[0].xPos).toEqual(850);
            expect($scope.locationModel[0].yPos).toEqual(1001);
        }));
        
        it('should work with no amses', inject(function ($q, amsDataRestService) {
        	// given when 
        	deferred.resolve({data: []});
        	$scope.$digest();
        	
        	// then
            expect(amsDataRestService.getAmsData).toHaveBeenCalled();
            expect($scope.locationModel).toBeDefined();
            expect($scope.locationModel.length).toEqual(0);
        }));
    });


});