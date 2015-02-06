(function() {
	function SuggestionCtrl(suggestionService) {
		this.data = suggestionService.data;
		this.save = suggestionService.save;
		this.updatePlannedEndTime = suggestionService.updateEndTime;
		
		suggestionService.init();
	}
	
	angular.module('lunchinator.suggestions')
		.controller('SuggestionCtrl', ['SuggestionService', SuggestionCtrl])
})();