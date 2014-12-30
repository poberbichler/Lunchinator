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
	
			suggestionData.newSuggestion = {
					endTime: new Date(),
					startTime: new Date()
			};
		}
		
		function save() {
			console.log(suggestionData);
			suggestionResource.save(suggestionData.newSuggestion, function(result) {
				suggestionData.suggestions.push(result);
				suggestionData.newSuggestion = {};
			});
		}
	}
	
	function SuggestionResource($resource) {
		return $resource('http://localhost:8082/suggestions/:methodName', {}, {
			findAll: {method: 'GET', isArray: true, params: {methodName: 'all'}},
			save: {method: 'POST', params: {methodName: 'save'}}
		})
	}
	
	angular.module('lunchinator.suggestions', ['ngResource'])
		.controller('SuggestionCtrl', ['SuggestionService', SuggestionCtrl])
		.factory('SuggestionResource', ['$resource', SuggestionResource])
		.factory('SuggestionService', ['SuggestionResource', 'RestaurantResource', SuggestionService])
})();