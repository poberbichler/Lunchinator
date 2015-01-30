(function() {
	function SuggestionCtrl(suggestionService) {
		this.data = suggestionService.data;
		this.save = suggestionService.save;
		
		suggestionService.init();
	}
	
	function SuggestionService(suggestionResource, restaurantResource) {
		var suggestionData = {};
		
		return {
			data: suggestionData,
			init: init,
			save: save
		}
		
		function init() {
			suggestionData.suggestions = suggestionResource.findUpcoming();
			suggestionData.availableRestaurants = restaurantResource.findAvailable();
	
			suggestionData.newSuggestion = {};
		}
		
		function save() {
			suggestionResource.save(suggestionData.newSuggestion, function(result) {
				suggestionData.suggestions.push(result);
				suggestionData.newSuggestion = {};
			});
		}
	}
	
	function SuggestionResource($resource, BASE_URLS) {
		return $resource(BASE_URLS.suggestions + ':methodName', {}, {
			findAll: {method: 'GET', isArray: true, params: {methodName: 'all'}},
			save: {method: 'POST', params: {methodName: 'save'}},
			findUpcoming: {method: 'GET', isArray: true, params: {methodName: 'upcoming'}}
		})
	}
	
	angular.module('lunchinator.suggestions', ['ngResource'])
		.controller('SuggestionCtrl', ['SuggestionService', SuggestionCtrl])
		.factory('SuggestionResource', ['$resource', 'BASE_URLS', SuggestionResource])
		.factory('SuggestionService', ['SuggestionResource', 'RestaurantResource', SuggestionService])
})();