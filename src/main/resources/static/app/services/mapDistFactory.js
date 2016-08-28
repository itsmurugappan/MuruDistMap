(function() {
    var distFactory = function($http) {
    
        var factory = {};
        
        factory.getDistance = function(locations) {
            return $http({
            url: '/getDistance', 
            method: 'POST',
            data: {locations: locations}
            });
        };

        return factory;
    };
    
    distFactory.$inject = ['$http'];
        
    angular.module('distApp').factory('distFactory', distFactory);
                                           
}());