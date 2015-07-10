package com.utd.robocode.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import com.utd.robocode.dto.*;
import com.utd.robocode.repository.MyRobotsContributorsRepository;
import com.utd.robocode.utils.DataStoreUtils;

public class MyRobotsContributorsService {
	private MyRobotsContributorsRepository objmyroboRepo;
	List<Modifier> mr = null;
	private String a;
	
	public List<Modifier> Modif(Users objUser,String a){
	
		try
		{
			//this.a=a;
			//this.objUser=objUser;
			objmyroboRepo = new MyRobotsContributorsRepository();
			mr = objmyroboRepo.Modif(objUser,a);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return mr;
	}
}
