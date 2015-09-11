describe('Add beacon tests', function () {
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
        var element = angular.element('<form name="beaconAddForm"/>');
        $compile(element)($scope);

        $controller('BeaconAddCntl', {
            $scope: $scope,
            $modalInstance: modalInstanceMock,
            allBuildings: allBuildingsMock
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
        it('should disable floors selection if building was selected', function () {
            //given
            $scope.newBeacon.building = 'MT2';
            //when then
            expect($scope.floorSelectionDisabled()).toBeFalsy();
        });

        it('should not disable floors selection if building is empty', function () {
            //given
            $scope.newBeacon.building = '';
            //when then
            expect($scope.floorSelectionDisabled()).toBeTruthy();
        });
    });
});