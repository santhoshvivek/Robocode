package com.utd.robocode.repository;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.utd.robocode.dto.Robots;
import com.utd.robocode.dto.Users;
import com.utd.robocode.utils.DataStoreUtils;

public class RankingRepository {

	public StringBuilder sbdomain = null;
	public DataStoreUtils dataStoreUtils = new DataStoreUtils();
	public static HashMap<String, SessionFactory> sessionFactories = DataStoreUtils.buildSessionFactory();
	
	public String domain;
	
	public RankingRepository(String domain) {
		this.domain = domain;
	}
	
	public boolean setPoints(String name, String domain_id, int points)
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
		
		List<Robots> robots = session.createQuery("from Robots where robot_name ='"+name+"'").list();
		Robots objRobot = robots.get(0);
		int prev = objRobot.getRobot_points();
		objRobot.setRobot_points(points + prev);
		session.beginTransaction();
		session.update(objRobot);
		session.getTransaction().commit();
		return true;				
		}
		catch(Exception x)
		{
			x.printStackTrace();
		}
		return false;
	}
	
	
	public List<Robots> fetchAllRobotsList()throws Exception
	{

		List<Robots> robotslist = null;
		SessionFactory sf1;
		SessionFactory sf2; 
		Session session = null;
		
		
		int domainx_sess = Integer.parseInt(domain);
		
		if(domainx_sess==1)
		{
			sf1 = sessionFactories.get("DBConnectionDomain1");
			session = sf1.openSession();
			session.beginTransaction();
		}
		else if(domainx_sess==2)
		{
			sf2 = sessionFactories.get("DBConnectionDomain2");
			session = sf2.openSession();
			session.beginTransaction();
		}
		
		
		try
		{	
			robotslist = session.createQuery("from Robots where robot_points > 0 order by robot_points desc").list();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return robotslist;

	}
	
	public String fetchUserName(int user_id) throws Exception
	{
		String user = null;
		List<Users> userEach = null;
	
		SessionFactory sf1;
		SessionFactory sf2; 
		Session session = null;
		
		int domainx_sess = Integer.parseInt(domain);
		
		if(domainx_sess==1)
		{
			sf1 = sessionFactories.get("DBConnectionDomain1");
			session = sf1.openSession();
			session.beginTransaction();
		}
		else if(domainx_sess==2)
		{
			sf2 = sessionFactories.get("DBConnectionDomain2");
			session = sf2.openSession();
			session.beginTransaction();
		}
		
		
		try
		{	
			userEach = session.createQuery("FROM Users WHERE user_id="+user_id).list();
			user = userEach.get(0).getUser_name();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return user;

	}
}
