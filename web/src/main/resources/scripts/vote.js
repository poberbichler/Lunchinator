(function() {
	function VotingDetails(votingResource, authorizationService) {
		return {
			restrict: 'E',
			scope: {
				votedElement: '=element'
			},
			template:
				'<div class="voting-details">' +
					'<span ng-class="{ownVote: isOwnVote}"><a class="glyphicon glyphicon-thumbs-up"></a>({{upvotes}})</span>' +
					'<span ng-class="{ownVote: isOwnVote === false}"><a class="glyphicon glyphicon-thumbs-down"></a>({{downvotes}})</span>' +
				'</div>',
				
			link: function(scope) {
				scope.upvotes = 0;
				scope.downvotes = 0;
				scope.isOwnVote = undefined;

				votingResource.findByTarget({targetId: scope.votedElement.id}, function(result) {
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

	function VotingInput(votingResource) {
		return {
			restrict: 'E',
			scope: {
				votedElement: '=element'
			},
			template:
				'<span class="voting-input pull-right">' +
					'<a href="" ng-click="upvote()" ng-class="{voted: isVote(true)}" class="glyphicon glyphicon-thumbs-up"></a>' +
					'<a href="" ng-click="downvote()" ng-class="{voted: isVote(false)}" class="glyphicon glyphicon-thumbs-down"></a>' +
				'</span>',
			link: function(scope) {
				scope.upvote = function() {
					votingResource.save({target: scope.votedElement.id, upvote: true}, function(result) {
						scope.votedElement.totalVotes++;
						localStorage[scope.votedElement.id] = true;
					});
				}

				scope.downvote = function() {
					votingResource.save({target: scope.votedElement.id, upvote: false}, function(result) {
						scope.votedElement.totalVotes++;
						localStorage[scope.votedElement.id] = false;
					});
				}
				
				scope.isVote = function(value) {
					var element = localStorage[scope.votedElement.id];
					
					if (element === undefined) {
						return false;
					}
					
					return element === value.toString();
				}
			}
		}
	}
	
	function VotingResource($resource, BASE_URLS) {
		return $resource(BASE_URLS.voting + ':targetId', {}, {
			save: {method: 'POST'},
			findByTarget: {method: 'GET', isArray: true, params: {targetId: '@targetId'}}
		});
	}
	
	angular.module('lunchinator.voting', [])
		.directive('votingInput', ['VotingResource', VotingInput])
		.directive('votingDetails', ['VotingResource', 'AuthorizationService', VotingDetails])
		.factory('VotingResource', ['$resource', 'BASE_URLS', VotingResource])
})();