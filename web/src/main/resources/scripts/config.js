(function() {
	var BASE_URL = 'http://localhost:'; 
	var BASE_URLS = {
			restaurants: BASE_URL + '8083/restaurants/',
			suggestions: BASE_URL + '8082/suggestions/'
	}
	
	angular.module('lunchinator.config', [])
		.value('BASE_URLS', BASE_URLS)
})();