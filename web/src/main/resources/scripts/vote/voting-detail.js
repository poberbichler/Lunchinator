(function() {
	function VotingDetails($rootScope, votingResource, authorizationService) {
		return {
			restrict: 'E',
			scope: {
				votedElement: '=element'
			},
			template:
				'<span class="voting-details" ng-show="alreadyVoted()">' +
				'	<span ng-class="{ownVote: isOwnVote}"><a class="glyphicon glyphicon-thumbs-up"></a>({{upvotes}})</span>' +
				'	<span ng-class="{ownVote: isOwnVote === false}"><a class="glyphicon glyphicon-thumbs-down"></a>({{downvotes}})</span>' +
				'</span>',
				
			link: function(scope) {
				scope.upvotes = 0;
				scope.downvotes = 0;
				scope.isOwnVote = undefined;

				scope.alreadyVoted = function() {
					return localStorage[authorizationService.generateStorageKey(scope.votedElement.id)] !== undefined;
				}
				
				if (scope.alreadyVoted()) {
					loadVotes();
				}
				
				$rootScope.$on("vote:add", function(event, data) {
					if (scope.votedElement.id === data.id) {
						loadVotes();
					}
				});
				
				function loadVotes() {
					votingResource.findByTarget({targetId: scope.votedElement.id}, function(result) {
						scope.upvotes = 0;
						scope.downvotes = 0;
						
						angular.forEach(result, function(vote) {
							vote.upvote ? scope.upvotes++ : scope.downvotes++;
							if (vote.author === authorizationService.getCurrentUser().name) {
								scope.isOwnVote = vote.upvote;
							}
						});
					});
				}
			}
		}
	}
	
	angular.module('lunchinator.voting')
		.directive('votingDetails', ['$rootScope', 'VotingResource', 'AuthorizationService', VotingDetails])
})();