angular.module('app.main').factory('userDataRestService', function ($http, currentContextPath) {
    'use strict';

    return {
        getAllUsers: function () {
            return $http.get(currentContextPath.get() + 'services/getAllUsers');
        },
        getUser: function (username, password) {
            return $http.get(currentContextPath.get() + 'services/getUser/' + username + '/' + password);
        },
        addUserData: function (data) {
            return $http.post(currentContextPath.get() + 'services/addNewUser', data);
        },
        userTest: function (string) {
            return $http.get(currentContextPath.get() + 'user/'+string);
        }
    };
});