(function() {
	function RestaurantResource($resource, BASE_URLS) {
		return $resource(BASE_URLS.restaurants + ':methodName/:restaurantId', {}, {
			save: {method: 'POST', params: {methodName: 'save'}},
			findAll: {method: 'GET', isArray: true, params: {methodName: 'all'}},
			findAvailable: {method: 'GET', isArray: true, params: {methodName: 'all'}},
			findOne: {method: 'GET', isArray: false, params: {restaurantId: '@restaurantId'}}
		});
	}
	
	angular.module('lunchinator.restaurants')
		.factory('RestaurantResource', ['$resource', 'BASE_URLS', RestaurantResource])
})();