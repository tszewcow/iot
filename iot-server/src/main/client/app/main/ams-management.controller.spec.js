describe('Ams management tests', function () {
    'use strict';

    var $scope;

    beforeEach(module('app.main'));

    beforeEach(inject(function ($controller, $rootScope) {
        $scope = $rootScope.$new();
        $controller('AmsManagementCntl', {
            $scope: $scope
        });
    }));


    describe('scope model initialization', function () {
        it('should initialize model', function () {
            // given when then
            expect($scope.amsModel.length).toEqual(0);
            expect(angular.isDefined($scope.mySelectedItems)).toBeTruthy();
        });


    });

    describe('scope functions test', function () {
        xit('should add new ams', function () {
            // given when
            $scope.addAms();
            // then
            expect($scope.amsModel.length).toEqual(3);
            expect($scope.amsModel[2].number).toEqual(3);
        });

        xit('should delete selected ams', function () {
            // given
            $scope.mySelectedItems.push($scope.amsModel[0]);
            // when
            $scope.deleteAms();
            // then
            expect($scope.amsModel.length).toEqual(1);
            expect($scope.amsModel[0].number).toEqual(2);
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

        it('should call $modal.show when edit ams function is called', inject(function ($modal) {
            // given
            $scope.mySelectedItems.push('some entry');
            spyOn($modal, 'open').and.returnValue({
                result: {
                    then: angular.noop
                }
            });
            // when
            $scope.editAms();
            // then
            expect($modal.open).toHaveBeenCalledWith({
                templateUrl: '/main/ams-edit.html',
                controller: 'EditAmsCntl',
                animation: true,
                resolve: {
                    ams: jasmine.any(Function)
                }
            });
            expect($modal.open.calls.argsFor(0)[0].resolve.ams()).toEqual('some entry');
        }));
    });
});