package com.utd.robocode.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.servlet.*;
import javax.servlet.http.HttpSession;

import com.utd.robocode.dto.*;
import com.utd.robocode.utils.DataStoreUtils;

public class PlayBattleRepository {
	
	public StringBuilder sbdomain = null;	
	public DataStoreUtils dataStoreUtils = new DataStoreUtils();
	public static HashMap<String, SessionFactory> sessionFactories = DataStoreUtils.buildSessionFactory();

	public String domain;
	public Users objUser;
	
	public PlayBattleRepository(String domain, Users objUser) {
		this.domain = domain;
		this.objUser = objUser;
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
			robotslist = session.createQuery("from Robots").list();
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
