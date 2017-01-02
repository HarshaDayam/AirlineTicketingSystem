package com.controller;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.model.Customer;
import com.model.Login;
import com.services.CustomerService;
import com.services.LoginService;
import com.viewmodels.ReservationCustomerFlight;

@Path("/user")
public class UserController {

	@Path("/login")
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	public Login login(Login login) {

		if("admin".equalsIgnoreCase(login.getUsername()) && "admin".equals(login.getPassword())){
			login.setPassword("XXXXX");
			login.setUserId("Admin_000");
			return login;
		}else{
			LoginService service = new LoginService();
			return service.verifyUser(login);
		}
	}
	
	@Path("/{customerId}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Customer getCustomerDetails(@PathParam("customerId") int customerId) {
       CustomerService service = new CustomerService();
       return service.getCustomerDetails(customerId);
		
	}
	
	@Path("/myreservations/{customerId}")
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ReservationCustomerFlight> getmyReservations(@PathParam("customerId") int customerId) {
       CustomerService service = new CustomerService();
       return service.getMyReservations(customerId);
		
	}
	
}
