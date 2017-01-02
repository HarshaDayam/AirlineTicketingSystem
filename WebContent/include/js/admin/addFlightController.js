(function() {
	'use strict'
	var app = angular.module("app");

	app.controller("addFlightController", function($scope,$http, $rootScope,$window,
			$location,$routeParams) {

		var ctrl = this;
		
		ctrl.data = {};
		
		if($window.sessionStorage.getItem('userId') !== 'Admin_000'){
			$location.path('/');
		}
			
		$http.get('/AdvanceSystemProject/rest/flights/airports').then(
				function(response) {
					ctrl.airports = response.data;
				}, function(data, status, headers, config) {
					alert("AJAX failed to get data, status=" + status);
		});

		
		ctrl.addFlight = function() {
			var newData = {};
			angular.extend(newData, ctrl.data);
			newData.origin = newData.origin.airportCode;
			newData.destination = newData.destination.airportCode; 
			newData.departureTime = newData.departureTime.getHours() + ':' + newData.departureTime.getMinutes() + ':00';
			newData.arrivalTime = newData.arrivalTime.getHours() + ':' + newData.arrivalTime.getMinutes() + ':00';
						
			var response = $http.post(
					'/AdvanceSystemProject/rest/admin/addflight/', newData).then(function(response) {
				console.log(response.data);
				ctrl.details = response.data;
				$location.path('/admin');

			}, function(data, status, headers, config) {
				alert("AJAX failed to get data, status=" + status);
			});
		}

		

	});
})();
