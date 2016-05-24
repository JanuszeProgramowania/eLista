var app = angular.module('app', ['ngRoute', 'smart-table', 'ui.bootstrap']);

app.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'templates/home.html'
        })
        .when('/home', {
            templateUrl: 'templates/home.html'
        })

        .when('/absence', {
            templateUrl: 'templates/absence.html',
            controller: 'AbsenceController',
        })
        .otherwise({redirectTo: '/'});
}]);

