describe('Beacon data rest service tests', function(){
   'use strict';

    var beaconDataRestService;

    beforeEach(module('app.main'));
    beforeEach(inject(function(_beaconDataRestService_){
        beaconDataRestService = _beaconDataRestService_;
    }));

    describe('beaconDataRestService tests', function(){
       it('should call $http.get when retrieving beacons data', inject(function($http, currentContextPath){
           // given
           spyOn(currentContextPath, 'get').and.returnValue('url-prefix/');
           spyOn($http, 'get');
           // when
           beaconDataRestService.getBeaconsData();
           // then
           expect($http.get).toHaveBeenCalledWith('url-prefix/beacons');
       }));
    });
});