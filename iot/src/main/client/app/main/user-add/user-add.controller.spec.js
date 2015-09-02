describe('Add user tests', function () {
    'use strict';

    beforeEach(module('app.main'));

    var $scope;
    var modalInstanceMock = {
        close: jasmine.createSpy(),
        dismiss: jasmine.createSpy()
    };

    beforeEach(inject(function ($controller, $rootScope, $compile) {
        $scope = $rootScope.$new();
        var element = angular.element('<form name="userAddForm"/>');
        $compile(element)($scope);

        $controller('UserAddCntl', {
            $scope: $scope,
            $modalInstance: modalInstanceMock
        });
    }));


    describe('testing modal controls', function () {
        it('ok should close dialog and return new user data', function () {
            // given when
            $scope.ok();
            // then
            expect(modalInstanceMock.close).toHaveBeenCalledWith({
            	id: null,
                firstName: '',
                lastName: '',
                userName: '',
                password: '',
                passwordConfirmation: '',
            	salt: '',
        		createdOn: null,
        		lastLogin: null,
        		active: true
            });
        });

        it('cancel should close modal without returning data', function () {
            //given when
            $scope.cancel();
            //then
            expect(modalInstanceMock.dismiss).toHaveBeenCalledWith('cancel');
        });
    });

});