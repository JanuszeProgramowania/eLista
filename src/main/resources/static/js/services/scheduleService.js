app.factory('scheduleFactory', ['$http', function ($http) {

    var urlBase = '/elista/schedules';
    var scheduleFactory = {};

    scheduleFactory.getSchedulesByUserId = function (id, callback) {
        return $http.get(urlBase + '/getByUserId/' + id).then(function (response) {
            if (response.data.error) {
                return null;
            } else {
                scheduleFactory.returnedData = response.data;
                callback(scheduleFactory.returnedData);
            }
        });
    };

    scheduleFactory.getSchedule = function (id, callback) {
        return $http.get(urlBase + '/getById/' + id).then(function (response) {
            if (response.data.error) {
                return null;
            } else {
                scheduleFactory.returnedData = response.data;
                callback(scheduleFactory.returnedData);
            }
        });
    };

    scheduleFactory.saveSchedule = function (wrapper, callback) {
        return $http.post(urlBase + '/saveSchedule', wrapper).then(function (response) {
            if (response.data.error) {
                return null;
            } else {
                scheduleFactory.returnedData = response.data;
                callback(scheduleFactory.returnedData);
            }
        });
    };


    scheduleFactory.deleteSchedule = function (id) {
        return $http.put(urlBase + '/deleteById/' + id);
    };
    return scheduleFactory;
}]);