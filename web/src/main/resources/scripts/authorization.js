(function() {
	function HttpAuthorizationRequestInterceptor() {
		return {
			'request': function(request) {
				request.headers['Authorization'] = 'USER_ID';
				return request;
			}
		}
	}
	
	function Config($httpProvider) {
		$httpProvider.interceptors.push('httpAuthorizationRequestInterceptor');
	}
	
	angular.module('lunchinator.authorization', [])
		.config(['$httpProvider', Config])
		.factory('httpAuthorizationRequestInterceptor', HttpAuthorizationRequestInterceptor)
})();