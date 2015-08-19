angular.module('app.main', ['ngRoute', 'app.main.templates', 'trNgGrid', 'ui.bootstrap'])
    .config(function ($routeProvider) {
        'use strict';
        $routeProvider
            .when('/', {
                redirectTo: '/main/welcome'
            })
            .when('/main/welcome', {
                templateUrl: 'main/html/welcome.html'
            })
            .when('/main/beacons-management', {
                templateUrl: 'main/html/beacons-management.html'
            })
            .when('/main/ams-management', {
                templateUrl: 'main/html/ams-management.html'
            })
            .when('/main/location-map', {
                templateUrl: 'main/html/location-map.html'
            })
            .otherwise({
                templateUrl: 'main/html/page-not-found.html'
            });
    });