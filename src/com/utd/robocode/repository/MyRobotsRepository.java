package com.utd.robocode.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.utd.robocode.dto.*;
import com.utd.robocode.utils.DataStoreUtils;

public class MyRobotsRepository{

	public StringBuilder sbdomain = null;	
	
	public DataStoreUtils dataStoreUtils = new DataStoreUtils();
	public static HashMap<String, SessionFactory> sessionFactories = DataStoreUtils.buildSessionFactory();
	
	
	
	public List<Robots> robots1(Users a)
	{
		Users objUser = a;
		
		if(objUser.getUser_domain_id().equals("1")){
			sbdomain = new StringBuilder("DBConnectionDomain1");
		}else{
			sbdomain = new StringBuilder("DBConnectionDomain2");
		}
		SessionFactory sessionFactory = sessionFactories.get(sbdomain.toString());
	
		//SessionFactory sf1 = sessionFactories.get("DBConnectionDomain1");
		//SessionFactory sf2 = sessionFactories.get("DBConnectionDomain2");
		//SessionFactory sessionFactory = sessionFactories.get(sbdomain.toString());
		
		List<Robots> mr = null;
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();

				objUser = a;
				
				mr = session.createQuery("from Robots where created_by =" + objUser.getUser_id()).list();
			
			/*if(sf2!=null)
			{
				Session session = sf2.openSession();
				session.beginTransaction();

				objUser = a;
				
				mr = session.createQuery("from Robots where created_by =" + objUser.getUser_id()).list();
			}*/
		
		
		return mr;
	}
	
}
