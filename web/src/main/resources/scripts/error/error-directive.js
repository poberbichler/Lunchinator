(function() {
    function ErrorDirective(errorService) {
        return {
            restrict: 'E',
            scope: {},
            template:
                '<div id="error-directive" class="alert alert-danger" role="alert" ng-show="errors.length !== 0">' +
                '	<button ng-click="reset()" type="button" class="close" aria-label="Close"><span aria-hidden="true">&times;</span></button>' +
                '		<p ng-repeat="error in errors">' +
                '			<strong>Error! </strong>' +
                '			{{::error.field}} {{::error.message}}.' +
                '		</p>' +
                '</div>',
            link: function(scope) {
                scope.errors = errorService.errors;
                scope.reset = errorService.reset;
            }
        }
    }
    
    angular.module('lunchinator.error')
    	.directive('errorDirective', ['ErrorService', ErrorDirective])
})();