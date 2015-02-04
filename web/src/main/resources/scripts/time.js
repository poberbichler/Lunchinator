(function() {
	function TimeDirective() {
		return {
			restrict: 'E',
			scope: {
				date: '='
			},
			template: '<br /><span>' +
					'<a class="btn btn-default" ng-click="addMinutes(120)">+2h</a>' +
					'<a class="btn btn-default" ng-click="addMinutes(60)">+1h</a>' +
					'<a class="btn btn-default" ng-click="addMinutes(45)">+45h</a>' +
					'<a class="btn btn-default" ng-click="addMinutes(30)">+30h</a>' +
					'<a class="btn btn-default" ng-click="addMinutes(15)">+15h</a>' +
				'</span>',
			
			link: function(scope) {
				scope.addMinutes = function(minutes) {
					scope.date.setMinutes(scope.date.getMinutes() + minutes);
				}
			}
		}
	}
	
	angular.module('lunchinator.time', [])
		.directive('timeDirective', [TimeDirective])
})();