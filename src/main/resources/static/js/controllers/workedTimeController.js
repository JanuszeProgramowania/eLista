/**
 * Created by Tomasz Jodko on 2016-05-31.
 */
app.controller('WorkedTimeController', ['$scope', '$rootScope', 'workedTimeFactory', '$location', function ($scope, $rootScope, workedTimeFactory, $location) {
    $scope.newWrapper = {
        user: {
            id: ''
        },
        workedTime: {
            id: '',
            start: '',
            finish: '',
            description: ''
        }
    };
    $scope.wrapper = {
        user: {
            id: ''
        },
        workedTime: {
            id: '',
            start: '',
            finish: '',
            description: ''
        }
    };


    $scope.popup1 = {
        opened: false
    };
    $scope.popup2 = {
        opened: false
    };
    $scope.popup3 = {
        opened: false
    };
    $scope.popup4 = {
        opened: false
    };

    $scope.getters = {
        startDate: function (row) {
            return new Date(row.start);
        },
        finishDate: function (row) {
            return new Date(row.finish);
        }
    }

    $scope.timeOptions = {
        showMeridian: false
    };

    $scope.open1 = function () {
        $scope.popup1.opened = true;
    };
    $scope.open2 = function () {
        $scope.popup2.opened = true;
    };
    $scope.open3 = function () {
        $scope.popup3.opened = true;
    };
    $scope.open4 = function () {
        $scope.popup4.opened = true;
    };


    $scope.saveNewWorkedTime = function () {
        $scope.newWrapper.user = {
            id: $rootScope.token.id
        };
        $scope.newWrapper.workedTime.id = 0;
        console.log($scope.newWrapper);
        workedTimeFactory.saveWorkedTime($scope.newWrapper, function (resp) {
            console.log(resp);
            $scope.userWorkedTimes.push(resp);
        });
    };

    $scope.saveWorkedTime = function () {
        $scope.wrapper.user = {
            id: $rootScope.token.id
        };
        $scope.wrapper.workedTime = {
            id: $scope.selectedWt.id,
            start: $scope.selectedWt.start,
            finish: $scope.selectedWt.finish,
            description: $scope.selectedWt.description
        };
        workedTimeFactory.saveWorkedTime($scope.wrapper, function (resp) {
            console.log(resp);
        });
    };

    $scope.deleteWorkedTime = function () {
        var id = $scope.selectedWt.id;
        workedTimeFactory.deleteWorkedTime(id);
        var index = $scope.userWorkedTimes.indexOf($scope.selectedWt);
        $scope.userWorkedTimes.splice(index, 1);
    };


    // fired when table rows are selected
    $scope.$watch('displayedCollection', function (row) {
        // get selected row
        var selectedCount = 0;
        row.filter(function (r) {
            if (r.isSelected) {
                r.start = new Date(r.start);
                r.finish = new Date(r.finish);
                console.log(r);

                $scope.selectedWt = r;

                selectedCount++;
            }
        });
        if (selectedCount > 0) {
            $('#selectedWtForm').collapse("show")
        } else {
            $('#selectedWtForm').collapse("hide")
        }
    }, true);


    if (!$rootScope.authenticated) {
        $location.path('/');    //redirect user to home.
    }
    workedTimeFactory.getWorkedTimesByUserId($rootScope.token.id, function (resp) {
        $scope.userWorkedTimes = resp;
        console.log(resp);
    });

}]);
