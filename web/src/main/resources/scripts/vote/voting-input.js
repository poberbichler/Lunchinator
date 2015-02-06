(function() {
	function VotingInput($rootScope, votingResource, authorizationService) {
		return {
			restrict: 'E',
			scope: {
				votedElement: '=element'
			},
			template:
				'<span class="voting-input pull-right">' +
				'	<a href="" ng-click="upvote()" ng-class="{voted: isVote(true)}" class="glyphicon glyphicon-thumbs-up"></a>' +
				'	<a href="" ng-click="downvote()" ng-class="{voted: isVote(false)}" class="glyphicon glyphicon-thumbs-down"></a>' +
				'</span>',
				
			link: function(scope) {
				scope.upvote = function() {
					saveVote(true);
				}
				scope.downvote = function() {
					saveVote(false);
				}
				
				function saveVote(currentVote) {
					votingResource.save({target: scope.votedElement.id, upvote: currentVote}, function(result) {
						scope.votedElement.totalVotes++;
						localStorage[authorizationService.generateStorageKey(scope.votedElement.id)] = currentVote;
						$rootScope.$emit("vote:add", scope.votedElement);
					});
				}
				
				scope.isVote = function(value) {
					var element = localStorage[authorizationService.generateStorageKey(scope.votedElement.id)];
					
					if (element === undefined) {
						return false;
					}
					
					return element === value.toString();
				}
			}
		}
	}
	
	angular.module('lunchinator.voting')
		.directive('votingInput', ['$rootScope', 'VotingResource', 'AuthorizationService', VotingInput])
})();