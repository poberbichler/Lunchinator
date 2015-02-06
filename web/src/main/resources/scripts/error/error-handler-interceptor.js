(function() {
	function ErrorHandlerInterceptor($q, errorService) {
        return {
            'responseError': function(response) {
                if (response.status === 400) {
					errorService.reset();
                    angular.forEach(response.data, function(error) {
                        errorService.addError(error);
                    });
                }

                if (response.status === 500) {
                    errorService.reset();
                    errorService.addError({message: 'Something went wrong...'});
                }

                return $q.reject(response);
            }
        }
    }
	
	function Config($httpProvider) {
        $httpProvider.interceptors.push('errorHandlerInterceptor')
    }
	
	angular.module('lunchinator.error')
    	.config(['$httpProvider', Config])
    	.factory('errorHandlerInterceptor', ['$q', 'ErrorService', ErrorHandlerInterceptor])
})();
