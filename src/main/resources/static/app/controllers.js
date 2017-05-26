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
}(angular));
