angular.module('app.main', ['ngRoute', 'app.main.templates', 'trNgGrid', 'ui.bootstrap'])
    .config(function ($routeProvider) {
        'use strict';
        $routeProvider
            .when('/', {redirectTo: '/main/welcome'})
            .when('/main/welcome', {templateUrl: 'main/welcome/welcome.html'})
            .when('/main/beacons-management', {
                templateUrl: 'main/beacons-management.html'
            })
            .when('/main/ams-management', {
                templateUrl: 'main/ams-management.html'
            })
            .when('/main/location-map', {
                templateUrl: 'main/location-map.html'
            })
            .otherwise({templateUrl: 'main/page-not-found/page-not-found.html'});
    });