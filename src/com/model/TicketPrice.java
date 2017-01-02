package com.model;


import static java.time.temporal.ChronoUnit.DAYS;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class TicketPrice {

	public static double calculate(Date departure,double distance, double baseCharges,
			                   int passengers, String ticketClass) {
		
		LocalDate dept = LocalDate.parse(( new SimpleDateFormat("yyyy-MM-dd").format(departure)));
		
		long days = DAYS.between(LocalDate.now(),dept);
		
		double totalCharges = baseCharges + (distance * 0.15);
		
		if(days < 7)
			totalCharges += 75;
		else if(days > 7 && days < 14)
			totalCharges +=  50;
		else if(days > 14 && days < 21 )
			totalCharges += 25;
		else
			totalCharges += 15; 
		
		if("FirstClass".equalsIgnoreCase(ticketClass))
			totalCharges += 50;
		
		return Math.round(totalCharges * passengers)  ;
	}
}
