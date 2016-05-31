/**
 * Created by Tomasz Jodko on 2016-05-31.
 */
app.controller('AdminController', ['$scope', '$rootScope', 'userFactory', '$location', function ($scope, $rootScope, userFactory, $location) {

    $scope.newUser = {
        authority: "ROLE_USER"
    };
    $scope.saveNewUser = function () {
        $scope.newUser.id = 0;

        userFactory.saveUser($scope.newUser, function (resp) {
            console.log(resp);
            $scope.users.push(resp);
        });
    };

    $scope.saveUser = function () {
        userFactory.saveUser($scope.selectedUser, function (resp) {
            console.log(resp);
        });
    };

    $scope.deleteUser = function () {
        var id = $scope.selectedUser.id;
        userFactory.deleteAbsence(id);
        var index = $scope.users.indexOf($scope.selectedUser);
        $scope.users.splice(index, 1);
    };

    // fired when table rows are selected
    $scope.$watch('displayedCollection', function (row) {
        // get selected row
        var selectedCount = 0;
        row.filter(function (r) {
            if (r.isSelected) {
                console.log(r);
                $scope.selectedUser = r;
                selectedCount++;
            }
        });
        if (selectedCount > 0) {
            $('#selectedUserForm').collapse("show")
        } else {
            $('#selectedUserForm').collapse("hide")
        }
    }, true);

    userFactory.getUsers(function (resp) {
        $scope.users = resp;
        console.log(resp);
    });

    if (!$rootScope.authenticated && $rootScope.admin === false) {
        $location.path('/');    //redirect user to home.
    }
}]);