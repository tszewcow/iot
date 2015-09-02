describe('Edit beacon tests', function () {
    'use strict';

    var $scope;
    var modalInstanceMock = {
        close: jasmine.createSpy(),
        dismiss: jasmine.createSpy()
    };
    var beaconMock = {
        number: 1,
        name: 'some name ',
        mac: 'D6:90:A8:08:F0:E0',
        uuidNormal: 'normal uuid',
        uuidSecure: 'secure uuid',
        uuidService: 'service uuid',
        building: 'some building number',
        floor: 'stock number',
        room: 'room number',
        xBeacon: 'x',
        yBeacon: 'y'
    };

    beforeEach(module('app.main'));
    beforeEach(inject(function ($controller, $rootScope, $compile) {
        $scope = $rootScope.$new();
        var element = angular.element('<form name="beaconEditForm" />');
        $compile(element)($scope);

        $controller('BeaconEditCntl', {
            $scope: $scope,
            $modalInstance: modalInstanceMock,
            beacon: beaconMock
        });
    }));

    describe('testing modal controls', function () {
        it('ok should close dialog and returned modified data', function () {
            // given when
            $scope.ok();
            // then
            expect(modalInstanceMock.close).toHaveBeenCalledWith(beaconMock);
        });

        it('cancel should close modal without returning data', function () {
            //given when
            $scope.cancel();
            //then
            expect(modalInstanceMock.dismiss).toHaveBeenCalledWith('cancel');
        });
        it('should initialization floors table', function () {
            //given when then
            expect($scope.buildings).toEqual({
                MT2: [5, 6, 7, 8, 9, 10, 11],
                MT4: [6, 7, 8, 9, 10, 11, 12]
            });
        });
    });

});