(function(){
var app = angular.module('app');

var settings = {
        rows: 10,
        cols: 20,
        rowCssPrefix: 'row-',
        colCssPrefix: 'col-',
        seatWidth: 35,
        seatHeight: 35,
        seatCss: 'seat',
        selectedSeatCss: 'selectedSeat',
        selectingSeatCss: 'selectingSeat',
        firstClassCss: 'first-class',
        economyClassCss: 'economy-class'
    };

    app.directive('seatBooking', function($timeout, $http, $routeParams, $location, $rootScope){
        return {
             restrict:'AE',
            scope:{
                reserved: '=',
                numberOfPassengers: '='
            },
            templateUrl: 'seatsDirective.html',
            link: function(scope){
            	var max = scope.numberOfPassengers;
            	var ticketClass = $rootScope.ticketClass;
            	var init = function (reservedSeat) {
                    var str = [], seatNo, className, k=0;
                    for (i = 0; i < settings.rows; i++) {
                    	if(i===3 || i === 8) k = k + 30;
                    	var l=0;
                        for (j = 0; j < settings.cols; j++) {
                        	if(j=== 0 || j === 5) l = l + 30;
                            seatNo = (i + j * settings.rows + 1);
                            
                            className = settings.seatCss + ' ' + settings.rowCssPrefix + i.toString() + ' ' + settings.colCssPrefix + j.toString();
                                                                                  
                            if ($.isArray(reservedSeat) && $.inArray(seatNo, reservedSeat) != -1) {
                                className += ' ' + settings.selectedSeatCss;
                            }
                            
                            
                            if(ticketClass === 'FirstClass' && seatNo >50){
                            	className += ' ' + settings.selectedSeatCss;
                            }else if(ticketClass === 'Economy' && seatNo <=50){
                            	className += ' ' + settings.selectedSeatCss;
                            }
                                                        
                            str.push('<li class="' + className + '"' +
                                      'style="top:' + (i * settings.seatHeight + k).toString() + 'px;left:' + (j * settings.seatWidth + l).toString() + 'px">' +
                                      '<a title="' + seatNo + '">' + seatNo + '</a>' +
                                      '</li>');
                        }
                    }
                    $('#place').html(str.join(''));
                };
                
                $timeout(function(){
                	 var bookedSeats = scope.reserved;
                     init(bookedSeats);
                     var ticketClass = $rootScope.ticketClass;
                     
                     $('.seat').click(function () {
                         if ($(this).hasClass(settings.selectedSeatCss)){
                             alert('This seat is already reserved');
                         }
                         else{
                        	 var str = [];
                        	 $.each($('#place li.' + settings.selectingSeatCss + ' a'), function (index, value) {
                                 item = $(this).attr('title');                   
                                 str.push(item);                   
                             });
                             
                             if(str.length < max || (str.length === max && $(this).hasClass(settings.selectingSeatCss))){
                            	 $(this).toggleClass(settings.selectingSeatCss);
                             }
                         }
                 });
              
                 $('#btnShow').click(function () {
                     var str = [];
                     $.each($('#place li.' + settings.selectedSeatCss + ' a, #place li.'+ settings.selectingSeatCss + ' a'), function (index, value) {
                         str.push($(this).attr('title'));
                     });
                     
                 });

                 $('#btnShowNew').click(function () {
                     var str = [], item;
                     $.each($('#place li.' + settings.selectingSeatCss + ' a'), function (index, value) {
                         item = $(this).attr('title');                   
                         str.push(item);                   
                     });
                     
                     if(str.length !== scope.numberOfPassengers){
                    	 alert('Please select seats for all '+ scope.numberOfPassengers +' passengers');
                    	 return;
                     }
                     
                     var data = []
                     for(var i =0; i< str.length; i++){
                      var passenger = {
                    			 seat: str[i]
                         }
                      data.push(passenger);
                     }
                     $http.post('/AdvanceSystemProject/rest/flights/seatbooking/' + $routeParams.confirmationId + '/' + $routeParams.flightId , 
                    		 data).then(function(response){
                    			 $location.path('/reservation/'+ $routeParams.confirmationId);
                     });
                     
                 });
                 
                }, 200);
            }
        }
    });
})();