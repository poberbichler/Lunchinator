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
		
		$urlRouterProvider.otherwise('/home');
	}
	
	angular.module('lunchinator', ['ui.router', 'lunchinator.restaurants'])
		.config(['$stateProvider', '$urlRouterProvider', Config])
		.controller('navbarCtrl', [NavbarCtrl]);
})();