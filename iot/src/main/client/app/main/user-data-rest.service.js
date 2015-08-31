angular.module('app.main').factory('userDataRestService', function ($http, currentContextPath) {
    'use strict';

    return {
        getAllUsers: function () {
            return $http.get(currentContextPath.get() + '/services/getAllUsers');
        },
        getUser: function (username, password) {
            return $http.get(currentContextPath.get() + '/services/getUser/' + username + '/' + password);
        }
    };
});