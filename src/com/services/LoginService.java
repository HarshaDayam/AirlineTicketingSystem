package com.services;


import Repositories.LoginDao;

import com.model.Login;

public class LoginService{
	
	
	public Login verifyUser(Login login){
		LoginDao dao = new LoginDao();
		return dao.verifyUser(login);
	}
		
}
