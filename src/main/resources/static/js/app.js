var app = angular.module('app', ['ngRoute', 'smart-table', 'ui.bootstrap', 'ui.bootstrap.datetimepicker']);

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
            controller: 'AbsenceController'
        })
        .when('/schedule', {
            templateUrl: 'templates/schedule.html',
            controller: 'ScheduleController'
        })
        .when('/workedtime', {
            templateUrl: 'templates/workedtime.html',
            controller: 'WorkedTimeController'
        })
        .when('/account', {
            templateUrl: 'templates/account.html',
            controller: 'AccountController'
        })
        .when('/admin', {
            templateUrl: 'templates/admin.html',
            controller: 'AdminController'
        })
        .otherwise({redirectTo: '/'});
}]);

