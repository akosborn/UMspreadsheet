(function (angular) {
    angular.module("UMspreadsheet.controllers", []);
    angular.module("UMspreadsheet.services", []);
    var app = angular.module("UMspreadsheet", ["ngResource", "UMspreadsheet.controllers", "UMspreadsheet.services"]);

    app.directive('afterTracksRender', ['Track', function (Track) {
            return {
                link: function ($scope, element, attrs, controller) {

                    var track = $scope.track;

                    element.durationPicker({
                        showSeconds: true,
                        showDays: false,

                        onChanged: function (newVal) {

                            if (newVal != 0)
                            {
                                track.length = newVal;
                                $scope.$apply();
                                Track.update({id: track.id}, track);
                            }
                        }
                    });

                    var minutes = Math.floor(track.length / 60);
                    var seconds = track.length - (minutes * 60);

                    element.parent([0]).children([2]).children([2]).children([2])[4].value = minutes;
                    element.parent([0]).children([2]).children([2]).children([2])[6].value = seconds;

                    var hours = element.parent([0]).children([2]).children([2])[1];
                    hours.classList.add('hidden');
                }
            }
        }]);
}(angular));