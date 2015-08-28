angular.module('app.main').factory('amsDataRestService', function ($http, currentContextPath) {
    'use strict';

    return {
        getAmsData: function () {
            return $http.get(currentContextPath.get() + 'services/automaticmobileset');

        },
        getAmsDataOnGivenFloor: function (building, floor) {
            return $http.get(currentContextPath.get() + 'services/automaticmobileset/' + building + '/' + floor);
        },
        addAmsData: function (data) {
            return $http.post(currentContextPath.get() + 'services/automaticmobileset', data);
        },
        deleteAmsData: function (id) {
            return $http.delete(currentContextPath.get() + 'services/automaticmobileset/' + id);
        },
        updateAmsData: function (data) {
            return $http.put(currentContextPath.get() + 'services/automaticmobileset', data);
        }
    };
});