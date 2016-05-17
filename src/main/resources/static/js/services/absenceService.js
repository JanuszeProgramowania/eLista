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

    absenceFactory.getAbsence = function (id) {
        return $http.get(urlBase + '/getById/' + id).then(function (response) {
            if (response.data.error) {
                return null;
            } else {
                return response.data;
            }
        });
    };

    absenceFactory.saveAbsence = function (absence) {
        return $http.post(urlBase + '/saveAbsence', absence);
    };

    absenceFactory.deleteAbsence = function (id) {
        return $http.put(urlBase + '/deleteById/' + id);
    };
    return absenceFactory;
}]);