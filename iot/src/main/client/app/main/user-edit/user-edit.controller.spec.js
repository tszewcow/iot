describe('Edit user tests', function () {
    'use strict';

    beforeEach(module('app.main'));

    var $scope;
    var modalInstanceMock = {
        close: jasmine.createSpy(),
        dismiss: jasmine.createSpy()
    };
    var userMock = {
            firstName: 'Jan',
            lastName: 'Kowalski',
            email: 'jkow',
            password: 'pswd',
            passwordConfirmation: 'pswd',
        	salt: 'salt',
    		createdOn: 'now',
    		lastLogin: 'never',
    		userRole: 'user',
    		active: true
    };

    beforeEach(inject(function ($controller, $rootScope, $compile) {
        $scope = $rootScope.$new();
        var element = angular.element('<form name="userEditForm" />');
        $compile(element)($scope);

        $controller('UserEditCntl', {
            $scope: $scope,
            $modalInstance: modalInstanceMock,
            user: userMock
        });
    }));

    describe('testing modal controls', function () {
        it('ok should close dialog and returned modified data', function () {
            // given when
            $scope.ok();
            // then
            expect(modalInstanceMock.close).toHaveBeenCalledWith(userMock);
        });

        it('cancel should close modal without returning data', function () {
            //given when
            $scope.cancel();
            //then
            expect(modalInstanceMock.dismiss).toHaveBeenCalledWith('cancel');
        });
    });
});