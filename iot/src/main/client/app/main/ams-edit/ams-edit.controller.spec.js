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
        floor: 'stock number 2',
        room: 'room number 2',
        coordinateX: '32',
        coordinateY: '2',
        ipAutomaticMobileSet: '125'
    };

    beforeEach(inject(function ($controller, $rootScope, $compile) {
        $scope = $rootScope.$new();
        var element = angular.element('<form name="amsEditForm" />');
        $compile(element)($scope);

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
        it('should initialization floors table', function () {
            //given when then
            expect($scope.buildings).toEqual({
                MTII: [5, 6, 7, 8, 9, 10, 11],
                MTIV: [6, 7, 8, 9, 10, 11, 12]
            });
        });

        it('should disable guradian if project is <none>', function () {
            // given
            $scope.data.project = '<none>';
            // when then
            expect($scope.disableGuardianInput()).toBeTruthy();
        });
    });

});