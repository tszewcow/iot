angular.module('app.main', ['ngRoute', 'app.main.templates', 'trNgGrid', 'ui.bootstrap'])
    .config(function ($routeProvider) {
        'use strict';
        $routeProvider
            .when('/', {redirectTo: '/main/hello'})
            .when('/main/hello', {templateUrl: 'main/hello/hello.html',
            					controller :  'navigationCtrl'})
            .when('/main/welcome', {templateUrl: 'main/welcome/welcome.html'})
            .when('/main/beacons-management', {
                templateUrl: 'main/beacons-management/beacons-management.html'
            })
            .when('/main/ams-management', {
                templateUrl: 'main/ams-management/ams-management.html'
            })
            .when('/main/locations', {
                templateUrl: 'main/locations/locations.html'
            })
            .when('/main/location-map', {
                templateUrl: 'main/location-map/location-map.html'
            })
            .otherwise({templateUrl: 'main/page-not-found/page-not-found.html'});
    });