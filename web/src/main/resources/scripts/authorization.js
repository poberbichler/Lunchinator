(function() {
	function HttpAuthorizationRequestInterceptor() {
		return {
			request: function(config) {
				config.headers['Authorization'] = 'USER_ID';
				return config;
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