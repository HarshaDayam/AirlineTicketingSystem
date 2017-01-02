package com.services;

import java.util.ArrayList;
import java.util.List;

import com.model.Passenger;

import Repositories.SeatsDAO;

public class SeatSelectionService {

	public ArrayList<Integer> selectedSeats(int confirmationId, int flightId){
		
		SeatsDAO sDAO = new SeatsDAO();
		
		return sDAO.getSelectedSeats(confirmationId, flightId);
	}
	
	public void seatBooking(int confirmationId, int flightId, List<Passenger>  passengers){
		SeatsDAO sDAO = new SeatsDAO();
		
		sDAO.selectSeats(confirmationId, flightId, passengers);
		
	}

}
