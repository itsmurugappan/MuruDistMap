(function() {
    
    var app = angular.module('distApp', ['ngRoute','angular-loading-bar']);
    
    app.config(function($routeProvider) {
        $routeProvider
            .when('/', {
                controller: 'DistController',
                templateUrl: 'app/views/mapdist.html'
			})
            .otherwise( { redirectTo: '/' } );
    });
	
}());