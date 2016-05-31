/**
 * Created by Tomasz Jodko on 2016-05-31.
 */
app.controller('AccountController', ['$scope', '$http', '$rootScope', '$location', function ($scope, $http, $rootScope, $location) {

    $scope.cpSuccess = false;

    $scope.changePassword = function () {
        $http.put('elista/users/changePassword/', $scope.newPassword).success(function () {
            $scope.cpSuccess = true;
            $('#newPassword').text(null);
            $('#confPassword').text(null);
        });

    };


    if (!$rootScope.authenticated) {
        $location.path('/');    //redirect user to home.
    }
}]);