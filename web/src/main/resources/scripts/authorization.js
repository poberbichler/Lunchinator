(function() {
	function HttpAuthorizationRequestInterceptor(authorizationService) {
		return {
			'request': function(request) {
				request.headers['Authorization'] = authorizationService.getCurrentUser().name;
				return request;
			}
		}
	}
	
	function Config($httpProvider) {
		$httpProvider.interceptors.push('httpAuthorizationRequestInterceptor');
	}
	
	function AuthorizationService() {
		var userList = [{name: 'poberbichler'}, {name: 'maxmustermann'}];
		var currentUserIndex = 1;

		return {
			userList: userList,
			getCurrentUser: getCurrentUser,
			setCurrentUser: setCurrentUser,
			generateStorageKey: generateStorageKey
		}
		
		function getCurrentUser() {
			return userList[currentUserIndex];
		}
		
		function setCurrentUser(user) {
			currentUserIndex = userList.indexOf(user);
		}
		
		function generateStorageKey(id) {
			return getCurrentUser().name + '_' + id;
		}
	}
	
	angular.module('lunchinator.authorization', [])
		.config(['$httpProvider', Config])
		.factory('AuthorizationService', AuthorizationService)
		.factory('httpAuthorizationRequestInterceptor', ['AuthorizationService', HttpAuthorizationRequestInterceptor])
})();