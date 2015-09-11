describe('Add ams tests', function () {
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

    beforeEach(module('app.main'));
    beforeEach(inject(function ($controller, $rootScope, $compile) {
        $scope = $rootScope.$new();
        var element = angular.element('<form name="amsAddForm" />');
        $compile(element)($scope);

        $controller('AddAmsCntl', {
            $scope: $scope,
            $modalInstance: modalInstanceMock,
            allBuildings: allBuildingsMock
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
                ipAutomaticMobileSet: '',
                userAutomaticMobileSet: '',
                passwordAutomaticMobileSet: ''
            });
        });

        it('cancel should close modal without returning data', function () {
            //given when
            $scope.cancel();
            //then
            expect(modalInstanceMock.dismiss).toHaveBeenCalledWith('cancel');
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

        it('should disable floors selection if building was selected', function () {
            //given
            $scope.newAms.building = 'MT2';
            //when then
            expect($scope.floorSelectionDisabled()).toBeFalsy();
        });

        it('should not disable floors selection if building is empty', function () {
            //given
            $scope.newAms.building = '';
            //when then
            expect($scope.floorSelectionDisabled()).toBeTruthy();
        });
    });
});