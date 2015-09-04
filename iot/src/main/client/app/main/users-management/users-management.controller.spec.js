describe('Users management tests', function () {
    'use strict';

    var $scope;

    beforeEach(module('app.main'));

    beforeEach(inject(function ($controller, $rootScope, userDataRestService, $q) {
        // given
        var deferred = $q.defer();
        $scope = $rootScope.$new();
        spyOn(userDataRestService, 'getAllUsers').and.returnValue(deferred.promise);
        deferred.resolve({
            data: [
                {
                	userName: 'user-1',
                    id: 1
                },
                {
                	userName: 'user-2',
                    id: 2
                }
            ]
        });
        // when
        $controller('UsersManagementCntl', {
            $scope: $scope,
            globalSpinner: {
                decorateCallOfFunctionReturningPromise: function (func) {
                    func()
                }
            }
        });
        $scope.$digest();
    }));


    describe('scope model initialization', function () {
        it('should initialize model', inject(function ($q, userDataRestService) {
            // given when then
            expect(userDataRestService.getAllUsers).toHaveBeenCalled();
            expect($scope.users.length).toEqual(2);
            expect($scope.users[0].userName).toEqual('user-1');
            expect($scope.users[1].userName).toEqual('user-2');
            expect($scope.mySelectedItems.length).toEqual(0);
        }));
    });

    describe('scope functions test', function () {
        it('should call modal on addUser function call and add user on success', inject(function ($modal, $q, userDataRestService) {
            // given
            var modalDeferred = $q.defer(),
                userDataServiceDeferred = $q.defer();
            spyOn($modal, 'open').and.returnValue({
                result: modalDeferred.promise
            });
            spyOn(userDataRestService, 'addUserData').and.returnValue(userDataServiceDeferred.promise);
            // when
            $scope.addUser();
            modalDeferred.resolve();
            userDataServiceDeferred.resolve({
                data: {
                	userName: 'new user'
                }
            });
            $scope.$digest();
            // then
            expect($modal.open).toHaveBeenCalledWith({
                templateUrl: '/main/user-add/user-add.tpl.html',
                controller: 'UserAddCntl',
                animation: true,
                size: 'md'
            });
            expect($scope.users.length).toEqual(3);
            expect($scope.users[2].userName).toEqual('new user');
        }));

        it('should delete selected beacon', inject(function (userDataRestService) {
            // given
            spyOn(userDataRestService, 'deleteUserData');
            $scope.mySelectedItems.push($scope.users[0]);
            // when
            $scope.deleteUser();
            // then
            expect(userDataRestService.deleteUserData).toHaveBeenCalledWith($scope.mySelectedItems[0].id);
            expect($scope.users.length).toEqual(1);
            expect($scope.users[0]).toEqual({
                userName: 'user-2',
                id: 2
            });

        }));

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

        it('should call $modal.show when edit user function is called', inject(function ($modal) {
            // given
            $scope.mySelectedItems.push('some entry');
            spyOn($modal, 'open').and.returnValue({
                result: {
                    then: angular.noop
                }
            });
            // when
            $scope.editUser();
            // then
            expect($modal.open).toHaveBeenCalledWith({
                templateUrl: '/main/user-edit/user-edit.tpl.html',
                controller: 'UserEditCntl',
                animation: true,
                size: 'md',
                resolve: {
                    user: jasmine.any(Function)
                }
            });
            expect($modal.open.calls.argsFor(0)[0].resolve.user()).toEqual('some entry');
        }));

        it('should call $modal.show when add user function is called', inject(function ($modal, $q, userDataRestService) {
            // given
            var modalDeferred = $q.defer(),
                userDataRestServiceDeferred = $q.defer();
            spyOn($modal, 'open').and.returnValue({
                result: modalDeferred.promise
            });
            spyOn(userDataRestService, 'addUserData').and.returnValue(userDataRestServiceDeferred.promise);
            // when
            $scope.addUser();
            modalDeferred.resolve('some modal response');
            userDataRestServiceDeferred.resolve({
                data: 'added user'
            });
            $scope.$digest();
            // then
            expect($modal.open).toHaveBeenCalledWith({
                templateUrl: '/main/user-add/user-add.tpl.html',
                controller: 'UserAddCntl',
                animation: true,
                size: 'md'
            });
            expect(userDataRestService.addUserData).toHaveBeenCalledWith('some modal response');
            expect($scope.users.length).toEqual(3);
            expect($scope.users[0]).toEqual({
                userName: 'user-1',
                id: 1
            });
        }));
    });
});