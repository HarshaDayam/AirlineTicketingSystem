(function() {
	'use strict'
	var app = angular.module("app");

	app.controller("myReservationsController",
					function($scope, $http, $rootScope, $location, $window) {
						
						var ctrl = this;
						
						 
						var userId = $window.sessionStorage.getItem('userId');
						
						if(userId && userId !== 'Admin_000'){		
							getMyReservations();			
						}else{
							$location.path('/');
						}
						
						ctrl.checkPast = function(date){
							if(new Date(date) < new Date()){
								return true;
							}
							return false;
						}
						
						ctrl.view = function(reservation){
							$location.path('/reservation/'+reservation.conformationId);
						}
						
						ctrl.cancel = function(reservation) {
							var result = confirm("Are you sure you want to cancel reservation?");
							
							if(result === true){
							$http.post('/AdvanceSystemProject/rest/flights/cancelreservation/'
											+ reservation.conformationId).then(function(response) {
												getMyReservations();
									}, function(data, status, headers, config) {
										alert("AJAX failed to get data, status=" + status);
									});
							}

						}
						
						function getMyReservations(){
							var userId = $window.sessionStorage.getItem('userId');
							$http.get('/AdvanceSystemProject/rest/user/myreservations/'+userId).then(
									function(response) {
										ctrl.myReservations = response.data;
										
										
										
									}, function(data, status, headers, config) {
										alert("AJAX failed to get data, status=" + status);
							});
						}
						
						
					});
})();