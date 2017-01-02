package com.services;

import com.model.ReservationDetails;
import com.viewmodels.ReservationCustomerFlight;

import Repositories.ReservationsDAO;

public class ReservationService {
	
	public int makeReservation(ReservationDetails reserve){
		
		ReservationsDAO rDAO = new ReservationsDAO();
		
		/*TicketPrice tPrice = new TicketPrice();
		double ticketPrice = tPrice.calculate(reserve.getDepartureDate(), reserve.getDistance(), reserve.getBasePrice(), reserve.getPassengers(), reserve.isOneway());
		reserve.setTotalPrice(ticketPrice);
			*/		
		return rDAO.makeReservation(reserve);
		
	}
	
	public ReservationCustomerFlight getReservation(int confirmId, String name){
		 ReservationsDAO rDAO = new ReservationsDAO();
		 
		 return rDAO.getReservationDetails(confirmId, name);
	}
	
	public void cancelReservation(int confirmId){
		
		ReservationsDAO rDAO = new ReservationsDAO();
		
		rDAO.cancelReservation(confirmId);
	}

}
