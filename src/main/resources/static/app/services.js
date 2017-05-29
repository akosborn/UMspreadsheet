(function (angular) {
    var ShowFactory =  function ($resource) {
        var data = $resource('/api/shows/:id', {
            id: '@id'
        }, {
            update: {
                method: 'PUT'
            }
        });

        return data;
    };

    ShowFactory.$inject = ['$resource'];
    angular.module('UMspreadsheet.services').factory("Show", ShowFactory);


    var TrackFactory = function ($resource) {
        return $resource('/api/tracks/:id', null,
            {
            'update': {
                method: 'PUT'
            },
            'remove': {
                method: 'DELETE'
            }
        });
    };

    TrackFactory.$inject = ['$resource'];
    angular.module('UMspreadsheet.services').factory("Track", TrackFactory);

    var SetFactory = function ($resource) {
        return $resource('/api/sets/:id', null,
            {
            'update': {
                method: 'PUT'
            },
            remove: {
                method: 'DELETE'
            }
        });
    };

    SetFactory.$inject = ['$resource'];
    angular.module('UMspreadsheet.services').factory("Set", SetFactory);


}(angular));