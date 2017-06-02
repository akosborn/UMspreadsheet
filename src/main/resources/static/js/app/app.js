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

    app.filter('formatType', function() {
        return function(input, $scope) {
            if (input == 'NONE')
                input = '';
            else if (input == 'JIMMYSTEWART')
                input = 'Jimmy Stewart';
            else if (input == 'LYRICALSTEW')
                input = 'Lyrical Stew';
            else if (input == 'COVER')
                input = 'Cover';
            else if (input == 'MASHUP')
                input = 'Mashup';
            else if(input == 'NEWSONG')
                input = 'New';

            return input;
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
                            var el = trackElements[i].children[0];
                            var elHeight = el.offsetHeight;
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

                    var slider = element.bootstrapSlider({
                        tooltip: 'show',
                        value: score
                    });

                    $timeout(function () {

                        var value = $scope.track.userTrackReview.score;
                        var selection = slider.parent().children().eq(0).children().eq(0).children().eq(1);
                        slider.bootstrapSlider('setValue', score);

                        var colorChange = function () {

                            value = $scope.track.userTrackReview.score;

                            if (value < 6)
                                selection.css('background', '#F3F3F3');
                            if (value >= 6 && value < 7)
                                selection.css('background', '#CD7F32');
                            if (value >= 7 && value < 8)
                                selection.css('background', '#C0C0C0');
                            if (value >= 8 && value < 9)
                            {
                                selection.css('background', '#FFD700');
                            }
                            if (value >= 9)
                                selection.css('background', '#B9F2FF');

                        };

                        colorChange();

                        element
                            .slider()
                            .on('slideStop', colorChange).data('value');
                    });
                }
            }
        });

    app.directive('createCommentModal', function () {
            return {
                link: function ($scope, element, attrs, controller) {

                    element.avgrund({
                        height: 200,
                        holderClass: 'custom',
                        showClose: true,
                        showCloseText: 'close',
                        closeByEsc: true,
                        onBlurContainer: '.container',
                        template: element.parent().next().children([0])
                    });
                }
            }
        });
}(angular));