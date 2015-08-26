angular.module('app', ['ngRoute',  'app.main', 'oasp.oaspUi.spinner'])
    .config(function ($locationProvider) {
        'use strict';
        $locationProvider.html5Mode(false);
    });
