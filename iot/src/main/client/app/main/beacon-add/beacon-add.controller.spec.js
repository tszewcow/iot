describe('Add beacon tests', function () {
    'use strict';

    beforeEach(module('app.main'));

    var $scope;
    var modalInstanceMock = {
        close: jasmine.createSpy(),
        dismiss: jasmine.createSpy()
    };

    beforeEach(inject(function ($controller, $rootScope, $compile) {
        $scope = $rootScope.$new();
        var element = angular.element('<form name="beaconAddForm"/>');
        $compile(element)($scope);

        $controller('BeaconAddCntl', {
            $scope: $scope,
            $modalInstance: modalInstanceMock
        });
    }));


    describe('testing modal controls', function () {
        it('ok should close dialog and return new beacon data', function () {
            // given when
            $scope.ok();
            // then
            expect(modalInstanceMock.close).toHaveBeenCalledWith({
                id: null,
                name: '',
                mac: '',
                uuidNormal: '',
                uuidSecure: '',
                uuidService: '',
                building: '',
                floor: '',
                room: '',
                xBeacon: '',
                yBeacon: ''
            });
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