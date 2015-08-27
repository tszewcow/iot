describe('Ams management tests', function () {
    'use strict';

    beforeEach(module('app.main'));

    var $scope;

    beforeEach(inject(function ($controller, $rootScope, amsDataRestService) {
        spyOn(amsDataRestService, 'getAmsData').and.returnValue({
            then: angular.noop
        });
        $scope = $rootScope.$new();
        $controller('AmsManagementCntl', {
            $scope: $scope,
            globalSpinner: {
                decorateCallOfFunctionReturningPromise: function (func) {
                    func()
                }
            }
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

        // TODO: repair test
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
                templateUrl: '/main/ams-edit/ams-edit.tpl.html',
                controller: 'EditAmsCntl',
                animation: true,
                resolve: {
                    ams: jasmine.any(Function)
                }
            });
            expect($modal.open.calls.argsFor(0)[0].resolve.ams()).toEqual('some entry');
        }));

        it('should call $modal.show when add ams function is called', inject(function ($modal, $q, amsDataRestService) {
            // given
            var modalDeferred = $q.defer(),
                amsDataRestServiceDeferred = $q.defer();
            spyOn($modal, 'open').and.returnValue({
                result: modalDeferred.promise
            });
            spyOn(amsDataRestService, 'addAmsData').and.returnValue(amsDataRestServiceDeferred.promise);
            // when
            $scope.addAms();
            modalDeferred.resolve('some modal response');
            amsDataRestServiceDeferred.resolve({
                data: 'added ams'
            });
            $scope.$digest();
            // then
            expect($modal.open).toHaveBeenCalledWith({
                templateUrl: '/main/ams-add/ams-add.tpl.html',
                controller: 'AddAmsCntl',
                animation: true
            });
            expect(amsDataRestService.addAmsData).toHaveBeenCalledWith('some modal response');
            expect($scope.amsModel.length).toEqual(1);
            expect($scope.amsModel[0]).toEqual('added ams');
        }));

    });
});