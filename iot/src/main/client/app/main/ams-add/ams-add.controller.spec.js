describe('Add ams tests', function () {
    'use strict';

    var $scope;
    var modalInstanceMock = {
        close: jasmine.createSpy(),
        dismiss: jasmine.createSpy()
    };

    beforeEach(module('app.main'));
    beforeEach(inject(function ($controller, $rootScope, $compile) {
        $scope = $rootScope.$new();
        var element = angular.element('<form name="amsAddForm" />');
        $compile(element)($scope);

        $controller('AddAmsCntl', {
            $scope: $scope,
            $modalInstance: modalInstanceMock
        });
    }));


    describe('testing modal controls', function () {
        it('ok should close dialog and return new ams data', function () {
            // given when
            $scope.ok();
            // then
            expect(modalInstanceMock.close).toHaveBeenCalledWith({
                id: null,
                project: '',
                guardian: '',
                building: '',
                floor: '',
                room: '',
                xAutomaticMobileSet: '',
                yAutomaticMobileSet: '',
                macAutomaticMobileSet: '',
                ipAutomaticMobileSet: ''
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

        it('should disable guradian if project is <none>', function () {
            // given
            $scope.newAms.project = '<none>';
            // when then
            expect($scope.disableGuardianInput()).toBeTruthy();
        });
        it('should enable guradian if project is not <none>', function () {
            // given
            $scope.newAms.project = '';
            // when then
            expect($scope.disableGuardianInput()).toBeFalsy();
        });
    });

});