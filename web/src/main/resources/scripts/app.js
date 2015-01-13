(function() {
	function NavbarCtrl($scope, authorizationService) {
		$scope.userList = authorizationService.userList;
		$scope.currentUser = authorizationService.getCurrentUser;

		$scope.setCurrentUser = authorizationService.setCurrentUser;
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
		'lunchinator.config',
		'lunchinator.authorization',
		'lunchinator.error'])
			.config(['$stateProvider', '$urlRouterProvider', Config])
			.controller('navbarCtrl', ['$scope', 'AuthorizationService', NavbarCtrl]);
})();