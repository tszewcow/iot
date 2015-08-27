describe('Add ams tests', function () {
    'use strict';

    beforeEach(module('app.main'));

    var $scope;
    var modalInstanceMock = {
        close: jasmine.createSpy(),
        dismiss: jasmine.createSpy()
    };

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
                macAutomaticMobileSet: ''
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