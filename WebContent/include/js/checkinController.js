(function() {
	'use strict'
	var app = angular.module("app");

	app.controller("checkinController", function($scope, $http, $rootScope,
			$location,$routeParams) {
		var ctrl = this;
		ctrl.getReservation = getReservation;
		if($routeParams.confirmationId){
			ctrl.number = $routeParams.confirmationId;
			ctrl.getReservation();
		}
		
		ctrl.checkPast = function(date){
			if(new Date(date) < new Date()){
				return true;
			}
			return false;
		}

		 function getReservation() {
			var confirmationId = ctrl.number;
			var name = ctrl.name || $rootScope.name;
			$rootScope.name = ctrl.name;

			var response = $http.get(
					'/AdvanceSystemProject/rest/flights/reservation/'
					+ confirmationId +'?name=' + name).then(function(response) {
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

		ctrl.selectSeats = function() {
			$rootScope.ticketClass = ctrl.details.ticketClass;
			$location.path('/seat/' + ctrl.number + '/' + ctrl.details.flightId);
		}
		
		ctrl.selectReturnSeats = function() {
			$rootScope.ticketClass = ctrl.details.ticketClass;
			$location.path('/seat/' + ctrl.number + '/' + ctrl.details.returnFlightId);
		}
	});
})();
