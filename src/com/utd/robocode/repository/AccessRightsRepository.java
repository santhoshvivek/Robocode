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

import com.utd.robocode.dto.AccessRights;
import com.utd.robocode.dto.Robots;
import com.utd.robocode.dto.Users;
import com.utd.robocode.utils.DataStoreUtils;

public class AccessRightsRepository extends Repository{

	public StringBuilder sbdomain = null;	
	public Robots objRobot = null;
	public Users objUser = null;
	public DataStoreUtils dataStoreUtils = new DataStoreUtils();
	public static HashMap<String, SessionFactory> sessionFactories = DataStoreUtils.buildSessionFactory();
	
	public AccessRightsRepository(int domain) {
		
		if(domain == 1){
			sbdomain = new StringBuilder("DBConnectionDomain1");
		}else{
			sbdomain = new StringBuilder("DBConnectionDomain2");
		}
		
	}
	
	public AccessRightsRepository(){
		
	}
	
		
	public void AssignRightsToRobot(List<AccessRights> accessRightsList) throws Exception{
		SessionFactory sessionFactory = sessionFactories.get(sbdomain.toString());
		try {
			if(sessionFactory != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				// Create native sql query
				//session.createSQLQuery("INSERT INTO Robots (robo_nam)");							
				
				for(AccessRights accessRights : accessRightsList)
					session.save(accessRights);
				
				/*Query robot = session.createSQLQuery("INSERT INTO Robots (robot_name, robot_desc, created_by) "
						+ "VALUES('" + objRobot.getRobot_name() + "','"
						+ objRobot.getRobot_desc()+ "','"
						+ objUser.getUser_id()+"')");
				robot.executeUpdate();*/
				
				session.getTransaction().commit();
				
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally{
			//sessionFactory.close();
		}		
	}
}
