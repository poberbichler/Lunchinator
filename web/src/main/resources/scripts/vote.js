(function() {
	function VotingDetails($rootScope, votingResource, authorizationService) {
		return {
			restrict: 'E',
			scope: {
				votedElement: '=element'
			},
			template:
				'<div class="voting-details" ng-show="alreadyVoted()">' +
					'<span ng-class="{ownVote: isOwnVote}"><a class="glyphicon glyphicon-thumbs-up"></a>({{upvotes}})</span>' +
					'<span ng-class="{ownVote: isOwnVote === false}"><a class="glyphicon glyphicon-thumbs-down"></a>({{downvotes}})</span>' +
				'</div>',
				
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

	function VotingInput($rootScope, votingResource, authorizationService) {
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
	
	function VotingResource($resource, BASE_URLS) {
		return $resource(BASE_URLS.voting + ':targetId', {}, {
			save: {method: 'POST'},
			findByTarget: {method: 'GET', isArray: true, params: {targetId: '@targetId'}}
		});
	}
	
	angular.module('lunchinator.voting', [])
		.directive('votingInput', ['$rootScope', 'VotingResource', 'AuthorizationService', VotingInput])
		.directive('votingDetails', ['$rootScope', 'VotingResource', 'AuthorizationService', VotingDetails])
		.factory('VotingResource', ['$resource', 'BASE_URLS', VotingResource])
})();
