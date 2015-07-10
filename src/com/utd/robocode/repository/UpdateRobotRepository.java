package com.utd.robocode.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.utd.robocode.dto.Robots;
import com.utd.robocode.dto.Roles;
import com.utd.robocode.dto.Updates;
import com.utd.robocode.dto.Users;
import com.utd.robocode.services.MossRobotService;
import com.utd.robocode.utils.DataStoreUtils;

public class UpdateRobotRepository {
	
	public StringBuilder sbdomain = null;	
	public DataStoreUtils dataStoreUtils = new DataStoreUtils();
	public static HashMap<String, SessionFactory> sessionFactories = DataStoreUtils.buildSessionFactory();
	
	public boolean update(String code, String robot_id , String domain_id, Users x)
	{
		if(domain_id.equals("1"))
		{
			sbdomain = new StringBuilder("DBConnectionDomain1");
		}
		else
		{
			sbdomain = new StringBuilder("DBConnectionDomain2");
		}
		SessionFactory sf = sessionFactories.get(sbdomain.toString());
		Session session = sf.openSession();
		try
		{
		
		List<Robots> robots = session.createQuery("from Robots where robot_id ="+robot_id).list();
		Robots objRobot = robots.get(0);
		objRobot.setRobot_code(code);
		objRobot.setUpdated_date(Calendar.getInstance().getTime());
		session.beginTransaction();
		session.update(objRobot);
		session.getTransaction().commit();
		
		// Update Moss results
		MossCalculationRepository objMossCalculationRepository = new MossCalculationRepository(objRobot, 
				Integer.parseInt(domain_id), x);
		objMossCalculationRepository.deleteMossResult(domain_id);
		
		MossRobotService objMossRobotService = new MossRobotService(objRobot, Integer.parseInt(domain_id), x); 
		
		objMossRobotService.objRobot = objRobot;
					
		Thread th = new Thread(objMossRobotService);
		th.start();
		
		
		
		Updates robotUpdates = new Updates();
		robotUpdates.setRu_robot_id(Integer.parseInt(objRobot.getRobot_id()));
		robotUpdates.setRu_user_id(Integer.parseInt(x.getUser_id()));
		robotUpdates.setRu_domain_id(Integer.parseInt(x.getUser_domain_id()));
		robotUpdates.setCreated_date(objRobot.getCreated_date());
		robotUpdates.setUpdated_date(objRobot.getUpdated_date());
		session.beginTransaction();
		session.save(robotUpdates);
		session.getTransaction().commit();
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

}
