(function() {
	function RestaurantsCtrl(restaurantService) {
		this.data = restaurantService.restaurantData;
		this.save = restaurantService.save;

		restaurantService.init();
	}
	
	function RestaurantResource($resource, BASE_URLS) {
		return $resource(BASE_URLS.restaurants + ':methodName/:restaurantId', {}, {
			save: {method: 'POST', params: {methodName: 'save'}},
			findAll: {method: 'GET', isArray: true, params: {methodName: 'all'}},
			findAvailable: {method: 'GET', isArray: true, params: {methodName: 'all'}},
			findOne: {method: 'GET', isArray: false, params: {restaurantId: '@restaurantId'}}
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
		.factory('RestaurantResource', ['$resource', 'BASE_URLS', RestaurantResource])
		.factory('RestaurantService', ['RestaurantResource', RestaurantService])
})();