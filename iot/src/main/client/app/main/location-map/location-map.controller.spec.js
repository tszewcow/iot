describe('Location map tests', function () {
    'use strict';

    var $scope;
    var deferredAms;
    var deferredBeacon;

    beforeEach(module('app.main'));

    describe('scope model initialization', function () {

        beforeEach(inject(function ($controller, $rootScope, amsDataRestService, beaconDataRestService, $interval, $q, $routeParams) {
            deferredAms = $q.defer();
            deferredBeacon = $q.defer();
            $scope = $rootScope.$new();
            $routeParams.building = 'MTII';
            $routeParams.floor = 7;

            spyOn(amsDataRestService, 'getAmsDataOnGivenFloor').and.returnValue(deferredAms.promise);
            spyOn(beaconDataRestService, 'getBeaconsDataOnGivenFloor').and.returnValue(deferredBeacon.promise);

            $controller('LocationMapCntl', {
                $scope: $scope
            });
        }));

        afterEach(inject(function (amsDataRestService, beaconDataRestService, $routeParams) {
            expect(amsDataRestService.getAmsDataOnGivenFloor).toHaveBeenCalledWith('MTII', 7);
            expect(beaconDataRestService.getBeaconsDataOnGivenFloor).toHaveBeenCalledWith('MTII', 7);
        }));

        it('should initialize all amses', inject(function ($q, amsDataRestService, beaconDataRestService) {
            // given when 
            deferredAms.resolve({
                data: [
                    {
                        project: 'ams-1',
                        xAutomaticMobileSet: 100,
                        yAutomaticMobileSet: 350
                    },
                    {
                        project: 'ams-2',
                        xAutomaticMobileSet: 5,
                        yAutomaticMobileSet: 202
                    },
                    {
                        project: 'ams-3',
                        xAutomaticMobileSet: 1280,
                        yAutomaticMobileSet: 393
                    }
                ]
            });
            deferredBeacon.resolve({
                data: []
            });
            $scope.$digest();

            // then
            expect($scope.locationModel.length).toEqual(3);
            expect($scope.locationModel[0].project).toEqual('ams-1');
            expect($scope.locationModel[0].xPos).toEqual(100);
            expect($scope.locationModel[0].yPos).toEqual(350);
            expect($scope.locationModel[1].project).toEqual('ams-2');
            expect($scope.locationModel[1].xPos).toEqual(5);
            expect($scope.locationModel[1].yPos).toEqual(202);
            expect($scope.locationModel[2].project).toEqual('ams-3');
            expect($scope.locationModel[2].xPos).toEqual(1280);
            expect($scope.locationModel[2].yPos).toEqual(393);
        }));

        it('should initialize beacons', inject(function ($q, amsDataRestService, beaconDataRestService) {
            // given when 
            deferredAms.resolve({
                data: []
            });
            deferredBeacon.resolve({
                data: [
                    {
                        name: 'beacon01',
                        xBeacon: 404,
                        yBeacon: 124
                    },
                    {
                        name: 'beacon02',
                        xBeacon: 405,
                        yBeacon: 287
                    }
                   ]
            });
            $scope.$digest();

            // then
            expect($scope.beacons.length).toEqual(2);
            expect($scope.beacons[0].name).toEqual('beacon01');
            expect($scope.beacons[0].xPos).toEqual(404);
            expect($scope.beacons[0].yPos).toEqual(124);
            expect($scope.beacons[1].name).toEqual('beacon02');
            expect($scope.beacons[1].xPos).toEqual(405);
            expect($scope.beacons[1].yPos).toEqual(287);
        }));

        it('should floor ams floats to ints', inject(function ($q, amsDataRestService, beaconDataRestService) {
            // given when 
            deferredAms.resolve({
                data: [{
                    project: 'mobile_set',
                    xAutomaticMobileSet: 850.5,
                    yAutomaticMobileSet: 1001.9009
                    }]
            });
            deferredBeacon.resolve({
                data: []
            });
            $scope.$digest();

            // then
            expect($scope.locationModel.length).toEqual(1);
            expect($scope.locationModel[0].project).toEqual('mobile_set');
            expect($scope.locationModel[0].xPos).toEqual(850);
            expect($scope.locationModel[0].yPos).toEqual(1001);
        }));

        it('should floor beacon floats to ints', inject(function ($q, amsDataRestService, beaconDataRestService) {
            // given when 
            deferredAms.resolve({
                data: []
            });
            deferredBeacon.resolve({
                data: [{
                    name: 'BeaconTest001',
                    xBeacon: 850.5,
                    yBeacon: 1001.9009
                }]
            });
            $scope.$digest();

            // then
            expect($scope.beacons.length).toEqual(1);
            expect($scope.beacons[0].name).toEqual('BeaconTest001');
            expect($scope.beacons[0].xPos).toEqual(850);
            expect($scope.beacons[0].yPos).toEqual(1001);
        }));

        it('should work with no points', inject(function ($q, amsDataRestService, beaconDataRestService) {
            // given when 
            deferredAms.resolve({
                data: []
            });
            deferredBeacon.resolve({
                data: []
            });
            $scope.$digest();

            // then
            expect($scope.locationModel).toBeDefined();
            expect($scope.locationModel.length).toEqual(0);
            expect($scope.beacons).toBeDefined();
            expect($scope.beacons.length).toEqual(0);
        }));

        it('should initialize both amses and beacons', inject(function ($q, amsDataRestService, beaconDataRestService) {
            // given when 
            deferredAms.resolve({
                data: [{
                    project: 'ams-1',
                    xAutomaticMobileSet: 100,
                    yAutomaticMobileSet: 350
                }]
            });
            deferredBeacon.resolve({
                data: [{
                    name: 'BeaconTest001',
                    xBeacon: 850.5,
                    yBeacon: 1001.9009
                }]
            });
            $scope.$digest();

            // then
            expect($scope.locationModel.length).toEqual(1);
            expect($scope.beacons.length).toEqual(1);
        }));

        it('should not show warning when isActual is true', inject(function () {
            //given
            deferredAms.resolve({
                data: [{
                    isActual: true
                    }]
            });
            deferredBeacon.resolve({
                data: []
            });
            $scope.$digest();
            //when
            $scope.showWarning();
            //then
            expect($scope.showWarning()).toBeFalsy();
        }));

        it('should show warning when isActual is false', inject(function () {
            //given
            deferredAms.resolve({
                data: [{
                    isActual: false
                    }]
            });
            deferredBeacon.resolve({
                data: []
            });
            $scope.$digest();
            //when
            $scope.showWarning();
            //then
            expect($scope.showWarning()).toBeTruthy();
        }));
    });

    describe('calculating building and floor from params', function () {

        beforeEach(inject(function ($controller, $rootScope, amsDataRestService, beaconDataRestService, $interval, $q, $routeParams) {
            deferredAms = $q.defer();
            deferredBeacon = $q.defer();
            $scope = $rootScope.$new();
            $routeParams.building = 'TestBuildingIV';
            $routeParams.floor = 129;
            var emptyResult = {
                data: []
            };

            spyOn(amsDataRestService, 'getAmsDataOnGivenFloor').and.returnValue(deferredAms.promise);
            spyOn(beaconDataRestService, 'getBeaconsDataOnGivenFloor').and.returnValue(deferredBeacon.promise);

            $controller('LocationMapCntl', {
                $scope: $scope
            });
            $scope.$digest();
        }));

        it('should return proper image url', inject(function ($q, amsDataRestService, beaconDataRestService) {
            expect($scope.imageUrl).toEqual('/main/img/TestBuildingIV_129.png');
        }));

        it('should load points for building and floor', inject(function ($q, amsDataRestService, beaconDataRestService) {
            expect(amsDataRestService.getAmsDataOnGivenFloor).toHaveBeenCalledWith('TestBuildingIV', 129);
            expect(beaconDataRestService.getBeaconsDataOnGivenFloor).toHaveBeenCalledWith('TestBuildingIV', 129);
        }));
    });
});