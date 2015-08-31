describe('Beacon data rest service tests', function () {
    'use strict';

    var beaconDataRestService;
    var REST_URL = 'url-prefix/services/beacon';

    beforeEach(module('app.main'));
    beforeEach(inject(function (_beaconDataRestService_) {
        beaconDataRestService = _beaconDataRestService_;
    }));

    describe('beaconDataRestService tests', function () {
        it('should call $http.get when retrieving beacons data', inject(function ($http, currentContextPath) {
            // given
            spyOn(currentContextPath, 'get').and.returnValue('url-prefix/');
            spyOn($http, 'get');
            // when
            beaconDataRestService.getBeaconsData();
            // then
            expect($http.get).toHaveBeenCalledWith('url-prefix/services/beacons');
        }));
    });

    it('should call $http.get when retrieving beacons data', inject(function ($http, currentContextPath) {
        // given
        var building = 'MT2';
        var floor = '7';
        spyOn(currentContextPath, 'get').and.returnValue('url-prefix/');
        spyOn($http, 'get');
        // when
        beaconDataRestService.getBeaconsDataOnGivenFloor(building, floor);
        // then
        expect($http.get).toHaveBeenCalledWith('url-prefix/services/beacons/' + building + '/' + floor);
    }));

    it('should call $http.post when adding new beacon', inject(function ($http, currentContextPath) {
        // given
        var beacon = {
            project: 'test'
        };
        spyOn(currentContextPath, 'get').and.returnValue('url-prefix/');
        spyOn($http, 'post');
        // when
        beaconDataRestService.addBeaconData(beacon);
        // then
        expect($http.post).toHaveBeenCalledWith(REST_URL, beacon);
    }));

    it('should call $http.delete when deleting beacon', inject(function ($http, currentContextPath) {
        // given
        var id = 40982;
        spyOn(currentContextPath, 'get').and.returnValue('url-prefix/');
        spyOn($http, 'delete');
        // when
        beaconDataRestService.deleteBeaconData(id);
        // then
        expect($http.delete).toHaveBeenCalledWith(REST_URL + '/' + id);
    }));

    it('should call $http.put when updating new beacon', inject(function ($http, currentContextPath) {
        // given
        var beacon = {
            project: 'test'
        };
        spyOn(currentContextPath, 'get').and.returnValue('url-prefix/');
        spyOn($http, 'put');
        // when
        beaconDataRestService.updateBeaconData(beacon);
        // then
        expect($http.put).toHaveBeenCalledWith(REST_URL, beacon);
    }));
});