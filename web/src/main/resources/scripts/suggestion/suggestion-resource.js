(function() {
	function SuggestionResource($resource, BASE_URLS) {
		return $resource(BASE_URLS.suggestions + ':methodName', {}, {
			findAll: {method: 'GET', isArray: true, params: {methodName: 'all'}},
			save: {method: 'POST', params: {methodName: 'save'}},
			findUpcoming: {method: 'GET', isArray: true, params: {methodName: 'upcoming'}}
		})
	}
	
	angular.module('lunchinator.suggestions')
		.factory('SuggestionResource', ['$resource', 'BASE_URLS', SuggestionResource])
})();