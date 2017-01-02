(function() {
	'use strict'
	var app = angular.module("app");

	app.controller(
					"seatController",
					function($scope, $http, $rootScope, $location, $routeParams) {
						var ctrl = this;
						ctrl.reserved  = [];
						ctrl.numberOfPassengers = $rootScope.numberOfPassengers;
						
						if(!$rootScope.numberOfPassengers || !$rootScope.ticketClass){
							 $location.path('/reservation/'+ $routeParams.confirmationId);
							 return;
						}
						
						$http.get('/AdvanceSystemProject/rest/flights/seats/' + 
								$routeParams.confirmationId + '/' + $routeParams.flightId).then(
							function(response) {
								console.log(response.data);
								ctrl.reserved = response.data;	
							}, function(data, status, headers, config) {
								alert("AJAX failed to get data, status=" + status);
							});
						
					});
})();
