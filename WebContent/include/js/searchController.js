(function() {
	'use strict'
	var app = angular.module("app");
	
	app.directive('minDate', function() {
	    return {
	        require: 'ngModel',
	        link: function(scope, element, attrs, ngModelController) {
	            
	            var minDate = scope.$eval(attrs.minDate) || new Date(new Date().setHours(0, 0, 0, 0));
	            
	            ngModelController.$validators.minDate = function(value) {
	                return value >= minDate;
	            };
	        }
	    };
	});

	
	app.controller("searchController", function($scope, $http, $rootScope,
			$location) {
		var ctrl = this;
		ctrl.selectAirportFrom = null;
		ctrl.selectAirportTo = null;
		ctrl.airports = [];
		ctrl.trip = 'oneway';
		ctrl.passengers = null;
		ctrl.deptDate = null;
		ctrl.returnDate = null;
		ctrl.ticketClass = null;
		ctrl.startDate = new Date();

		var response = $http.get(
				'/AdvanceSystemProject/rest/flights/airports').then(
				function(response) {
					ctrl.airports = response.data;
				}, function(data, status, headers, config) {
					alert("AJAX failed to get data, status=" + status);
				});

		ctrl.search = function() {
			
			if($scope.searchForm.$invalid) return;
			
			if(ctrl.selectAirportFrom.airportCode === ctrl.selectAirportTo.airportCode){
				alert("Origin and destination cannot be same");
				return;
			}
			
			if(ctrl.trip !== 'oneway' && new Date(ctrl.deptDate) >= new Date(ctrl.returnDate)){
				alert("Departure date should always be at-least one day before return date");
				return;
			}

			var data = {
				origin : ctrl.selectAirportFrom.airportCode,
				dest : ctrl.selectAirportTo.airportCode,
				deptDate : ctrl.deptDate,
				passengers : ctrl.passengers,
				ticketClass: ctrl.ticketClass
			};

			var response = $http.post(
					'/AdvanceSystemProject/rest/flights/search', data)
					.then(function(response) {
						
						ctrl.searchResult = response.data;
					}, function(data, status, headers, config) {
						alert("AJAX failed to get data, status=" + status);
					});

			if (ctrl.trip !== 'oneway') {
				search2();
			}
		}

		function search2() {

			var data1 = {
				origin : ctrl.selectAirportTo.airportCode,
				dest : ctrl.selectAirportFrom.airportCode,
				deptDate : ctrl.returnDate,
				passengers : ctrl.passengers,
				ticketClass: ctrl.ticketClass
			};

			var response = $http.post(
					'/AdvanceSystemProject/rest/flights/search', data1)
					.then(function(response) {
						console.log(response.data);
						ctrl.searchResult2 = response.data;
					}, function(data, status, headers, config) {
						alert("AJAX failed to get data, status=" + status);
					});

		}
		
		ctrl.book = function(){
						
			var flightData = {
				isRoundTrip : ctrl.trip === 'oneway' ? false : true,
				Flight : ctrl.selectoneway,
				returnFlight : ctrl.selectreturn,
				passengers : ctrl.passengers,
				ticketClass: ctrl.ticketClass
			}
			
			if(flightData.isRoundTrip){
				if(!flightData.Flight) {
					alert('Please select flight'); return;
				}
				
				if(!flightData.returnFlight){ 
					alert('Please select return flight'); return;	
				}
				
				if((ctrl.ticketClass === 'FirstClass' && flightData.passengers > flightData.Flight.firstClassSeats)
						||(ctrl.ticketClass === 'Economy' && flightData.passengers > flightData.Flight.economySeats)){
					alert('Seats not available for First fight'); return;
				}
				
				if((ctrl.ticketClass === 'FirstClass' && flightData.passengers > flightData.returnFlight.firstClassSeats)
						||(ctrl.ticketClass === 'Economy' && flightData.passengers > flightData.returnFlight.economySeats)){
					alert('Seats not available for return flight'); return;
				}
				
			}else{
				if(!flightData.Flight) {
					alert('Please select flight'); return;	
				}
				
				if((ctrl.ticketClass === 'FirstClass' && flightData.passengers > flightData.Flight.firstClassSeats)
						||(ctrl.ticketClass === 'Economy' && flightData.passengers > flightData.Flight.economySeats)){
					alert('Seats not available for oneway fight'); return;
				}
			}
			
			$rootScope.flightData = flightData;						
			$location.path('/reserve');
		}
		
		ctrl.login = function(){
			var loginData={
				username : ctrl.username,
				password : ctrl.password
			}
			var response = $http.post(
					'/AdvanceSystemProject/rest/flights/login', loginData)
					.then(function(response) {
						console.log(response.data);
						ctrl.searchResult2 = response.data;
					}, function(data, status, headers, config) {
						alert("AJAX failed to get data, status=" + status);
					});
		}
	});
})();