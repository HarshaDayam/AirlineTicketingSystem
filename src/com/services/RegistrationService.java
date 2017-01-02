package com.services;

import com.model.Registration;

import Repositories.RegistrationDao;

public class RegistrationService {
	
	public int register(Registration registerationDetails){
		
		RegistrationDao rDAO = new RegistrationDao();
		
		return rDAO.makeRegistration(registerationDetails);
	}

}
