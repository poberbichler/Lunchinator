(function() {
	function RestaurantsCtrl(restaurantResource) {
		this.restaurants = restaurantResource.findAll();
		this.save = restaurantResource.save;
		
		var vm = this;
		this.doStuff = function() {
			var received = restaurantResource.save(vm.restaurant);
			console.log(received);
		}
	}
	
	function RestaurantResource($resource) {
		return $resource('http://localhost:8081/restaurants/:methodName', {}, {
			save: {method: 'POST', params: {methodName: 'save'}},
			findAll: {method: 'GET', isArray: true, params: {methodName: 'all'}}
		});
	}
	
	function RestaurantService(restaurantResource) {
		
	}
	
	angular.module('lunchinator.restaurants', ['ngResource'])
		.controller('RestaurantCtrl', ['RestaurantResource', RestaurantsCtrl])
		.factory('RestaurantResource', ['$resource', RestaurantResource]);
})();