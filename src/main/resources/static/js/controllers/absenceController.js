app.controller('AbsenceController', ['$scope', '$rootScope', 'absenceFactory', '$location', function ($scope, $rootScope, absenceFactory, $location) {
    $scope.newWrapper = {
        user: {
            id: ''
        },
        absence: {
            id: '',
            date: '',
            hours: '',
            type: ''
        }
    };
    $scope.wrapper = {
        user: {
            id: ''
        },
        absence: {
            id: '',
            date: '',
            hours: '',
            type: ''
        }
    };


    $scope.popup1 = {
        opened: false
    };
    $scope.popup2 = {
        opened: false
    };
    $scope.dateOptions = {
        formatYear: 'yy',
        startingDay: 1
    };
    $scope.open1 = function () {
        $scope.popup1.opened = true;
    };
    $scope.open2 = function () {
        $scope.popup2.opened = true;
    };


    $scope.saveNewAbsence = function () {
        $scope.newWrapper.user = {
            id: $rootScope.token.id
        };
        $scope.newWrapper.absence.id = 0;
        console.log($scope.newWrapper);
        absenceFactory.saveAbsence($scope.newWrapper, function (resp) {
            console.log(resp);
            $scope.userAbsences.push(resp);
        });
    };

    $scope.saveAbsence = function () {
        $scope.wrapper.user = {
            id: $rootScope.token.id
        };
        $scope.wrapper.absence = {
            id: $scope.selectedAbs.id,
            date: $scope.selectedAbs.date,
            hours: $scope.selectedAbs.hours,
            type: $scope.selectedAbs.type
        };
        absenceFactory.saveAbsence($scope.wrapper, function (resp) {
            console.log(resp);
        });
    };

    $scope.deleteAbsence = function () {
        var id = $scope.selectedAbs.id;
        absenceFactory.deleteAbsence(id);
        var index = $scope.userAbsences.indexOf($scope.selectedAbs);
        $scope.userAbsences.splice(index, 1);
    };


    // fired when table rows are selected
    $scope.$watch('displayedCollection', function (row) {
        // get selected row
        var selectedCount = 0;
        row.filter(function (r) {
            if (r.isSelected) {
                console.log(r);
                $scope.selectedAbs = r;
                selectedCount++;
            }
        });
        if (selectedCount > 0) {
            $('#selectedAbsForm').collapse("show")
        } else {
            $('#selectedAbsForm').collapse("hide")
        }
    }, true);


    if (!$rootScope.authenticated) {
        $location.path('/');    //redirect user to home.
    }
    absenceFactory.getAbsencesByUserId($rootScope.token.id, function (resp) {
        $scope.userAbsences = resp;
        console.log(resp);
    });

}]);