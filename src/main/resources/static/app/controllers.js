(function (angular) {
    var ShowController = function ($scope, $window, Show) {

        $scope.showId = $window.showId;

        Show.get({id: showId}, function (response) {
            $scope.show = response ? response : {};
        });

        $scope.updateShow = function (show) {
            // parse full url for nugsId
            if (show.nugsId.indexOf("/")) {
                var splitURL = show.nugsId.split("/");
                show.nugsId = splitURL[splitURL.length - 1];
            }

            Show.update({id: show.id}, show);
        }
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

        $scope.addTrack = function (show, set, song) {
            new Track({
                song: song,
                show: show,
                set: set
            }).$save(function (track) {
                $scope.set.tracks = $scope.set.tracks || [];
                $scope.set.tracks.push(track);
            });
            $scope.newTrack = "";
        };

        $scope.deleteTrack = function (track) {
            if (confirm('Delete ' + track.song + '?'))
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
        };

        $scope.addSet = function (show, set) {
            new Set({
                name: set,
                show: show
            }).$save(function (set) {
                $scope.show.sets.push(set);
            });
            $scope.newSet = "";
        };

        $scope.updatePosition = function (set, position) {
            set.position = position;
            Set.update({id: set.id}, set);
        };

        $scope.deleteSet = function (set) {
            if (confirm("Delete " + set.name + "?")) {
                Set.remove({id: set.id}, set, function () {
                    $scope.show.sets.splice($scope.show.sets.indexOf(set), 1);
                });
            }
        }
    };

    SetController.$inject = ['$scope', 'Set'];
    angular.module("UMspreadsheet.controllers").controller("SetController", SetController);
}(angular));
