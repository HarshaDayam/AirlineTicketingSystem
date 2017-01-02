(function() {
	'use strict'
	var app = angular.module("app");

	app
			.controller(
					"registrationController",
					function($scope, $http, $rootScope, $location) {

						var ctrl = this;

						ctrl.register = function() {

							var data = {

								username : ctrl.username,
								password : ctrl.password,
								name : ctrl.name,
								email : ctrl.email,
								phone : ctrl.phone,
								address : ctrl.staddress,
								city : ctrl.city,
								country : ctrl.country,
								zip : ctrl.zip

							};
							ctrl.clicked = true;
							var response = $http
									.post(
											'/AdvanceSystemProject/rest/flights/registration',
											data)
									.then(
											function(response) {
												if (response.data > 0) {
													alert("Registration Successfull");
													console.log(response.data);
													navigateConfirmation();
												} else {

													alert("Registration Unsuccessful, Please try again Later");

												}
											},
											function(data, status, headers,
													config) {
												alert("AJAX failed to get data, status="
														+ status);
											});
							$location.path("/");
						}
						

					});
})();