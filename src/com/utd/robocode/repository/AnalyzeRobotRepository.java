package com.utd.robocode.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.utd.robocode.dto.*;
import com.utd.robocode.utils.DataStoreUtils;

public class AnalyzeRobotRepository{

	public StringBuilder sbdomain = null;	
	public AnalyzeRobot objmr = null;
	public Users objUser = null;
	public DataStoreUtils dataStoreUtils = new DataStoreUtils();
	public static HashMap<String, SessionFactory> sessionFactories = DataStoreUtils.buildSessionFactory();
	
	
	public AnalyzeRobotRepository(Users objUser) {
		// TODO Auto-generated constructor stub
		this.objUser = objUser;
		if(objUser.getUser_domain_id().equalsIgnoreCase("1")){
			sbdomain = new StringBuilder("DBConnectionDomain1");
		}else{
			sbdomain = new StringBuilder("DBConnectionDomain2");
		}
	}
	
	public List<Robots> robots1(Users objUser)throws Exception
	{
		SessionFactory sessionFactory = sessionFactories.get(sbdomain.toString());
		
		int acc;
		List<Robots> mr = null;
		Session session = sessionFactory.openSession();		
		//objUser = a;
		//objUser= Integer.parseInt((Users)request.getSession().getAttribute("userObj.getUser_id()"));
		// mr = session.createQuery("from MossResult WHERE percentage >= 25" ).list();
		mr = session.createQuery("from Robots WHERE created_by = "+objUser.getUser_id() ).list();
		return mr;
	}
	
	public List<MossResult> getResult(Integer robotId, Users objUser)throws Exception
	{
		SessionFactory sessionFactory = sessionFactories.get(sbdomain.toString());
		
		int acc;
		List<MossResult> mr = null;
		Robots robot = null;
		Session session = sessionFactory.openSession();		
		//objUser = a;
		//objUser= Integer.parseInt((Users)request.getSession().getAttribute("userObj.getUser_id()"));
		// mr = session.createQuery("from MossResult WHERE percentage >= 25" ).list();
		mr = session.createQuery("from MossResult WHERE compare_robot_id_1 = "+robotId 
				+" and compare_domain_id_1 = "+objUser.getUser_domain_id() +" and compare_domain_id_2 = " +objUser.getUser_domain_id()).list();
		
		return mr;
	}
	
	public Robots getRobot(Integer robotId, Users objUser)throws Exception
	{
		SessionFactory sessionFactory = sessionFactories.get(sbdomain.toString());
		
		int acc;
		List<Robots> mr = null;
		Robots robot = null;
		Session session = sessionFactory.openSession();		
		//objUser = a;
		//objUser= Integer.parseInt((Users)request.getSession().getAttribute("userObj.getUser_id()"));
		// mr = session.createQuery("from MossResult WHERE percentage >= 25" ).list();
		mr = session.createQuery("from Robots WHERE created_by = "+objUser.getUser_id() +" and robot_id = "+robotId ).list();
		if(mr.size() != 0){
			robot = mr.get(0);
		}
		return robot;
	}
}
