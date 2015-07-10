package com.utd.robocode.services;

import java.util.List;

import com.utd.robocode.dto.AccessControl;
import com.utd.robocode.dto.Robots;
import com.utd.robocode.dto.Users;
import com.utd.robocode.repository.GetRobotRepository;


public class GetRobotService {
	
	private GetRobotRepository objGetRobotRepo;
	
	public Robots getRobot(String robot_id,String domain_id)
	{
		Robots robot = null;
		try
		{
			objGetRobotRepo = new GetRobotRepository();
			robot = objGetRobotRepo.GetRobot(robot_id, domain_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return robot;
	}
	
	public String getRobotCode(String robot_id,String domain_id)
	{
		String code = "";
		try
		{
			objGetRobotRepo = new GetRobotRepository();
			code = objGetRobotRepo.GetRobotCode(robot_id, domain_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return code;
	}
	
	public String getRobotName(String robot_id,String domain_id)
	{
		String code = "";
		try
		{
			objGetRobotRepo = new GetRobotRepository();
			code = objGetRobotRepo.GetRobotName(robot_id, domain_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return code;
	}
	
	public String getRobotDesc(String robot_id,String domain_id)
	{
		String code = "";
		try
		{
			objGetRobotRepo = new GetRobotRepository();
			code = objGetRobotRepo.GetRobotDesc(robot_id, domain_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return code;
	}

}
