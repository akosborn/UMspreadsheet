(function (angular) {
    var ShowController = function ($scope, Show) {

        Show.get({id: 401}, function (response) {
            $scope.show = response ? response : {};
        })

        // Show.query(function (response) {
        //     $scope.shows = response ? response : [];
        // });
    };

    ShowController.$inject = ['$scope', 'Show'];
    angular.module("UMspreadsheet.controllers").controller("ShowController", ShowController);


    var TrackController = function ($scope, Track) {

        Track.get({id: 6864}, function (response) {
            $scope.track = response ? response : [];
        })
    };

    TrackController.$inject = ['$scope', 'Track'];
    angular.module("UMspreadsheet.controllers").controller("TrackController", TrackController);
}(angular));
