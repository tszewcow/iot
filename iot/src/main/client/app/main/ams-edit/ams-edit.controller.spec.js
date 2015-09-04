describe('Edit ams tests', inject(function (allBuildings) {
    'use strict';

    var $scope;
    var modalInstanceMock = {
        close: jasmine.createSpy(),
        dismiss: jasmine.createSpy()
    };
    var allBuildingsMock = [
        {
            MT2: [1, 2, 3]
        },
        {
            MT4: [2, 3, 4]
        }
    ];
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

    beforeEach(module('app.main'));
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

        it('should disable guradian if project is <none>', function () {
            // given
            $scope.data.project = '<none>';
            // when then
            expect($scope.disableGuardianInput()).toBeTruthy();
        });

        it('should enable guradian if project is not <none>', function () {
            // given
            $scope.data.project = '';
            // when then
            expect($scope.disableGuardianInput()).toBeFalsy();
        });

        it('should disable floors selection if builidng was selected', function () {
            //given
            $scope.data.building = 'MT2';
            //when then
            expect($scope.floorSelectionDisabled()).toBeFalsy();
        });

        it('should not disable floors selection if builiding is empty', function () {
            //given
            $scope.data.building = '';
            //when then
            expect($scope.floorSelectionDisabled()).toBeFalsy();
        });
    });
}));