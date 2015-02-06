(function() {
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
	
	angular.module('lunchinator.suggestions')
		.factory('SuggestionService', ['SuggestionResource', 'RestaurantResource', SuggestionService])
})();