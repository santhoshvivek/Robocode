package com.utd.robocode.repository;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.utd.robocode.dto.AccessRights;
import com.utd.robocode.dto.MossResult;
import com.utd.robocode.dto.Robots;
import com.utd.robocode.dto.Updates;
import com.utd.robocode.utils.DataStoreUtils;

public class DeleteRobotRepository {
	
	public StringBuilder sbdomain = null;	
	public DataStoreUtils dataStoreUtils = new DataStoreUtils();
	public static HashMap<String, SessionFactory> sessionFactories = DataStoreUtils.buildSessionFactory();
		
	public boolean delete(String robot_id, String domain_id)
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
		
		//Deleting from Robots Table
		List<Robots> robo = session.createQuery("from Robots where robot_id = "+robot_id).list();
		session.beginTransaction();
		session.delete(robo.get(0));
		session.getTransaction().commit();
		
		//Deleting from Robot_Update Table
		List<Updates> roboUp = session.createQuery("from Updates where ru_robot_id = "+robot_id).list();
		for(int i =0 ;i<roboUp.size();i++)
		{
			session.beginTransaction();
			session.delete(roboUp.get(i));
			session.getTransaction().commit();
		}
		
		//Deleting from Access_Rights Table
		List<AccessRights> AR = session.createQuery("from AccessRights where ar_robot_id = "+robot_id).list();
		for(int i =0 ;i<AR.size();i++)
		{
			session.beginTransaction();
			session.delete(AR.get(i));
			session.getTransaction().commit();
		}
		
		//Deleting from Moss_Results Table
		List<MossResult> MR = session.createQuery("from MossResult where (compare_robot_id_1 = "+robot_id+") or (compare_robot_id_2 = "+robot_id+")").list();
		for(int i =0 ;i<MR.size();i++)
		{
			session.beginTransaction();
			session.delete(MR.get(i));
			session.getTransaction().commit();
		}
		
		
		
		return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}

}
