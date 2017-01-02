package com.services;

import java.util.List;

import com.model.Airport;

import Repositories.AirportDao;

public class AirportService {

	public List<Airport> getOrigin(){
		
		AirportDao airdao = new AirportDao();
	
		return airdao.getAirportDetails();
		
	}
}
