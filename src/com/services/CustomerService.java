package com.services;

import java.util.List;

import Repositories.CustomerDao;
import Repositories.ReservationsDAO;

import com.model.Customer;
import com.viewmodels.ReservationCustomerFlight;

public class CustomerService {
	
	public Customer getCustomerDetails(int customerId){
		CustomerDao dao = new CustomerDao();
		return dao.getCustomerDetails(customerId);
	}
	
	public List<ReservationCustomerFlight> getMyReservations(int customerId){
		ReservationsDAO dao = new ReservationsDAO();
		return dao.getMyReservations(customerId);
	}

}
