(function() {
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
	
	angular.module('lunchinator.restaurants')
		.factory('RestaurantService', ['RestaurantResource', RestaurantService])
})();