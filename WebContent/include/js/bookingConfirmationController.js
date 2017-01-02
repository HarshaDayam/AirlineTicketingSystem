(function() {
	'use strict'
	var app = angular.module("app");

	app.controller(
					"bookingConfirmationController",
					function($scope,$rootScope, $location) {
						
						var ctrl = this;
					
						ctrl.confirmationdata = $rootScope.confirmationdata;
						ctrl.confirmationnum = $rootScope.confirmationnum;
					});
})();
					