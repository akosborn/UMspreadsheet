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
            remove: {
                method: 'DELETE'
            }
        });
    };

    TrackFactory.$inject = ['$resource'];
    angular.module('UMspreadsheet.services').factory("Track", TrackFactory);

//     trackFactory.$inject = ["$resource"];
//     serviceModule.factory("Track", trackFactory);
//
//     var showFactory = function ($resource) {
//         return $resource('/api/shows/:id', {
//             id: "@id"
//         }, {
//             get: {
//                 method: 'GET'
//             }
//         });
//     };
//
//     showFactory.$inject = ["$resource"];
//     serviceModule.factory("Show", showFactory);
//
//     var trackController = function ($scope, trackFactory) {
//         $scope.track = trackFactory.get({id: 1500});
//         $scope.tracks = trackFactory.get();
//     };
//
//     trackController.$inject = ['$scope'];
//
//     var showController = function ($scope, Show) {
//         Show.query(function (response) {
//             $scope.show =  response ? response : [];
//         });
//     };
//
//     showController.$inject = ['$scope'];
//
// // Register controller with app
//     controllerModule.controller("showController", showController);
//     controllerModule.controller("trackController", trackController);
}(angular));