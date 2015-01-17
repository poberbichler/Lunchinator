(function() {
	function VotingDirective() {
		return {
			restrict: 'E',
			scope: {
				votedElement: '=element'
			},
			template: 
				'<div class="voting-input">' +
					'<span><a href="" ng-click="upvote()" class="glyphicon glyphicon-thumbs-up"></a></span>' +
					'<span><a href="" ng-click="downvote()" class="glyphicon glyphicon-thumbs-down"></a></span>' +
				'</div>',
				
			link: function(scope) {
				scope.upvote = function() {
					console.log('upvoted for', scope.votedElement);
				}
				
				scope.downvote = function() {
					console.log('downvoted for', scope.votedElement);
				}
			}
		}
	}
	
	angular.module('lunchinator.voting', [])
		.directive('votingDirective', VotingDirective)
})();