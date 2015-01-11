(function() {
    function ErrorHandlerInterceptor($q, errorService) {
        return {
            'responseError': function(response) {
                if (response.status === 400) {
                    angular.forEach(response.data, function(error) {
                        errorService.addError(error);
                    });
                }

                console.log(errorService);
                return $q.reject(response);
            }
        }
    }


    function ErrorService() {
        var errors = [];

        return {
            errors: errors,
            addError: addError,
            reset: reset
        }

        function addError(error) {
            errors.push(error);
        }

        function reset() {
            console.log('resetting errors', errors);
            errors = [];
        }
    }

    function ErrorDirective(errorService) {
        return {
            restrict: 'E',
            scope: {},
            template:
                '<div class="alert alert-danger" role="alert">' +
                    '<strong>Error </strong>' +
                    '<ul ng-repeat="error in errors">' +
                        '<li>{{error}}</li>' +
                    '</ul>' +
                    '<a ng-click="reset()" href>reset</a>' +
                '</div>',
            link: function(scope) {
                scope.errors = errorService.errors;
                scope.reset = errorService.reset;
            }
        }
    }

    function Config($httpProvider) {
        $httpProvider.interceptors.push('errorHandlerInterceptor')
    }

    angular.module('lunchinator.error', [])
        .config(['$httpProvider', Config])
        .directive('errorDirective', ['ErrorService', ErrorDirective])
        .factory('errorHandlerInterceptor', ['$q', 'ErrorService', ErrorHandlerInterceptor])
        .factory('ErrorService', ErrorService)
})();