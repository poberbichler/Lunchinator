(function() {
	function SuggestionCtrl(suggestionService) {
		this.data = suggestionService.data;
		this.save = suggestionService.save;
		this.updatePlannedEndTime = suggestionService.updateEndTime;
		
		suggestionService.init();
	}
	
	function SuggestionService(suggestionResource, restaurantResource) {
		var suggestionData = {};
		
		return {
			data: suggestionData,
			init: init,
			save: save,
			
			updateEndTime: updateEndTime
		}
		
		function init() {
			suggestionData.suggestions = suggestionResource.findUpcoming();
			suggestionData.availableRestaurants = restaurantResource.findAvailable();
			
			var nextStartTime = new Date();
			if (nextStartTime.getHours() > 12) {
				nextStartTime.setDate(nextStartTime.getDate() + 1);
			}
			
			nextStartTime.setHours(11, 30);
			suggestionData.newSuggestion = { startTime: nextStartTime }
			updateEndTime(nextStartTime);
		}
		
		function save() {
			suggestionResource.save(suggestionData.newSuggestion, function(result) {
				suggestionData.suggestions.push(result);
				suggestionData.newSuggestion = {};
			});
		}
		
		function updateEndTime(startTime) {
			var endTime = new Date(startTime.getTime());
			endTime.setMinutes(endTime.getMinutes() + 60);
			
			suggestionData.newSuggestion.endTime = endTime;
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