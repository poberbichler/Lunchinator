(function() {
	function RestaurantsCtrl(restaurantService) {
		this.data = restaurantService.restaurantData;
		this.save = restaurantService.save;

		restaurantService.init();
	}
	
	function RestaurantResource($resource) {
		return $resource('http://localhost:8081/restaurants/:methodName', {}, {
			save: {method: 'POST', params: {methodName: 'save'}},
			findAll: {method: 'GET', isArray: true, params: {methodName: 'all'}},
			findAvailable: {method: 'GET', isArray: true, params: {methodName: 'all'}}
		});
	}
	
	function RestaurantService(restaurantResource) {
		var restaurantData = {};
		
		return {
			restaurantData: restaurantData,

			init: init,
			save: save
		}
		
		function init() {
			restaurantData.restaurants = restaurantResource.findAll();
		}
		
		function save() {
			restaurantResource.save(restaurantData.newRestaurant, function(result) {
				restaurantData.restaurants.push(result);
				restaurantData.newRestaurant = {};
			});
		}
	}
	
	angular.module('lunchinator.restaurants', ['ngResource'])
		.controller('RestaurantCtrl', ['RestaurantService', RestaurantsCtrl])
		.factory('RestaurantResource', ['$resource', RestaurantResource])
		.factory('RestaurantService', ['RestaurantResource', RestaurantService])
})();