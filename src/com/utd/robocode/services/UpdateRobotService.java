package com.utd.robocode.services;

import com.utd.robocode.dto.Users;
import com.utd.robocode.repository.MossCalculationRepository;
import com.utd.robocode.repository.UpdateRobotRepository;

public class UpdateRobotService {
	
	public boolean updateRobot(String code, String robot_id, String domain_id, Users x)
	{
		UpdateRobotRepository a = new UpdateRobotRepository();
		return a.update(code, robot_id, domain_id,x);
		
	}

}
