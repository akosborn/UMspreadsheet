(function (angular) {
    var ShowController = function ($scope, $window, Show) {

        $scope.showId = $window.showId;

        Show.get({id: showId}, function (response) {
            $scope.show = response ? response : {};
        })

        // Show.query(function (response) {
        //     $scope.shows = response ? response : [];
        // });
    };

    ShowController.$inject = ['$scope', '$window', 'Show'];
    angular.module("UMspreadsheet.controllers").controller("ShowController", ShowController);


    var TrackController = function ($scope, Track) {

        // Track.get({id: 6864}, function (response) {
        //     $scope.track = response ? response : [];
        // })

        $scope.updateTrack = function (track) {
            console.log(track);
            Track.update({id: track.id}, track);
            console.log(track);
        }
    };

    TrackController.$inject = ['$scope', 'Track'];
    angular.module("UMspreadsheet.controllers").controller("TrackController", TrackController);
}(angular));
