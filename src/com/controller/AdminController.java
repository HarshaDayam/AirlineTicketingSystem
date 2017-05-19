package com.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.model.Airport;
import com.model.Flight;
import com.services.AdminService;

// admin controller
@Path("/admin")
public class AdminController { 
	  
	@Path("/addflight")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })

	public int addFlight(Flight flight) {

		AdminService rService = new AdminService();

		return rService.addFlight(flight);
	}
	
	@Path("/addairport")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })

	public boolean addAirport(Airport airport) {

		AdminService rService = new AdminService();

		rService.addAirport(airport);
		return true;
	}
	
	

}
