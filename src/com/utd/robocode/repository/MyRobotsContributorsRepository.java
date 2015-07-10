package com.utd.robocode.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.utd.robocode.dto.*;
import com.utd.robocode.utils.DataStoreUtils;

public class MyRobotsContributorsRepository{

	public StringBuilder sbdomain = null;	
	public StringBuilder sbdomain1 = null;	
	public Updates objmr = null;
	public Users objUser = null;
	public String a;
	public DataStoreUtils dataStoreUtils = new DataStoreUtils();
	public static HashMap<String, SessionFactory> sessionFactories = DataStoreUtils.buildSessionFactory();
	
	
	
	public List<Modifier> Modif(Users objUser, String a)throws Exception
	{
		this.objUser = objUser;
		this.a=a;
		if(objUser.getUser_domain_id().equalsIgnoreCase("1")){
			sbdomain = new StringBuilder("DBConnectionDomain1");
		}else{
			sbdomain = new StringBuilder("DBConnectionDomain2");
		}
	
		
		SessionFactory sessionFactory = sessionFactories.get(sbdomain.toString());
		int acc,i;
		//List<Modifier> mod = null;
		List<Updates> mr = null;
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		mr = session.createQuery("from Updates where ru_robot_id="+ a ).list();
		List<Modifier> mod = new ArrayList<Modifier>();
		for(i=0;i<mr.size();i++)
		{
			if(mr.get(i).getRu_domain_id()==1){
				sbdomain1 = new StringBuilder("DBConnectionDomain1");
		    }
			else{
				sbdomain1 = new StringBuilder("DBConnectionDomain2");
			}
			SessionFactory sessionFactory1 = sessionFactories.get(sbdomain1.toString());
			Session session1 = sessionFactory.openSession();
			session1.beginTransaction();
			//List <Updates> r1= session.createQuery("from updates" ).list();
			List <Users> u1 = session.createQuery("from Users where user_id="+ mr.get(i).getRu_user_id()).list();
			//u1.get(0);
			Modifier m = new Modifier();
			
			m.setUser_name(u1.get(0).getUser_name());
			m.setRu_domain_id(mr.get(i).getRu_domain_id());
			m.setUpdated_date(mr.get(i).getUpdated_date());
			mod.add(m);
			
		}
		
		return mod;
	}
	
}
