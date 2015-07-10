package com.utd.robocode.services;

import com.utd.robocode.repository.DeleteRobotRepository;

public class DeleteRobotService {
	
	public boolean deleteRobot(String robot_id,String domain_id)
	{
		DeleteRobotRepository del = new DeleteRobotRepository();
		return del.delete(robot_id, domain_id);
	}

}
