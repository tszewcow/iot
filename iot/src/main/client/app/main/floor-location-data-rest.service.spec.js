describe('Floor location data rest service tests', function () {
    'use strict';

    beforeEach(module('app.main'));
    beforeEach(inject(function (_floorLocationRestService_) {
        floorLocationRestService = _floorLocationRestService_;
    }));

    var floorLocationRestService;

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

        it('should call $http.get when retrieving buildings', inject(function ($http, currentContextPath) {
            // given
            spyOn(currentContextPath, 'get').and.returnValue('url-prefix/');
            spyOn($http, 'get');
            // when
            floorLocationRestService.getAllBuildings();
            // then
            expect($http.get).toHaveBeenCalledWith('url-prefix/services/buildings');
        }));
    });
});