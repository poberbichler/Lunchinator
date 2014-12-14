(function() {
	function NavbarCtrl() {
	}
	
	function Config($stateProvider, $urlRouterProvider) {
		$stateProvider.state('home', {
			url: '/home',
			templateUrl: '/templates/home'
		});
		
		$stateProvider.state('stuff', {
			url: '/stuff',
			templateUrl: '/templates/stuff'
		});
		
		$urlRouterProvider.otherwise('/home');
	}
	
	angular.module('lunchinator', ['ui.router'])
		.config(['$stateProvider', '$urlRouterProvider', Config])
		.controller('navbarCtrl', [NavbarCtrl]);
})();