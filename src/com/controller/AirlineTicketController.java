package com.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import com.model.Airport;
import com.model.Flight;
import com.model.FlightSearch;
import com.model.Passenger;
import com.model.Registration;
import com.model.ReservationDetails;
import com.services.AirportService;
import com.services.FlightsService;
import com.services.RegistrationService;
import com.services.ReservationService;
import com.services.SeatSelectionService;
import com.viewmodels.ReservationCustomerFlight;

@Path("/flights")
public class AirlineTicketController {

	static Logger logger = Logger.getLogger(AirlineTicketController.class);

	@Path("/airports")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Airport> getAirports() {

		AirportService aservice = new AirportService();

		return aservice.getOrigin();

	}

	@Path("/search")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	public List<Flight> searchFlights(FlightSearch search) {

		FlightsService fService = new FlightsService();

		return fService.getFlights(search);

	}

	@Path("/reservation")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })

	public int reserve(ReservationDetails reserve) {

		ReservationService rService = new ReservationService();

		return rService.makeReservation(reserve);

	}
	
	@Path("/registration")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })

	public int register(Registration reserve) {

		RegistrationService rService = new RegistrationService();

		return rService.register(reserve);

	}

	@Path("/reservation/{confirmationId}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })

	public ReservationCustomerFlight getReservationdetails(@PathParam("confirmationId") int confirmationId, 
			                                                             @QueryParam("name") String name) {

		ReservationService rService = new ReservationService();

		return rService.getReservation(confirmationId, name);
	}
	
	@Path("/cancelreservation/{confirmationId}")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })

	public void cancelReservation(@PathParam("confirmationId") int confirmationId) {

		ReservationService rService = new ReservationService();

		rService.cancelReservation(confirmationId);
	}
	
	@Path("/seats/{confirmationId}/{flightId}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })

	public ArrayList<Integer> getPassengerDetails(@PathParam("confirmationId") int confirmationId, @PathParam("flightId") int flightId) {

		SeatSelectionService aSeat = new SeatSelectionService();

		return aSeat.selectedSeats(confirmationId, flightId);
	}

	@Path("/seatbooking/{confirmationId}/{flightId}")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })

	public void seatBooking(@PathParam("confirmationId") int confirmationId, @PathParam("flightId") int flightId, List<Passenger> passengers) {

		SeatSelectionService aSeatService = new SeatSelectionService();

		aSeatService.seatBooking(confirmationId, flightId, passengers);
	}

}
