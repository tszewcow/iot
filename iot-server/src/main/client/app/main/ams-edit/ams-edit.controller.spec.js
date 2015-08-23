describe('Edit ams tests', function () {
    'use strict';

    beforeEach(module('app.main'));

    var $scope;
    var modalInstanceMock = {
        close: jasmine.createSpy(),
        dismiss: jasmine.createSpy()
    };
    var amsMock = {
        number: 2,
        project: 'some project 2',
        guardian: 'normal guardian 2',
        building: 'some building number 2',
        stock: 'stock number 2',
        room: 'room number 2',
        coordinateX: '32',
        coordinateY: "2"
    };

    beforeEach(inject(function ($controller, $rootScope) {
        $scope = $rootScope.$new();
        $controller('EditAmsCntl', {
            $scope: $scope,
            $modalInstance: modalInstanceMock,
            ams: amsMock
        });
    }));

    describe('testing modal controls', function () {
        it('ok should close dialog and returned modified data', function () {
            // given when
            $scope.ok();
            // then
            expect(modalInstanceMock.close).toHaveBeenCalledWith(amsMock);
        });

        it('cancel should close modal without returning data', function () {
            //given when
            $scope.cancel();
            //then
            expect(modalInstanceMock.dismiss).toHaveBeenCalledWith('cancel');
        });
    });

});