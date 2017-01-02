(function() {
	'use strict'
	var app = angular.module("app");

	app.controller("showAirportsController", function($scope,$http, $rootScope,$window,
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

		
		ctrl.addAirport = function() {
			$location.path('/admin/addairport/');
		}

	});
})();
