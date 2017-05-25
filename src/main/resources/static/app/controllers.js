(function(angular) {
    var AppController = function($scope, Track) {
        Track.query(function(response) {
            $scope.tracks = response ? response : [];
        });

        $scope.addTrack = function(song) {
            new Track({
                song: song,
                segue: false
            }).$save(function(track) {
                $scope.tracks.push(track);
            });
            $scope.newTrack = "";
        };

        $scope.updateTrack = function(track) {
            track.$update();
        };

        $scope.deleteTrack = function(track) {
            track.$remove(function() {
                $scope.tracks.splice($scope.tracks.indexOf(track), 1);
            });
        };
    };

    AppController.$inject = ['$scope', 'Track'];
    angular.module("myApp.controllers").controller("AppController", AppController);
}(angular));