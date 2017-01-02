(function() {
	var app = angular.module("app", [ "ngRoute" ]);
	app.config(function($routeProvider, $locationProvider) {
		$routeProvider.when("/reserve", {
			templateUrl : 'reservation.html',
			controller : 'reservationController',
			controllerAs: 'ctrl'

		}).when("/register",{
			templateUrl : 'register.html',
			controller : 'registrationController',
			controllerAs: 'ctrl'
						
		}).when("/myreservations",{
			templateUrl : 'myReservations.html',
			controller : 'myReservationsController',
			controllerAs: 'ctrl'				
		}).when("/cancelreservation", {
			templateUrl : 'cancelReservation.html',
			controller : 'cancelRegistrationController',
			controllerAs: 'ctrl'

		}).when("/seat/:confirmationId/:flightId", {
			templateUrl : 'seatSelection.html',
			controller : 'seatController',
			controllerAs: 'ctrl'

		}).when("/reservation/:confirmationId", {
			templateUrl : 'checkin.html',
			controller : 'checkinController',
			controllerAs: 'ctrl'
		})
		.when("/checkin", {
			templateUrl : 'checkin.html',
			controller : 'checkinController',
			controllerAs: 'ctrl'

		}).when("/bookingconfirmation", {
			templateUrl : 'bookingConfirmation.html',
			controller : 'bookingConfirmationController',
			controllerAs: 'ctrl'
		})
		.when("/admin", {
			templateUrl : 'admin/admin.html'
		})
		.when("/admin/addflight", {
			templateUrl : 'admin/addFlights.html',
			controller : 'addFlightController',
			controllerAs: 'ctrl'
		})
		.when("/admin/airports", {
			templateUrl : 'admin/showAirports.html',
			controller : 'showAirportsController',
			controllerAs: 'ctrl'
		})
		.when("/admin/addairport", {
			templateUrl : 'admin/addAirport.html',
			controller : 'addAirportController',
			controllerAs: 'ctrl'
		})
		.when("/", {
			templateUrl : 'search.html',
			controller : 'searchController',
			controllerAs: 'ctrl'

		}).when("/termsandconditions",{
			templateUrl : 'termsAndConditions.html',
							
		});
	});
})();