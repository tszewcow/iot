describe('Beacons management tests', function () {
    'use strict';

    var $scope;

    beforeEach(module('app.main'));

    beforeEach(inject(function ($controller, $rootScope) {
        $scope = $rootScope.$new();
        $controller('beaconsManagementCntl', {
            $scope: $scope
        });
    }));


    describe('scope model initialization', function () {
        xit('should initialize model', inject(function ($q, beaconDataRestService) {
            var deferred = $q.defer();
            spyOn(beaconDataRestService, 'getBeaconsData').and.callFake(function () {
                return deferred.promise;
            });
            deferred.resolve({
                data: [1, 2]
            });
            $scope.$digest();

            expect(beaconDataRestService.getBeaconsData).toHaveBeenCalled();
        }));



    });

    describe('scope functions test', function () {
        xit('should add new beacon', function () {
            // given when
            $scope.addBeacon();
            // then
            expect($scope.beaconModel.length).toEqual(3);
            expect($scope.beaconModel[2].number).toEqual(3);
        });

        xit('should delete selected beacon', function () {
            // given
            $scope.mySelectedItems.push($scope.beaconModel[0]);
            // when
            $scope.deleteBeacon();
            // then
            expect($scope.beaconModel.length).toEqual(1);
            expect($scope.beaconModel[0].number).toEqual(2);
        });

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

        it('should call $modal.show when edit beacon function is called', inject(function ($modal) {
            // given
            $scope.mySelectedItems.push('some entry');
            spyOn($modal, 'open').and.returnValue({
                result: {
                    then: angular.noop
                }
            });
            // when
            $scope.editBeacon();
            // then
            expect($modal.open).toHaveBeenCalledWith({
                templateUrl: '/main/html/beacon-edit.html',
                controller: 'editBeaconCntl',
                animation: true,
                resolve: {
                    beacon: jasmine.any(Function)
                }
            });
            expect($modal.open.calls.argsFor(0)[0].resolve.beacon()).toEqual('some entry');
        }));
    });
});