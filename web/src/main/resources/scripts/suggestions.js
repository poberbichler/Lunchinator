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
			suggestionData.suggestions = suggestionResource.findAll();
			suggestionData.availableRestaurants = restaurantResource.findAvailable();
		}
		
		function save() {
			suggestionResource.save(suggestionData.newSuggestion, function(result) {
				suggestionData.suggestions.push(result);
			});
		}
	}
	
	function SuggestionResource($resource) {
		return $resource('http://localhost:8082/suggestions/:methodName', {}, {
			findAll: {method: 'GET', isArray: true, params: {methodName: 'all'}}
		})
	}
	
	angular.module('lunchinator.suggestions', ['ngResource'])
		.controller('SuggestionCtrl', ['SuggestionService', SuggestionCtrl])
		.factory('SuggestionResource', ['$resource', SuggestionResource])
		.factory('SuggestionService', ['SuggestionResource', 'RestaurantResource', SuggestionService])
})();