package com.services;

import java.util.ArrayList;
import java.util.List;

import Repositories.FlightDAO;

import com.model.Flight;
import com.model.FlightSearch;
import com.model.TicketPrice;

public class FlightsService {

	public List<Flight> getFlights(FlightSearch search) {

		FlightDAO fdao = new FlightDAO();

		List<Flight> flightDetails = new ArrayList<Flight>();

		flightDetails = fdao.getFlightDetails(search.getOrigin(), search.getDest(), search.getDeptDate());

		for (Flight f : flightDetails) {

			double totalPrice = TicketPrice.calculate(f.getDeptDate(),
					f.getDistance(), f.getBasePrice(), search.getPassengers(), search.getTicketClass());

			f.setTotalPrice(totalPrice);

		}
		return flightDetails;

	}
	/*
	 * public List<Flight> getReturnFlights(String origin, String dest,String
	 * deptDate){ FlightDAO fdao = new FlightDAO(); return
	 * fdao.getFlightDetails(origin, dest,deptDate);
	 * 
	 * }
	 */
}
