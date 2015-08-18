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
            .when('/main/management-beacons', {
                templateUrl: 'main/html/management-beacons.html'
            })
            .when('/main/list-of-ams', {
                templateUrl: 'main/html/list-of-ams.html'
            })
            .when('/main/location-map', {
                templateUrl: 'main/html/location-map.html'
            })
            .otherwise({
                templateUrl: 'main/html/page-not-found.html'
            });
    });