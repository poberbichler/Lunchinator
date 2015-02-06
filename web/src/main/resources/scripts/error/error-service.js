(function() {
	function ErrorService() {
        var errors = [];

        return {
            errors: errors,
            addError: addError,
            reset: reset
        }

        function addError(error) {
            errors.push(error);
        }

        function reset() {
            errors.length = 0;
        }
    }
	
	angular.module('lunchinator.error')
		.factory('ErrorService', ErrorService)
})();