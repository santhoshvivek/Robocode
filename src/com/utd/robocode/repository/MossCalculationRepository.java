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
import com.utd.robocode.dto.MossResult;
import com.utd.robocode.dto.Robots;
import com.utd.robocode.dto.Users;
import com.utd.robocode.utils.DataStoreUtils;

public class MossCalculationRepository extends Repository{

	public StringBuilder sbdomain = null;	
	public Robots objRobot = null;
	public Users objUser = null;
	public DataStoreUtils dataStoreUtils = new DataStoreUtils();
	public static HashMap<String, SessionFactory> sessionFactories = DataStoreUtils.buildSessionFactory();
	
	public MossCalculationRepository(Robots objRobot, int domain, Users objUser) {
		// TODO Auto-generated constructor stub
		this.objRobot = objRobot;
		this.objUser  = objUser;
		
		if(domain == 1){
			sbdomain = new StringBuilder("DBConnectionDomain1");
		}else{
			sbdomain = new StringBuilder("DBConnectionDomain2");
		}
		
	}
	
	public MossCalculationRepository(String robotId, int domain, Users objUser) {
		// TODO Auto-generated constructor stub
		this.objRobot.setRobot_id(robotId);
		this.objUser  = objUser;
		
		if(domain == 1){
			sbdomain = new StringBuilder("DBConnectionDomain1");
		}else{
			sbdomain = new StringBuilder("DBConnectionDomain2");
		}
		
	}
	
	public void insertMossResult(MossResult result) throws Exception{
		SessionFactory sessionFactory = sessionFactories.get(sbdomain.toString());
		try {
			if(sessionFactory != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				
				session.save(result);
				
				session.getTransaction().commit();								
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally{
			//sessionFactory.close();
		}
	}
	
	public void deleteMossResult(String domain) {
		SessionFactory sessionFactory = sessionFactories.get(sbdomain.toString());
		try {
			if(sessionFactory != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				
				//Deleting from Access_Rights Table
				session.createQuery("delete from MossResult where compare_robot_id_1 = "+ objRobot.getRobot_id()
						+" and compare_domain_id_1 = "+Integer.parseInt(domain)).executeUpdate();
				
				
				session.getTransaction().commit();								
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally{
			//sessionFactory.close();
		}
	}
	
	public List<Robots> getAllRobots() throws Exception{
		SessionFactory sessionFactory = sessionFactories.get(sbdomain.toString());
		List<Robots> robot = null;
		try {
			if(sessionFactory != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				robot = session.createQuery("from Robots").list();
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
		return robot;
	}	
	
	
}
