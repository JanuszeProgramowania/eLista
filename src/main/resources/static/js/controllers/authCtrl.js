app.controller('AuthCtrl', function ($scope, $rootScope, $http, TokenStorage) {
    $rootScope.authenticated = false;
    $rootScope.admin = false;
    $rootScope.token; // For display purposes only

    $scope.init = function () {
        $http.get('/elista/users/current').success(function (user) {
            $rootScope.authenticated = false;
            $rootScope.admin = false;
            if (typeof TokenStorage.retrieve() !== 'undefined' && TokenStorage.retrieve() !== null) {
                $rootScope.authenticated = true;

                // For display purposes only
                $rootScope.token = JSON.parse(atob(TokenStorage.retrieve().split('.')[0]));
                if ($rootScope.token.authority === "ROLE_ADMIN") {
                    $rootScope.admin = true;
                }
            }
        });
    };

    $scope.login = function () {
        $http.post('/api/login', {
            email: $scope.email,
            password: $scope.password
        }).success(function (result, status, headers) {
            $rootScope.authenticated = true;
            TokenStorage.store(headers('X-AUTH-TOKEN'));

            // For display purposes only
            $rootScope.token = JSON.parse(atob(TokenStorage.retrieve().split('.')[0]));
            if ($rootScope.token.authority === "ROLE_ADMIN") {
                $rootScope.admin = true;
            }

        });
    };

    $scope.logout = function () {
        // Just clear the local storage
        TokenStorage.clear();
        $rootScope.authenticated = false;
        $rootScope.admin = false;
    };
});