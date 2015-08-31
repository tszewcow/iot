describe('Floor location data rest service tests', function () {
    'use strict';

    var floorLocationRestService;

    beforeEach(module('app.main'));
    beforeEach(inject(function (_floorLocationRestService_) {
    	floorLocationRestService = _floorLocationRestService_;
    }));

    describe('floorLocationRestService tests', function () {
        it('should call $http.get when retrieving floors', inject(function ($http, currentContextPath) {
            // given
            spyOn(currentContextPath, 'get').and.returnValue('url-prefix/');
            spyOn($http, 'get');
            // when
            floorLocationRestService.getFloorLocations();
            // then
            expect($http.get).toHaveBeenCalledWith('url-prefix/services/locationfloor');
        }));
    });
});