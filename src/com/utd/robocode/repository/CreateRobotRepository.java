package com.utd.robocode.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.utd.robocode.dto.Robots;
import com.utd.robocode.dto.Roles;
import com.utd.robocode.dto.Updates;
import com.utd.robocode.dto.Users;
import com.utd.robocode.utils.DataStoreUtils;

public class CreateRobotRepository extends Repository{

	public StringBuilder sbdomain = null;	
	public Robots objRobot = null;
	public Users objUser = null;
	public DataStoreUtils dataStoreUtils = new DataStoreUtils();
	public static HashMap<String, SessionFactory> sessionFactories = DataStoreUtils.buildSessionFactory();
	
	public CreateRobotRepository(Robots objRobot, int domain, Users objUser) {
		// TODO Auto-generated constructor stub
		this.objRobot = objRobot;
		this.objUser  = objUser;
		
		if(domain == 1){
			sbdomain = new StringBuilder("DBConnectionDomain1");
		}else{
			sbdomain = new StringBuilder("DBConnectionDomain2");
		}
		
	}
	
	public Robots checkExistingRobot() throws Exception{
		SessionFactory sessionFactory = sessionFactories.get(sbdomain.toString());
		try {
			if(sessionFactory != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				List<Robots> robot = session.createQuery("from Robots where robot_name='" + objRobot.getRobot_name() + "'").list();
				System.out.println(robot.size());
				
				if(robot.size()!=0)
					objRobot = robot.get(0); 
				else
					objRobot = null;
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally{
			//sessionFactory.close();
		}
		return objRobot;
	}
	
	public Robots doCreateRobotStepOne() throws Exception{
		SessionFactory sessionFactory = sessionFactories.get(sbdomain.toString());
		try {
			if(sessionFactory != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				// Create native sql query
				//session.createSQLQuery("INSERT INTO Robots (robo_nam)");
							
				objRobot.setCreated_by(Integer.parseInt(objUser.getUser_id()));
				objRobot.setRobot_points(0);
				session.save(objRobot);
												
				/*Query robot = session.createSQLQuery("INSERT INTO Robots (robot_name, robot_desc, created_by) "
						+ "VALUES('" + objRobot.getRobot_name() + "','"
						+ objRobot.getRobot_desc()+ "','"
						+ objUser.getUser_id()+"')");
				robot.executeUpdate();*/
				
				
				
				session.getTransaction().commit();
				
				List<Robots> listRobots = session.createQuery("from Robots where robot_name ='" + objRobot.getRobot_name() + "'").list();
				System.out.println(listRobots.size());
				
				if(listRobots.size()!=0){
					objRobot = listRobots.get(0);
					Updates robotUpdates = new Updates();
					
					robotUpdates.setRu_robot_id(Integer.parseInt(objRobot.getRobot_id()));
					robotUpdates.setRu_user_id(Integer.parseInt(objUser.getUser_id()));
					robotUpdates.setRu_domain_id(Integer.parseInt(objUser.getUser_domain_id()));
					robotUpdates.setCreated_date(objRobot.getCreated_date());
					robotUpdates.setUpdated_date(objRobot.getUpdated_date());
					session.beginTransaction();
					session.save(robotUpdates);
					session.getTransaction().commit();
				}
				else
					objRobot = null;
				/*System.out.println(robot.size());
				*/				
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally{
			//sessionFactory.close();
		}
		return objRobot;
	}
	
	public List<Roles> getRoles() throws Exception{
		SessionFactory sessionFactory = sessionFactories.get(sbdomain.toString());
		List<Roles> roles = null;
		try {
			if(sessionFactory != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				roles = session.createQuery("from Roles where role_name != 'CREATOR'").list();
				System.out.println(roles.size());			
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally{
			//sessionFactory.close();
		}
		return roles;
	}
	
}
