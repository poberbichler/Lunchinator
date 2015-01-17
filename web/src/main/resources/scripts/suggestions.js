(function() {
	function SuggestionCtrl(suggestionService) {
		this.data = suggestionService.data;
		this.save = suggestionService.save;
		
		this.upvote = upvote;
		this.downvote = downvote;
		
		suggestionService.init();

		function upvote(suggestion) {
			suggestionService.vote(suggestion, true);
		}
		
		function downvote(suggestion) {
			suggestionService.vote(suggestion, false);
		}
	}
	
	function SuggestionService(suggestionResource, restaurantResource) {
		var suggestionData = {};
		
		return {
			data: suggestionData,
			init: init,
			save: save,
			vote: vote
		}
		
		function init() {
			suggestionData.suggestions = suggestionResource.findAll();
			suggestionData.availableRestaurants = restaurantResource.findAvailable();
	
			suggestionData.newSuggestion = {};
		}
		
		function save() {
			suggestionResource.save(suggestionData.newSuggestion, function(result) {
				suggestionData.suggestions.push(result);
				suggestionData.newSuggestion = {};
			});
		}
		
		function vote(suggestion, therefor) {
			console.log('this suggestion got voted', suggestion, therefor)
		}
	}
	
	function SuggestionResource($resource, BASE_URLS) {
		return $resource(BASE_URLS.suggestions + ':methodName', {}, {
			findAll: {method: 'GET', isArray: true, params: {methodName: 'all'}},
			save: {method: 'POST', params: {methodName: 'save'}}
		})
	}
	
	angular.module('lunchinator.suggestions', ['ngResource'])
		.controller('SuggestionCtrl', ['SuggestionService', SuggestionCtrl])
		.factory('SuggestionResource', ['$resource', 'BASE_URLS', SuggestionResource])
		.factory('SuggestionService', ['SuggestionResource', 'RestaurantResource', SuggestionService])
})();