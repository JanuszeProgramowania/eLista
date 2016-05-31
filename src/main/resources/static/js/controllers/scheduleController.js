/**
 * Created by Tomasz Jodko on 2016-05-25.
 */
app.controller('ScheduleController', ['$scope', '$rootScope', 'scheduleFactory', '$location', function ($scope, $rootScope, scheduleFactory, $location) {
    $scope.newWrapper = {
        user: {
            id: ''
        },
        schedule: {
            id: '',
            dayOfTheWeek: '',
            start: '',
            finish: ''
        }
    };
    $scope.wrapper = {
        user: {
            id: ''
        },
        schedule: {
            id: '',
            dayOfTheWeek: '',
            start: '',
            finish: ''
        }
    };

    $scope.timeOptions = {
        showMeridian: false
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

    $scope.getters = {
        startDate: function (row) {
            return new Date(row.start);
        },
        finishDate: function (row) {
            return new Date(row.finish);
        }
    }

    $scope.saveNewSchedule = function () {
        $scope.newWrapper.user = {
            id: $rootScope.token.id
        };
        $scope.newWrapper.schedule.id = 0;
        console.log($scope.newWrapper);
        scheduleFactory.saveSchedule($scope.newWrapper, function (resp) {
            console.log(resp);
            $scope.userSchedules.push(resp);
        });
    };

    $scope.saveSchedule = function () {
        $scope.wrapper.user = {
            id: $rootScope.token.id
        };
        $scope.wrapper.schedule = {
            id: $scope.selectedSch.id,
            dayOfTheWeek: $scope.selectedSch.dayOfTheWeek,
            start: $scope.selectedSch.start,
            finish: $scope.selectedSch.finish
        };
        scheduleFactory.saveSchedule($scope.wrapper, function (resp) {
            console.log(resp);
        });
    };

    $scope.deleteSchedule = function () {
        var id = $scope.selectedSch.id;
        scheduleFactory.deleteSchedule(id);
        var index = $scope.userSchedules.indexOf($scope.selectedSch);
        $scope.userSchedules.splice(index, 1);
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

                $scope.selectedSch = r;

                selectedCount++;
            }
        });
        if (selectedCount > 0) {
            $('#selectedSchForm').collapse("show")
        } else {
            $('#selectedSchForm').collapse("hide")
        }
    }, true);


    if (!$rootScope.authenticated) {
        $location.path('/');    //redirect user to home.
    }
    scheduleFactory.getSchedulesByUserId($rootScope.token.id, function (resp) {
        $scope.userSchedules = resp;
        console.log(resp);
    });

}]);
