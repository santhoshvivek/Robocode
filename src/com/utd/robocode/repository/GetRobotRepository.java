package com.utd.robocode.repository;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.utd.robocode.dto.Robots;
import com.utd.robocode.utils.DataStoreUtils;

public class GetRobotRepository {
	
	public StringBuilder sbdomain = null;	
	public DataStoreUtils dataStoreUtils = new DataStoreUtils();
	public static HashMap<String, SessionFactory> sessionFactories = DataStoreUtils.buildSessionFactory();
	
	public Robots GetRobot(String robot_id,String domain_id)
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
		session.beginTransaction();
		List<Robots> codes = null;
		codes =session.createQuery("from Robots where robot_id = " +robot_id).list();
		return codes.get(0);
	}
	
	public String GetRobotCode(String robot_id,String domain_id)
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
		session.beginTransaction();
		List<Robots> codes = null;
		codes =session.createQuery("from Robots where robot_id = " +robot_id).list();
		return codes.get(0).getRobot_code();
	}
	
	public String GetRobotName(String robot_id,String domain_id)
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
		session.beginTransaction();
		List<Robots> codes = null;
		codes =session.createQuery("from Robots where robot_id = " +robot_id).list();
		return codes.get(0).getRobot_name();
	}
	
	public String GetRobotDesc(String robot_id,String domain_id)
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
		session.beginTransaction();
		List<Robots> codes = null;
		codes =session.createQuery("from Robots where robot_id = " +robot_id).list();
		return codes.get(0).getRobot_desc();
	}

}
