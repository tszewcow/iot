angular.module('app.main', ['ngRoute', 'app.main.templates', 'trNgGrid', 'ui.bootstrap'])
    .config(function ($routeProvider, $httpProvider) {
        'use strict';
        $routeProvider
            .when('/', {redirectTo: '/main/login'})
            
            
            .when('/main/login', {
            	templateUrl : '/main/login/login.html'
            })
            
            .when('/main/welcome', {templateUrl: 'main/welcome/welcome.html'})
            .when('/main/beacons-management', {
                templateUrl: 'main/beacons-management/beacons-management.html',
                access: { requiredLogin: true }
            })
            .when('/main/ams-management', {
                templateUrl: 'main/ams-management/ams-management.html',
                access: { requiredLogin: true }
            })
            .when('/main/locations', {
                templateUrl: 'main/locations/locations.html',
                access: { requiredLogin: true }
            })
            .when('/main/location-map', {
                templateUrl: 'main/location-map/location-map.html',
                access: { requiredLogin: true }
            })
            .when('/main/options', {
                templateUrl: 'main/options/options.html',
                access: { requiredLogin: true }
            })
            .when('/main/users-management', {
                templateUrl: 'main/users-management/users-management.html',
                access: { requiredLogin: true }
            })
            .otherwise({templateUrl: 'main/page-not-found/page-not-found.html'});

        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    });