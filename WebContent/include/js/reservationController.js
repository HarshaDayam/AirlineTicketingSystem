(function() {
	'use strict'
	var app = angular.module("app");

	app.controller("reservationController",
					function($scope, $http, $rootScope, $location, $window) {
						

						var ctrl = this;
						
						if(!$rootScope.flightData){
							$location.path('/');
						}
						
						console.log($rootScope.flightData);
						
						var flightDetails = $rootScope.flightData.Flight;
						var returnFlightId = $rootScope.flightData.isRoundTrip ? $rootScope.flightData.returnFlight.flightId : null;
						var twowayCharge = $rootScope.flightData.isRoundTrip ? $rootScope.flightData.returnFlight.totalPrice : 0;
						var onewayCharge = flightDetails.totalPrice;
						var totalCharge = onewayCharge + twowayCharge;
						ctrl.editSection = true;
						
						ctrl.oneway = $rootScope.flightData.isRoundTrip ? false : true;
						ctrl.totalprice = totalCharge;
						ctrl.flightdetails = $rootScope.flightData.Flight;
						ctrl.returnflight = $rootScope.flightData.returnFlight;
						ctrl.passengerNames = [];
		            	var max = $rootScope.flightData.passengers;
		            	
		            	for(var i = 0; i < max; i++){
		            		ctrl.passengerNames.push({name: ''});
		            	}
		            	
						
						var userId = $window.sessionStorage.getItem('userId');
						
						if(userId){
							$http.get('/AdvanceSystemProject/rest/user/'+userId)
							.then(function(response) {
											ctrl.name = response.data.customerName;
											ctrl.staddress = response.data.streetAddress;
											ctrl.city = response.data.city;
											ctrl.country = response.data.country;
											ctrl.zip = response.data.zip;
											ctrl.phonenum = response.data.phone;
											ctrl.email = response.data.email;
									},
									function(data, status, headers,config) {
										alert("AJAX failed to get data, status="+ status);
									});
						}
						
						ctrl.continueReservation = function(){
							if($scope.reservationForm.$invalid) return;
							ctrl.editSection = false;
						}
						ctrl.edit = function(){
							ctrl.editSection = true;
						}
																					
						
						ctrl.submitReservation = function() {							
							ctrl.clicked = true;
							var data = {
								origin : flightDetails.origin,
								destination : flightDetails.destination,
								rewards : flightDetails.rewards,
								meal : flightDetails.meal,
								ticketClass : $rootScope.flightData.ticketClass,
								passengers : $rootScope.flightData.passengers,
								returnFlightId : $rootScope.flightData.isRoundTrip ? $rootScope.flightData.returnFlight.flightId
										: null,
								customerName : ctrl.name,
								streetAddress : ctrl.staddress,
								city : ctrl.city,
								country : ctrl.country,
								zip : ctrl.zip,
								phonenumber : ctrl.phonenum,
								email : ctrl.email,
								flightId : flightDetails.flightId,
								departureDate : flightDetails.deptDate,
								distance : flightDetails.distance,
								basePrice : flightDetails.basePrice,
								ccNumber : ctrl.ccNumber,
								expYear : ctrl.expyear,
								cvv : ctrl.cvv,
								totalPrice: totalCharge,
								customerId: userId,
								passengerNames: ctrl.passengerNames
										
							};
							
							$rootScope.confirmationdata = data;
							console.log(data);

							var response = $http.post('/AdvanceSystemProject/rest/flights/reservation',
											data).then(function(response) {
												if(response.data > 0){	
													$rootScope.confirmationnum = response.data;
													console.log(response.data);
													navigateConfirmation();
													ctrl.clicked = false;
												}else{
													alert("an error has occurred");
												}
											},
											function(data, status, headers,config) {
												alert("AJAX failed to get data, status=" + status);
								});
										
						}
								
						function navigateConfirmation(){
							
							$location.path('/bookingconfirmation');
						}
					});
})();
