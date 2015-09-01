describe('Locations tests', function () {
    'use strict';

    var $scope;
    var deferredLocations;

    beforeEach(module('app.main'));

    describe('scope model initialization', function () {

        beforeEach(inject(function ($controller, $rootScope, floorLocationRestService, $q) {
        	$scope = $rootScope.$new();
            
        	deferredLocations = $q.defer();
            
            spyOn(floorLocationRestService, 'getFloorLocations').and.returnValue(deferredLocations.promise);
            deferredLocations.resolve({
                data: [
                       {
                           building: 'MT2',
                           floor: 6
                       },
                       {
                    	   building: 'MT4',
                    	   floor: 7
                       }
                   ]
               });

            $controller('LocationsCntl', {
                $scope: $scope,
                globalSpinner: {
                    decorateCallOfFunctionReturningPromise: function (func) {
                        func();
                    }
                }
            });
        }));


        it('should create links', inject(function (floorLocationRestService) {
            // given when 
            $scope.$digest();

            // then
            expect($scope.locationLinks.length).toEqual(2);
            expect($scope.locationLinks[0].url).toEqual('#/main/location-map?building=MT2&floor=6');
            expect($scope.locationLinks[0].text).toEqual('MT2 floor 6');
            expect($scope.locationLinks[1].url).toEqual('#/main/location-map?building=MT4&floor=7');
            expect($scope.locationLinks[1].text).toEqual('MT4 floor 7');
            expect(floorLocationRestService.getFloorLocations).toHaveBeenCalled();
        }));

    });
});