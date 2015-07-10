package com.utd.robocode.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import com.utd.robocode.dto.*;
import com.utd.robocode.repository.GalleryRepository;
import com.utd.robocode.utils.DataStoreUtils;

public class GalleryService {
	private GalleryRepository objGallRepo;
	List<AccessRights> access = null;
	List<AccessControl> Ac = null;
	int user_id;
	/*public List<AccessRights> Ac1()
	{
		try{
			access = objGallRepo.RobotsGalleryUsingHibernate1(user_id); 			
		}catch(Exception ex){
			
		}	
		return access; 
	}
	public List<AccessRights> Ac2()
	{
		try{
			access = objGallRepo.RobotsGalleryUsingHibernate2(user_id); 			
		}catch(Exception ex){
			
		}	/
		return access; 
	}
	public Roles role()
	{
		Roles objRoles = objGallRepo.UserRoleforGallery();
		return objRoles;
		
	}
	*/
	public List<AccessControl> Access1(Users x)
	{
		try
		{
			objGallRepo = new GalleryRepository();
			Ac = objGallRepo.Access1(x);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Ac;
	}
	public List<AccessControl> Access2(Users x)
	{
		try
		{
			objGallRepo = new GalleryRepository();
			Ac = objGallRepo.Access2(x);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Ac;
	}
}



