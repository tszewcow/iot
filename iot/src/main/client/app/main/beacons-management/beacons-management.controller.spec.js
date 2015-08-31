describe('Beacons management tests', function () {
    'use strict';

    var $scope;

    beforeEach(module('app.main'));
    beforeEach(module('oasp.oaspUi.spinner'));

    beforeEach(inject(function ($controller, $rootScope, beaconDataRestService, $q) {
        // given
        var deferred = $q.defer();
        $scope = $rootScope.$new();
        spyOn(beaconDataRestService, 'getBeaconsData').and.returnValue(deferred.promise);
        deferred.resolve({
            data: [
                {
                    name: 'beacon-1',
                    id: 1
                },
                {
                    name: 'beacon-2',
                    id: 2
                }
            ]
        });
        // when
        $controller('BeaconsManagementCntl', {
            $scope: $scope,
            globalSpinner: {
                decorateCallOfFunctionReturningPromise: function (func) {
                    func();
                }
            }
        });
        $scope.$digest();
    }));


    describe('data load on dialog start tests', function () {
        it('should get beacons data on dialog lunch', inject(function (globalSpinner, beaconDataRestService, $controller) {
            // given
            // when
            $controller('BeaconsManagementCntl', {
                $scope: $scope,
                globalSpinner: globalSpinner
            });
            $scope.$digest();

            // then
            expect(beaconDataRestService.getBeaconsData).toHaveBeenCalled();
            expect($scope.beacons.length).toEqual(2);
        }));

    });

    describe('scope model initialization', function () {
        it('should initialize model', inject(function ($q, beaconDataRestService) {
            // given when then
            expect(beaconDataRestService.getBeaconsData).toHaveBeenCalled();
            expect($scope.beacons.length).toEqual(2);
            expect($scope.beacons[0].name).toEqual('beacon-1');
            expect($scope.beacons[1].name).toEqual('beacon-2');
            expect($scope.mySelectedItems.length).toEqual(0);
        }));
    });

    describe('scope functions test', function () {
        it('should call modal on addBeacon function call and add beacon on success', inject(function ($modal, $q, beaconDataRestService) {
            // given
            var modalDeferred = $q.defer(),
                beaconDataServiceDeferred = $q.defer();
            spyOn($modal, 'open').and.returnValue({
                result: modalDeferred.promise
            });
            spyOn(beaconDataRestService, 'addBeaconData').and.returnValue(beaconDataServiceDeferred.promise);
            // when
            $scope.addBeacon();
            modalDeferred.resolve();
            beaconDataServiceDeferred.resolve({
                data: {
                    name: 'new beacon'
                }
            });
            $scope.$digest();
            // then
            expect($modal.open).toHaveBeenCalledWith({
                templateUrl: '/main/beacon-add/beacon-add.tpl.html',
                controller: 'BeaconAddCntl',
                animation: true,
                size: 'lg'
            });
            expect($scope.beacons.length).toEqual(3);
            expect($scope.beacons[2].name).toEqual('new beacon');
        }));

        it('should delete selected beacon', inject(function (beaconDataRestService) {
            // given
            spyOn(beaconDataRestService, 'deleteBeaconData');
            $scope.mySelectedItems.push($scope.beacons[0]);
            // when
            $scope.deleteBeacon();
            // then
            expect(beaconDataRestService.deleteBeaconData).toHaveBeenCalledWith($scope.mySelectedItems[0].id);
            expect($scope.beacons.length).toEqual(1);
            expect($scope.beacons[0]).toEqual({
                name: 'beacon-2',
                id: 2
            });

        }));

        it('should disable button controls when there are no items selected', function () {
            // given when then
            expect($scope.controlButtonDisabled()).toBeTruthy();
        });

        it('should enable button controls when there are items selected', function () {
            // given
            $scope.mySelectedItems.push('some selected entry');
            // when then
            expect($scope.controlButtonDisabled()).toBeFalsy();
        });

        it('should call $modal.show when edit beacon function is called', inject(function ($modal, $q, beaconDataRestService) {
            // given
            var modalDeferred = $q.defer(),
                beaconDataRestServiceDeferred = $q.defer();
            $scope.mySelectedItems.push('some entry');
            spyOn($modal, 'open').and.returnValue({
                result: {
                    then: angular.noop
                }
            });
            spyOn(beaconDataRestService, 'updateBeaconData').and.returnValue(beaconDataRestServiceDeferred.promise);

            // when
            $scope.editBeacon();
            modalDeferred.resolve('some modal response');
            beaconDataRestServiceDeferred.resolve();
            $scope.$digest();
            // then
            expect($modal.open).toHaveBeenCalledWith({
                templateUrl: '/main/beacon-edit/beacon-edit.tpl.html',
                controller: 'BeaconEditCntl',
                animation: true,
                size: 'lg',
                resolve: {
                    beacon: jasmine.any(Function)
                }
            });
            expect($modal.open.calls.argsFor(0)[0].resolve.beacon()).toEqual('some entry');
        }));

        it('should call $modal.show when add beacon function is called', inject(function ($modal, $q, beaconDataRestService) {
            // given
            var modalDeferred = $q.defer(),
                beaconDataRestServiceDeferred = $q.defer();
            spyOn($modal, 'open').and.returnValue({
                result: modalDeferred.promise
            });
            spyOn(beaconDataRestService, 'addBeaconData').and.returnValue(beaconDataRestServiceDeferred.promise);
            // when
            $scope.addBeacon();
            modalDeferred.resolve('some modal response');
            beaconDataRestServiceDeferred.resolve({
                data: 'added beacon'
            });
            $scope.$digest();
            // then
            expect($modal.open).toHaveBeenCalledWith({
                templateUrl: '/main/beacon-add/beacon-add.tpl.html',
                controller: 'BeaconAddCntl',
                animation: true,
                size: 'lg'
            });
            expect(beaconDataRestService.addBeaconData).toHaveBeenCalledWith('some modal response');
            expect($scope.beacons.length).toEqual(3);
            expect($scope.beacons[0]).toEqual({
                name: 'beacon-1',
                id: 1
            });
        }));
    });
});