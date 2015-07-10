package com.utd.robocode.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.utd.robocode.dto.Users;
import com.utd.robocode.utils.DataStoreUtils;

public class LoginRepository extends Repository{

	public StringBuilder sbdomain = null;	
	public Users objUser = null;
	public DataStoreUtils dataStoreUtils = new DataStoreUtils();
	public static HashMap<String, SessionFactory> sessionFactories = DataStoreUtils.buildSessionFactory();
	
	public LoginRepository(Users objUser) {
		// TODO Auto-generated constructor stub
		this.objUser = objUser;
		if(objUser.getUser_domain_id().equalsIgnoreCase("1")){
			sbdomain = new StringBuilder("DBConnectionDomain1");
		}else{
			sbdomain = new StringBuilder("DBConnectionDomain2");
		}
	}
	
	public Users loginUsingHibernate() throws Exception{
		SessionFactory sessionFactory = sessionFactories.get(sbdomain.toString());
		try {
			if(sessionFactory != null){
				Session session = sessionFactory.openSession();
				session.beginTransaction();
				List<Users> user = session.createQuery("from Users where user_name ='" + objUser.getUser_name() + "'"
						+ "and user_pwd='"+objUser.getUser_pwd()+"'").list();
				System.out.println(user.size());
				
				if(user.size()!=0)
					objUser = user.get(0); 
				else
					objUser = null;
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally{
			//sessionFactory.close();
		}
		return objUser;
	}
}
