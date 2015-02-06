(function() {
	function RestaurantsCtrl(restaurantService) {
		this.data = restaurantService.restaurantData;
		this.save = restaurantService.save;

		restaurantService.init();
	}
	
	angular.module('lunchinator.restaurants')
		.controller('RestaurantCtrl', ['RestaurantService', RestaurantsCtrl])
})();