describe('Add beacon tests', function () {
    'use strict';

    beforeEach(module('app.main'));

    var $scope;
    var modalInstanceMock = {
        close: jasmine.createSpy(),
        dismiss: jasmine.createSpy()
    };

    beforeEach(inject(function ($controller, $rootScope) {
        $scope = $rootScope.$new();
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
    });

});