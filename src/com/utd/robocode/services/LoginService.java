package com.utd.robocode.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.utd.robocode.dto.Users;
import com.utd.robocode.repository.LoginRepository;
import com.utd.robocode.utils.DataStoreUtils;

public class LoginService {
	private LoginRepository objLoginRepo = null;
	
	public Users isAValidUser(Users objUser){
		try{
			//objLoginRepo.login(objUser);
			objLoginRepo = new LoginRepository(objUser);
			objUser = objLoginRepo.loginUsingHibernate();
			
		}catch(Exception ex){
			
		}	
		return objUser;
	}
}
