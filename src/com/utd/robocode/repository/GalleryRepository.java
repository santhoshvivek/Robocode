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

public class GalleryRepository {
	
	public StringBuilder sbdomain = null;	
	public AccessRights objAcc = null;
	public Roles objRoles = null;
	public Users objUser = null;
	public DataStoreUtils dataStoreUtils = new DataStoreUtils();
	public static HashMap<String, SessionFactory> sessionFactories = DataStoreUtils.buildSessionFactory();

	public List<AccessControl> Access1(Users a)throws Exception
	{
		int acc;
		List<AccessControl> AC = new ArrayList<AccessControl>() ;
		List<Robots> robolist = null;
		List<AccessRights> AR = null;
		List<Updates> up = null;
		AccessControl objAc = null;
		Updates objUpdate=null;
		Robots objRobot = null;
		Users objUser = a; // add user information
		SessionFactory sf1 = sessionFactories.get("DBConnectionDomain1");
		SessionFactory sf2 = sessionFactories.get("DBConnectionDomain2");
		Session session = sf1.openSession();
		Session session2 = sf2.openSession();
		try
		{
			if(sf1!=null)
			{
				
				session.beginTransaction();
				robolist = session.createQuery("from Robots").list();
			
				
			}
			if(sf2!=null)
			{
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
		for(int i=0;i < robolist.size();i++ )
		{			
			objRobot = robolist.get(i);
			objAc = new AccessControl();
			String R_ID = objRobot.getRobot_id();
			objAc.setRobot_id(Integer.parseInt(R_ID));
			objAc.setRobot_name(objRobot.getRobot_name());
			
			if(objRobot.getCreated_by() == Integer.parseInt(objUser.getUser_id()) && Integer.parseInt(objUser.getUser_domain_id())==1)
			{
				objAc.setAccess(4);
			}
			else
			{
				acc=4;
				up=new ArrayList<Updates>();
				up = session.createQuery("from Updates where ru_robot_id = " + objRobot.getRobot_id()).list();
				
				for(int j =0; j<up.size();j++)
				{
					int y=0;
					objUpdate=up.get(j);
					if((objUpdate.getRu_domain_id())==1)
					{
						if(Integer.parseInt(objUser.getUser_domain_id())==1)
						{
							List<AccessRights> mapped = session.createQuery("from AccessRights where ar_role_id = "+objUser.getUser_role_id() + "and ar_robot_id ="+objRobot.getRobot_id()).list();
	 						if(!(mapped.isEmpty()))
	 						{
	 							y = (mapped.get(0).getAr_right_id());	
	 						}
							 						
	 					}
						else
						{
							//List<Integer> intermediate = session2.createQuery("Select idm_other_domain_role_id from InterDomain where idm_role_id = "+ objUser.getUser_role_id()).list();
							//Integer x = intermediate.get(0);
							List<Integer> final_role = session.createQuery("Select idm_other_domain_role_id from InterDomain where idm_role_id = "+ objUser.getUser_role_id()).list();
	 						Integer final_role_id = final_role.get(0);
	 						List<AccessRights> mapped = session.createQuery("from AccessRights where ar_role_id = "+final_role_id+ "and ar_robot_id ="+objRobot.getRobot_id()).list();
	 						y = (mapped.get(0).getAr_right_id());
	 						
						}
					}
					else
					{
						if(Integer.parseInt(objUser.getUser_domain_id())==1)
						{
							List<Integer> intermediate = session2.createQuery("Select idm_other_domain_role_id from InterDomain where idm_role_id = "+ objUser.getUser_role_id()).list();
							Integer x = intermediate.get(0);
							List<Integer> final_role = session.createQuery("Select idm_other_domain_role_id from InterDomain where idm_role_id = "+ x).list();
	 						Integer final_role_id = final_role.get(0);
	 						List<AccessRights> mapped = session.createQuery("from AccessRights where ar_role_id = "+final_role_id+ "and ar_robot_id ="+objRobot.getRobot_id()).list();
	 						y = (mapped.get(0).getAr_right_id());
	 						 						
	 					}
						else
						{
							//List<Integer> intermediate = session2.createQuery("Select idm_other_domain_role_id from InterDomain where idm_role_id = "+ objUser.getUser_role_id()).list();
							//Integer x = intermediate.get(0);
							List<Integer> final_role = session.createQuery("Select idm_other_domain_role_id from InterDomain where idm_role_id = "+ objUser.getUser_role_id()).list();
	 						Integer final_role_id = final_role.get(0);
	 						List<AccessRights> mapped = session.createQuery("from AccessRights where ar_role_id = "+final_role_id+ "and ar_robot_id ="+objRobot.getRobot_id()).list();
	 						y = (mapped.get(0).getAr_right_id());
	 						
						}
					}
					
					acc = Math.min(acc, y);
				}
				objAc.setAccess(acc);
				
			}
			if(objAc.getAccess()>1)
			{
			AC.add(objAc);
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return AC;
	}
	public List<AccessControl> Access2(Users a)throws Exception
	{
		int acc;
		List<AccessControl> AC = new ArrayList<AccessControl>();
		List<Robots> robolist = null;
		List<AccessRights> AR = null;
		List<Updates> up = null;
		AccessControl objAc = null;
		Updates objUpdate=null;
		Robots objRobot = null;
		Users objUser = a; // add user information
		SessionFactory sf1 = sessionFactories.get("DBConnectionDomain2");
		SessionFactory sf2 = sessionFactories.get("DBConnectionDomain1");
		Session session2 = sf2.openSession();
		Session session = sf1.openSession();
		try
		{
			if(sf1!=null)
			{
				session.beginTransaction();
				robolist = session.createQuery("from Robots").list();
			}
			if(sf2!=null)
			{
				
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
		for(int i=0;i < robolist.size();i++ )
		{
			objAc = new AccessControl();
			objRobot = robolist.get(i);
			objAc.setRobot_id(Integer.parseInt(objRobot.getRobot_id()));
			objAc.setRobot_name(objRobot.getRobot_name()); 
			if(objRobot.getCreated_by() == Integer.parseInt(objUser.getUser_id()) && Integer.parseInt(objUser.getUser_domain_id())==1)
			{
				objAc.setAccess(4);
			}
			else
			{
				acc=4;
				up=null;
				up = session.createQuery("from Updates where ru_robot_id = " + objRobot.getRobot_id()).list();
				
				for(int j =0; j<up.size();j++)
				{
					int y=0;
					objUpdate=up.get(j);
					if((objUpdate.getRu_domain_id())==2)
					{
						if(Integer.parseInt(objUser.getUser_domain_id())==2)
						{
							List<AccessRights> mapped = session.createQuery("from AccessRights where ar_role_id = "+objUser.getUser_role_id() + "and ar_robot_id ="+objRobot.getRobot_id()).list();
	 						if(!(mapped.isEmpty()))
	 						{
	 							y = (mapped.get(0).getAr_right_id());	
	 						}
							 						
	 					}
						else
						{
							//List<Integer> intermediate = session2.createQuery("Select idm_other_domain_role_id from InterDomain where idm_role_id = "+ objUser.getUser_role_id()).list();
							//Integer x = intermediate.get(0);
							List<Integer> final_role = session.createQuery("Select idm_other_domain_role_id from InterDomain where idm_role_id = "+ objUser.getUser_role_id()).list();
	 						Integer final_role_id = final_role.get(0);
	 						List<AccessRights> mapped = session.createQuery("from AccessRights where ar_role_id = "+final_role_id+ "and ar_robot_id ="+objRobot.getRobot_id()).list();
	 						y = (mapped.get(0).getAr_right_id());
	 						
						}
					}
					else
					{
						if(Integer.parseInt(objUser.getUser_domain_id())==2)
						{
							List<Integer> intermediate = session2.createQuery("Select idm_other_domain_role_id from InterDomain where idm_role_id = "+ objUser.getUser_role_id()).list();
							Integer x = intermediate.get(0);
							List<Integer> final_role = session.createQuery("Select idm_other_domain_role_id from InterDomain where idm_role_id = "+ x).list();
	 						Integer final_role_id = final_role.get(0);
	 						List<AccessRights> mapped = session.createQuery("from AccessRights where ar_role_id = "+final_role_id+ "and ar_robot_id ="+objRobot.getRobot_id()).list();
	 						y = (mapped.get(0).getAr_right_id());
	 						 						
	 					}
						else
						{
							//List<Integer> intermediate = session2.createQuery("Select idm_other_domain_role_id from InterDomain where idm_role_id = "+ objUser.getUser_role_id()).list();
							//Integer x = intermediate.get(0);
							List<Integer> final_role = session.createQuery("Select idm_other_domain_role_id from InterDomain where idm_role_id = "+ objUser.getUser_role_id()).list();
	 						Integer final_role_id = final_role.get(0);
	 						List<AccessRights> mapped = session.createQuery("from AccessRights where ar_role_id = "+final_role_id+ "and ar_robot_id ="+objRobot.getRobot_id()).list();
	 						y = (mapped.get(0).getAr_right_id());
	 						
						}
					}
					
					acc = Math.min(acc, y);
				}
				objAc.setAccess(acc);
				
			}
			if(objAc.getAccess()>1)
			{
			AC.add(objAc);
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return AC;
	}

}
