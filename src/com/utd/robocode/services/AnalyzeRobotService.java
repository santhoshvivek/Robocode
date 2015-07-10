package com.utd.robocode.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import com.utd.robocode.dto.*;
import com.utd.robocode.repository.*;
import com.utd.robocode.utils.DataStoreUtils;

public class AnalyzeRobotService {
	
	private AnalyzeRobotRepository objAnaRobRepo;
	List<Robots> analyze = null;
	
	public AnalyzeRobotService(){
		
	}
		public List<Robots> Analyze(Users objUser)
	{
		try
		{
			objAnaRobRepo = new AnalyzeRobotRepository(objUser);
			analyze = objAnaRobRepo.robots1(objUser) ;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return analyze;
	}
}



