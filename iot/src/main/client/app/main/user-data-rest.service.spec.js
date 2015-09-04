describe('User data rest service tests', function () {
    'use strict';

    beforeEach(module('app.main'));
    beforeEach(inject(function (_userDataRestService_) {
    	userDataRestService = _userDataRestService_;
    }));

    var userDataRestService;

    describe('userDataRestService tests', function () {
        it('should call $http.get when retrieving users data', inject(function ($http, currentContextPath) {
            // given
            spyOn(currentContextPath, 'get').and.returnValue('url-prefix/');
            spyOn($http, 'get');
            // when
            userDataRestService.getAllUsers();
            // then
            expect($http.get).toHaveBeenCalledWith('url-prefix/services/getAllUsers');
        }));
    });

    it('should call $http.get when retrieving user data', inject(function ($http, currentContextPath) {
        // given
        var email = 'email';
        var pswd = 'pswd';
        spyOn(currentContextPath, 'get').and.returnValue('url-prefix/');
        spyOn($http, 'get');
        // when
        userDataRestService.getUser(email, pswd);
        // then
        expect($http.get).toHaveBeenCalledWith('url-prefix/services/getUser/' + email + '/' + pswd);
    }));

    it('should call $http.post when adding new user', inject(function ($http, currentContextPath) {
        // given
        var user = {
            email: 'email'
        };
        spyOn(currentContextPath, 'get').and.returnValue('url-prefix/');
        spyOn($http, 'post');
        // when
        userDataRestService.addUserData(user);
        // then
        expect($http.post).toHaveBeenCalledWith('url-prefix/services/addNewUser', user);
    }));

    it('should call $http.delete when deleting user', inject(function ($http, currentContextPath) {
        // given
        var id = 40982;
        spyOn(currentContextPath, 'get').and.returnValue('url-prefix/');
        spyOn($http, 'delete');
        // when
        userDataRestService.deleteUserData(id);
        // then
        expect($http.delete).toHaveBeenCalledWith('url-prefix/services/deleteUser/' + id);
    }));

    it('should call $http.put when updating user', inject(function ($http, currentContextPath) {
        // given
        var user = {
            email: 'email'
        };
        spyOn(currentContextPath, 'get').and.returnValue('url-prefix/');
        spyOn($http, 'put');
        // when
        userDataRestService.updateUserData(user);
        // then
        expect($http.put).toHaveBeenCalledWith('url-prefix/services/updateUser', user);
    }));
});