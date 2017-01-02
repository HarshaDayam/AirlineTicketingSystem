package com.services;

import Repositories.AdminDao;

import com.model.Airport;
import com.model.Flight;

public class AdminService {

	public int addFlight(Flight flight){
		AdminDao dao = new AdminDao();
		return dao.addFlight(flight);
	}
	
	public void addAirport(Airport airport){
		AdminDao dao = new AdminDao();
		dao.addAirport(airport);
	}
}
