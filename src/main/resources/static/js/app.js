var app = angular.module('eLista', ['ngRoute', 'authControllers']);

app.config(function ($routeProvider) {
    $routeProvider

        .otherwise({
            redirectTo: '/login'
        });
});