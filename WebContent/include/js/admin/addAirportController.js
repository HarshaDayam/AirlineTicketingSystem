(function() {
	'use strict'
	var app = angular.module("app");

	app.controller("addAirportController", function($scope,$http, $rootScope, $window,
			$location,$routeParams) {

		var ctrl = this;
		
		ctrl.data = {};
		
		if($window.sessionStorage.getItem('userId') !== 'Admin_000'){
			$location.path('/');
		}
				
		ctrl.addAirport = function() {
			
			var response = $http.post(
					'/AdvanceSystemProject/rest/admin/addairport/', ctrl.data).then(function(response) {
				console.log(response.data);
				$location.path('/admin/airports/');
				

			}, function(data, status, headers, config) {
				alert("AJAX failed to get data, status=" + status);
			});
		}

		

	});
})();
