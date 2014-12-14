(function() {
	function RestaurantsCtrl(restaurantResource) {
		this.restaurants = restaurantResource.query();
	}
	
	function RestaurantResource($resource) {
		return $resource('http://localhost:8081/restaurants/all');
	}
	
	angular.module('lunchinator.restaurants', ['ngResource'])
		.controller('RestaurantCtrl', ['RestaurantResource', RestaurantsCtrl])
		.factory('RestaurantResource', ['$resource', RestaurantResource]);
})();