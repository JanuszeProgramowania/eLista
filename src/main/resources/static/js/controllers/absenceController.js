app.controller('AbsenceController', ['$scope', '$rootScope', 'absenceFactory', '$location', function ($scope, $rootScope, absenceFactory, $location) {

    if (!$rootScope.authenticated) {
        $location.path('/');    //redirect user to home.
    }
    absenceFactory.getAbsencesByUserId($rootScope.token.id, function (resp) {
        $scope.userAbsences = resp;
        console.log(resp);
    });

}]);