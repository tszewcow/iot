describe('Ams management tests', function () {
    'use strict';

    var $scope;
    beforeEach(module('app.main'));
    beforeEach(module('oasp.oaspUi.spinner'));
    beforeEach(inject(function ($controller, $rootScope, amsDataRestService, floorLocationRestService, $q) {
        var deferred = $q.defer();
        $scope = $rootScope.$new();
        spyOn(amsDataRestService, 'getAmsData').and.returnValue(deferred.promise);
        deferred.resolve({
            data: [
                {
                    project: 'test001',
                    id: 1
                },
                {
                    project: 'test002',
                    id: 2
                }
            ]
        });
        var deferredFloors = $q.defer();
        spyOn(floorLocationRestService, 'getFloorLocations').and.returnValue(deferredFloors.promise);
        deferredFloors.resolve({
            data: []
        });
        $controller('AmsManagementCntl', {
            $scope: $scope,
            globalSpinner: {
                decorateCallOfFunctionReturningPromise: function (func) {
                    func();
                }
            }
        });
        $scope.$digest();
    }));

    describe('data load on dialog start tests', function () {
        it('should get ams data on dialog lunch', inject(function (globalSpinner, amsDataRestService, $controller) {
            // given
            // when
            $controller('AmsManagementCntl', {
                $scope: $scope,
                globalSpinner: globalSpinner
            });
            $scope.$digest();

            // then
            expect(amsDataRestService.getAmsData).toHaveBeenCalled();
            expect($scope.amsModel.length).toEqual(2);
        }));

    });

    describe('scope model initialization', function () {
        it('should initialize model', function () {
            // given when then
            expect($scope.amsModel.length).toEqual(2);
            expect(angular.isDefined($scope.mySelectedItems)).toBeTruthy();
        });
    });

    describe('scope functions test', function () {

        it('should delete selected ams', function () {
            // given
            $scope.mySelectedItems.push($scope.amsModel[0]);
            // when
            $scope.deleteAms();
            // then
            expect($scope.amsModel.length).toEqual(1);
            expect($scope.amsModel[0].project).toEqual('test002');
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
                size: 'lg',
                resolve: {
                    ams: jasmine.any(Function),
                    allBuildings: jasmine.any(Function)
                }
            });
            expect($modal.open.calls.argsFor(0)[0].resolve.ams()).toEqual('some entry');
        }));

        it('should call $modal.show when add ams function is called', inject(function ($modal, $q, amsDataRestService) {
            // given
            var initialModelSize = $scope.amsModel.length;
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
                animation: true,
                size: 'lg',
                resolve: {
                    allBuildings: jasmine.any(Function)
                }
            });
            expect(amsDataRestService.addAmsData).toHaveBeenCalledWith('some modal response');
            expect($scope.amsModel.length).toEqual(initialModelSize + 1);
            expect($scope.amsModel[2]).toEqual('added ams');
        }));

        it('should call service checking NOT available floor', inject(function ($q, floorAvailabilityService) {
            // given
            var building = 'MT2';
            var floor = 3;
            spyOn(floorAvailabilityService, 'checkAvailability').and.returnValue(false);
            // when
            expect($scope.checkAvailability(building, floor)).toBeFalsy();
            // then
            expect(floorAvailabilityService.checkAvailability).toHaveBeenCalledWith(building, floor);
        }));

        it('should call service checking available floor', inject(function ($q, floorAvailabilityService) {
            // given
            var building = 'MT2';
            var floor = 3;
            spyOn(floorAvailabilityService, 'checkAvailability').and.returnValue(true);
            // when
            expect($scope.checkAvailability(building, floor)).toBeTruthy();
            // then
            expect(floorAvailabilityService.checkAvailability).toHaveBeenCalledWith(building, floor);
        }));

        it('should show passwords column', inject(function () {
            // given
            $scope.fields = ['project', 'guardian', 'building', 'floor', 'room', 'xAutomaticMobileSet', 'yAutomaticMobileSet', 'macAutomaticMobileSet', 'ipAutomaticMobileSet', 'isActual', 'userAutomaticMobileSet'];
            // when
            $scope.showHidePass();
            // then
            expect($scope.showHidePass()).toEqual(['passwordAutomaticMobileSet']);

        }));

        it('should hide passwords column', inject(function () {
            // given
            $scope.fields = ['project', 'guardian', 'building', 'floor', 'room', 'xAutomaticMobileSet', 'yAutomaticMobileSet', 'macAutomaticMobileSet', 'ipAutomaticMobileSet', 'isActual', 'userAutomaticMobileSet', 'passwordAutomaticMobileSet'];
            // when
            $scope.showHidePass();
            // then
            expect($scope.showHidePass()).toEqual(12);
        }));


    });
});