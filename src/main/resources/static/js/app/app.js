(function (angular) {
    angular.module("UMspreadsheet.controllers", []);
    angular.module("UMspreadsheet.services", []);
    var app = angular.module("UMspreadsheet", ["ngResource", "UMspreadsheet.controllers", "UMspreadsheet.services"]);

    app.filter('secondsToMinSec', function ($filter) {
       return function (seconds) {
           return $filter('date')(new Date(0, 0, 0).setSeconds(seconds), 'm:ss');
       };
    });

    app.filter('capitalize', function() {
        return function(input, $scope) {
            if (input!=null)
                input = input.toLowerCase();
            return input.substring(0,1).toUpperCase() + input.substring(1);
        }
    });

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

    app.directive('equalHeights', function ($timeout) {
            return {
                link: function ($scope, element, attrs, controller) {

                    $timeout(function () {
                        var trackElements = element.find(".track-div");

                        var tallestBox = 0;

                        for (var i = 0; i < trackElements.length; i++)
                        {
                            var elHeight = trackElements[i].children[0].offsetHeight;
                            if (elHeight > tallestBox)
                            {
                                tallestBox = elHeight;
                            }
                        }


                        for (var x = 0; x < trackElements.length; x++)
                        {
                            trackElements[x].children[0].style.height = tallestBox + "px";
                        }
                    });
                }
            }
        });

    app.directive('createSlider', function ($timeout) {
            return {
                link: function ($scope, element, attrs, controller) {

                    var score = 0;
                    if ($scope.track.userTrackReview.score != null)
                    {
                        score = $scope.track.userTrackReview.score;
                    }

                    $timeout(function () {

                        var slider = element.bootstrapSlider({
                            tooltip: 'always',
                            value: score
                        });

                        slider.on("slideStop", function (value) {
                            // $scope.track.userTrackReview.score = value;
                            // $scope.apply();
                        });
                    });
                }
            }
        });
}(angular));