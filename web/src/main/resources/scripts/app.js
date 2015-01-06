(function() {
	function NavbarCtrl() {
	}
	
	function Config($stateProvider, $urlRouterProvider) {
		$stateProvider.state('home', {
			url: '/home',
			templateUrl: '/templates/home'
		});
		
		$stateProvider.state('restaurants', {
			url: '/restaurants',
			templateUrl: '/templates/restaurants',
			controller: 'RestaurantCtrl as restaurantCtrl'
		});
		
		$stateProvider.state('suggestions', {
			url: '/suggestions',
			templateUrl: '/templates/suggestions',
			controller: 'SuggestionCtrl as suggestionCtrl'
		});
		
		$urlRouterProvider.otherwise('/home');
	}
	
	angular.module('lunchinator', [
	                               'ui.router',
	                               'ui.bootstrap.datetimepicker',
	                               'lunchinator.restaurants', 
	                               'lunchinator.suggestions',
	                               'lunchinator.config'])
		.config(['$stateProvider', '$urlRouterProvider', Config])
		.controller('navbarCtrl', [NavbarCtrl]);
})();