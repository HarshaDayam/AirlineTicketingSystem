(function() {
	'use strict'
	var app = angular.module("app");

	app.controller("indexController", function($scope, $rootScope, $location, $window,
			$http) {

		var ctrl = this;
		ctrl.userLoggedIn  = false;
		ctrl.adminLoggedIn = false;
		ctrl.data = {
			username : '',
			password : ''
		}
		console.log($window.sessionStorage.getItem('userId'));
		if($window.sessionStorage.getItem('userId')){
			ctrl.userLoggedIn = true;
			if($window.sessionStorage.getItem('userId') === 'Admin_000'){
				ctrl.adminLoggedIn = true;
			}
		}

		ctrl.login = function() {			
			if(!ctrl.data.username || !ctrl.data.password){
				alert('username and password are required');
				return;
			}
				
			$http.post('/AdvanceSystemProject/rest/user/login', ctrl.data)
					.then(function(response) {
						if(response.data){
								$window.sessionStorage.setItem('userId', response.data.userId);
								ctrl.userLoggedIn = true;
								ctrl.loggedInUser = response.data.name;
								if(response.data.userId === 'Admin_000'){
									ctrl.adminLoggedIn = true;
									$location.path('/admin');
								}
								
								ctrl.data = {
										username : '',
										password : ''
									}
						}else{
							alert("Username/Password are incorrect");
						}
					});
		}
		
		ctrl.logout = function(){
			$window.sessionStorage.removeItem('userId');
			ctrl.userLoggedIn = false;
			ctrl.adminLoggedIn = false;
			$location.path('/');
			
		}
	});
})();
