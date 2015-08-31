describe('AMS data rest service tests', function () {
    'use strict';

    var amsDataRestService;
    var REST_URL = 'url-prefix/services/automaticmobileset';

    beforeEach(module('app.main'));
    beforeEach(inject(function (_amsDataRestService_) {
        amsDataRestService = _amsDataRestService_;
    }));

    describe('amsDataRestService tests', function () {
        it('should call $http.get when retrieving ams data', inject(function ($http, currentContextPath) {
            // given
            spyOn(currentContextPath, 'get').and.returnValue('url-prefix/');
            spyOn($http, 'get');
            // when
            amsDataRestService.getAmsData();
            // then
            expect($http.get).toHaveBeenCalledWith(REST_URL);
        }));

        it('should call $http.get when retrieving ams data', inject(function ($http, currentContextPath) {
            // given
            var building = 'MT2';
            var floor = '7';
            spyOn(currentContextPath, 'get').and.returnValue('url-prefix/');
            spyOn($http, 'get');
            // when
            amsDataRestService.getAmsDataOnGivenFloor(building, floor);
            // then
            expect($http.get).toHaveBeenCalledWith('url-prefix/services/automaticmobileset/' + building + '/' + floor);
        }));


        it('should call $http.post when adding new ams', inject(function ($http, currentContextPath) {
            // given
            var ams = {
                project: 'test'
            };
            spyOn(currentContextPath, 'get').and.returnValue('url-prefix/');
            spyOn($http, 'post');
            // when
            amsDataRestService.addAmsData(ams);
            // then
            expect($http.post).toHaveBeenCalledWith(REST_URL, ams);
        }));

        it('should call $http.delete when deleting ams', inject(function ($http, currentContextPath) {
            // given
            var id = 40982;
            spyOn(currentContextPath, 'get').and.returnValue('url-prefix/');
            spyOn($http, 'delete');
            // when
            amsDataRestService.deleteAmsData(id);
            // then
            expect($http.delete).toHaveBeenCalledWith(REST_URL + '/' + id);
        }));

        it('should call $http.put when updating new ams', inject(function ($http, currentContextPath) {
            // given
            var ams = {
                project: 'test'
            };
            spyOn(currentContextPath, 'get').and.returnValue('url-prefix/');
            spyOn($http, 'put');
            // when
            amsDataRestService.updateAmsData(ams);
            // then
            expect($http.put).toHaveBeenCalledWith(REST_URL, ams);
        }));

    });

});