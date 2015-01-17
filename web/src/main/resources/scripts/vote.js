(function() {
	function VotingDirective(votingResource) {
		return {
			restrict: 'E',
			scope: {
				votedElement: '=element'
			},
			template: 
				'<div class="voting-input">' +
					'<span><a href="" ng-click="upvote()" class="glyphicon glyphicon-thumbs-up"></a> ({{upvotes}})</span>' +
					'<span><a href="" ng-click="downvote()" class="glyphicon glyphicon-thumbs-down"></a> ({{downvotes}})</span>' +
				'</div>',
				
			link: function(scope) {
				votingResource.findByTarget({targetId: scope.votedElement.id}, function(result) {
					var upvotes = 0;
					var downvotes = 0;
					angular.forEach(result, function(vote) {
						vote.upvote ? upvotes++ : downvotes++;
					});
					
					scope.upvotes = upvotes;
					scope.downvotes = downvotes;
				});
				
				scope.upvote = function() {
					votingResource.save({target: scope.votedElement.id, upvote: true}, function() {
						scope.upvotes++;
					});
					
				}
				
				scope.downvote = function() {
					votingResource.save({target: scope.votedElement.id, upvote: false}, function() {
						scope.downvotes++;
					});
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
		.directive('votingDirective', ['VotingResource', VotingDirective])
		.factory('VotingResource', ['$resource', 'BASE_URLS', VotingResource])
})();