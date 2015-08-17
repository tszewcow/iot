angular.module('app.component3', ['ngRoute', 'app.component3.templates'])
    .config(function ($routeProvider) {
        'use strict';
        $routeProvider.when('/component-3/dialog-c', {templateUrl: 'component-3/html/dialog-c.html'});
    });
