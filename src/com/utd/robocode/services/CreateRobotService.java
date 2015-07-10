package com.utd.robocode.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;

import com.utd.robocode.dto.AccessRights;
import com.utd.robocode.dto.Robots;
import com.utd.robocode.dto.Roles;
import com.utd.robocode.dto.Users;
import com.utd.robocode.repository.AccessRightsRepository;
import com.utd.robocode.repository.CreateRobotRepository;
import com.utd.robocode.utils.DataStoreUtils;

public class CreateRobotService {
	private CreateRobotRepository objRobotRepo = null;
	
	public Robots createRobotStepOne(Robots objRobot, int domain, Users objUser){
		try{
			//objLoginRepo.login(objUser);
			objRobotRepo = new CreateRobotRepository(objRobot,domain, objUser);
			objRobot = objRobotRepo.doCreateRobotStepOne();
			/*if(objRobot == null){
				return false;
			}*/
		}catch(Exception ex){
			
		}	
		return objRobot; 
	}
	
	public boolean checkExistingRobo(Robots objRobot, int domain, Users objUser){
		try{
			//objLoginRepo.login(objUser);
			objRobotRepo = new CreateRobotRepository(objRobot,domain, objUser);
			objRobot = objRobotRepo.checkExistingRobot();
			if(objRobot == null){
				return false;
			}
		}catch(Exception ex){
			
		}	
		return true; 
	}
	
	public boolean assignAccessRightToRobot(List<AccessRights> accessRights, int domain) throws Exception{
		boolean result = false;
		AccessRightsRepository objAccessRightsRepository = new AccessRightsRepository(domain);
		objAccessRightsRepository.AssignRightsToRobot(accessRights);
		return result;
	}
	
	public List<Roles> getRoles(Robots objRobot, int domain, Users objUser) throws Exception{
		List<Roles> listRoles = new ArrayList<Roles>();
		objRobotRepo = new CreateRobotRepository(objRobot,domain, objUser);
		return objRobotRepo.getRoles();
	}
}
