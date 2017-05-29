(function (angular) {
    var ShowController = function ($scope, $window, Show) {

        $scope.showId = $window.showId;

        Show.get({id: showId}, function (response) {
            $scope.show = response ? response : {};
        })
    };

    ShowController.$inject = ['$scope', '$window', 'Show'];
    angular.module("UMspreadsheet.controllers").controller("ShowController", ShowController);


    var TrackController = function ($scope, Track) {

        // Track.get({id: 6864}, function (response) {
        //     $scope.track = response ? response : [];
        // })

        $scope.updateTrack = function (track) {
            Track.update({id: track.id}, track);
        };

        $scope.updateSetPosition = function (track, setPosition) {
            track.setPosition = setPosition;
            Track.update({id: track.id}, track);
        };

        $scope.addTrack = function (show, set, track) {
            new Track({
                song: track.song,
                show: show,
                set: set
            }).$save(function (track) {
                $scope.set.tracks.push(track);
            });
            $scope.newTrack = "";
        };

        $scope.deleteTrack = function (track) {
            Track.remove({id: track.id}, track, function () {
                $scope.set.tracks.splice($scope.set.tracks.indexOf(track), 1)
            })
        };
    };

    TrackController.$inject = ['$scope', 'Track'];
    angular.module("UMspreadsheet.controllers").controller("TrackController", TrackController);

    var SetController = function ($scope, Set) {

        $scope.updateSet = function (set) {
            Set.update({id: set.id}, set);
        }

    };

    SetController.$inject = ['$scope', 'Set'];
    angular.module("UMspreadsheet.controllers").controller("SetController", SetController);
}(angular));
