package com.utd.robocode.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.utd.robocode.dto.*;
import com.utd.robocode.repository.MyRobotsRepository;
import com.utd.robocode.utils.DataStoreUtils;

public class MyRobotsService {
	private MyRobotsRepository objmyroboRepo;
	List<Robots> mr = null;
	
	public List<Robots> robots1(Users objUser){
	
		try
		{
			objmyroboRepo = new MyRobotsRepository();
			mr = objmyroboRepo.robots1(objUser);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return mr;
	}
}
