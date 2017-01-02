(function() {
	'use strict'
	var app = angular.module("app");

	app.controller("cancelRegistrationController", function($scope,$http, $rootScope,
			$location,$routeParams) {

		var ctrl = this;
		
		ctrl.checkPast = function(date){
			if(new Date(date) < new Date()){
				return true;
			}
			return false;
		}

		
		ctrl.getReservationDetails = function() {
			var confirmationId = ctrl.number;
			var name = ctrl.name;
			
			var response = $http.get(
					'/AdvanceSystemProject/rest/flights/reservation/'
					+ confirmationId + '?name='+name).then(function(response) {
				if(!response.data){
							ctrl.details = undefined;
							alert("No reservation found with this id");
							return;
						}
				ctrl.details = response.data;
				$rootScope.numberOfPassengers = response.data.passengers;

			}, function(data, status, headers, config) {
				alert("AJAX failed to get data, status=" + status);
			});
		}

		ctrl.cancelflight = function() {
			var result = confirm("Are you sure you want to cancel reservation?");
			
			if(result === true){
			$http.post('/AdvanceSystemProject/rest/flights/cancelreservation/'
							+ ctrl.number).then(function(response) {
						ctrl.getReservationDetails();
					}, function(data, status, headers, config) {
						alert("AJAX failed to get data, status=" + status);
					});
			}

		}

	});
})();
