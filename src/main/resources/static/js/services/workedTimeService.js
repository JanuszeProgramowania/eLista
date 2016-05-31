/**
 * Created by Tomasz Jodko on 2016-05-31.
 */
app.factory('workedTimeFactory', ['$http', function ($http) {

    var urlBase = '/elista/workedTimes';
    var workedTimeFactory = {};

    workedTimeFactory.getWorkedTimesByUserId = function (id, callback) {
        return $http.get(urlBase + '/getByUserId/' + id).then(function (response) {
            if (response.data.error) {
                return null;
            } else {
                workedTimeFactory.returnedData = response.data;
                callback(workedTimeFactory.returnedData);
            }
        });
    };

    workedTimeFactory.getWorkedTimee = function (id, callback) {
        return $http.get(urlBase + '/getById/' + id).then(function (response) {
            if (response.data.error) {
                return null;
            } else {
                workedTimeFactory.returnedData = response.data;
                callback(workedTimeFactory.returnedData);
            }
        });
    };

    workedTimeFactory.saveWorkedTime = function (wrapper, callback) {
        return $http.post(urlBase + '/saveWorkedTime', wrapper).then(function (response) {
            if (response.data.error) {
                return null;
            } else {
                workedTimeFactory.returnedData = response.data;
                callback(workedTimeFactory.returnedData);
            }
        });
    };


    workedTimeFactory.deleteWorkedTime = function (id) {
        return $http.put(urlBase + '/deleteById/' + id);
    };
    return workedTimeFactory;
}]);