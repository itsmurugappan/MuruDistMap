(function() {
    
    var DistController = function ($scope, $routeParams, distFactory) {
		$scope.locations = new Object();
        $scope.locations.origins = new Array();
		$scope.locations.dest = new Array();
		$scope.showResults = false;
		$scope.maxlength = 100;
        $scope.minlength = 2;
        
        function init() {
                    var origin = new Object();
					origin.o = ' ';
					var dest = new Object();
					dest.d = ' ';
                    $scope.locations.origins.push(origin);
			 		$scope.locations.dest.push(dest);
        }        

        init();
		
			var checkResultLength = function(originLength,destLength){
				if((originLength)*destLength>10)
				{
					toastr.error('You have reached the maximum number of origin and destination combinations');
					return false;
				}
				return true;
			};
        
            var validateInput = function()
            {
              for(var i = 0 ; i<$scope.locations.origins.length ; i++)
              {
                if($scope.locations.origins[i].o === undefined || 
                   $scope.locations.origins[i].o.length < 2 || 
                   $scope.locations.origins[i].o.length > 100 )
                {
                    toastr.error('Origin should be between 2 and 100 characters');
                    return false;
                }                            
              }
              for(var i = 0 ; i<$scope.locations.dest.length ; i++)
              {
                if($scope.locations.dest[i].d === undefined || 
                   $scope.locations.dest[i].d.length < 2 || 
                   $scope.locations.dest[i].d.length > 100 )
                {
                    toastr.error('Destination should be between 2 and 100 characters');
                    return false;
                }                            
              }
              return true;
            };
                
            $scope.addOrigin = function () {
                        var originLength = $scope.locations.origins.length + 1;
				        var destLength = $scope.locations.dest.length;
				        if(checkResultLength(originLength,destLength))
				        {
					       var origin = new Object();
					       origin.o = ' ';
					       $scope.locations.origins.push(origin);
				        }
			};
			
			$scope.addDest = function () {
                    var originLength = $scope.locations.origins.length;
                    var destLength = $scope.locations.dest.length + 1;
                    if(checkResultLength(originLength,destLength))
                    {
                        var dest = new Object();
                        dest.d = ' ';
                        $scope.locations.dest.push(dest);
                    }
			};
				
		    $scope.getDistance = function ( ) {
                if(validateInput())
                {
                    distFactory.getDistance($scope.locations)
                    .then(function(response) {
					$scope.results = response.data;
					$scope.showResults = true;
                    }, function(data, status, headers, config) {
                     toastr.error('Error retreiving distance information');
                });                        
                }
  			};
        
            $scope.deleteOrigin = function ( id ) {
                if($scope.locations.origins.length > 1) {
	                $scope.locations.origins.splice(id, 1);
                }  
  			};
        
            $scope.deleteDest = function ( id ) {
                if($scope.locations.dest.length > 1) {
	                $scope.locations.dest.splice(id, 1);
                }  
  			};	
		
		    $scope.clearResults = function () {
                $scope.showResults = false;
  			};
  };
    
    DistController.$inject = ['$scope', '$routeParams', 'distFactory'];

    angular.module('distApp')
      .controller('DistController', DistController);
    
}());
