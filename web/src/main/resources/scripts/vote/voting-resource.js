(function() {
	function VotingResource($resource, BASE_URLS) {
		return $resource(BASE_URLS.voting + ':targetId', {}, {
			save: {method: 'POST'},
			findByTarget: {method: 'GET', isArray: true, params: {targetId: '@targetId'}}
		});
	}
	
	angular.module('lunchinator.voting')
		.factory('VotingResource', ['$resource', 'BASE_URLS', VotingResource])
})();		