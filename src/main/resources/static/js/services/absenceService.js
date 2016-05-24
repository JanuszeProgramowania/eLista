app.factory('absenceFactory', ['$http', function ($http) {

    var urlBase = '/elista/absences';
    var absenceFactory = {};

    absenceFactory.getAbsencesByUserId = function (id, callback) {
        return $http.get(urlBase + '/getByUserId/' + id).then(function (response) {
            if (response.data.error) {
                return null;
            } else {
                absenceFactory.returnedData = response.data;
                callback(absenceFactory.returnedData);
            }
        });
    };

    absenceFactory.getAbsence = function (id, callback) {
        return $http.get(urlBase + '/getById/' + id).then(function (response) {
            if (response.data.error) {
                return null;
            } else {
                absenceFactory.returnedData = response.data;
                callback(absenceFactory.returnedData);
            }
        });
    };

    absenceFactory.saveAbsence = function (wrapper, callback) {
        return $http.post(urlBase + '/saveAbsence', wrapper).then(function (response) {
            if (response.data.error) {
                return null;
            } else {
                absenceFactory.returnedData = response.data;
                callback(absenceFactory.returnedData);
            }
        });
    };


    absenceFactory.deleteAbsence = function (id) {
        return $http.put(urlBase + '/deleteById/' + id);
    };
    return absenceFactory;
}]);