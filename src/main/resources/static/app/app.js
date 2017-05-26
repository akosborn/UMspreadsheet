(function (angular) {
    angular.module("UMspreadsheet.controllers", []);
    angular.module("UMspreadsheet.services", []);
    angular.module("UMspreadsheet", ["ngResource", "UMspreadsheet.controllers", "UMspreadsheet.services"]);
}(angular));