package com.utd.robocode.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import com.utd.robocode.dto.*;
import com.utd.robocode.repository.PlayBattleRepository;
import com.utd.robocode.utils.DataStoreUtils;

public class PlayBattleService {
	
	private PlayBattleRepository objPlayBattleRepo;
	List<Robots> robotslist = null;
	
	public String domain;
	public Users objUser;

	public PlayBattleService(String domain, Users objUser){
		this.domain = domain;
		this.objUser = objUser;
	}
	
	public List<Robots> getAllRobotsList()
	{
		try
		{
			objPlayBattleRepo = new PlayBattleRepository(domain,objUser);
			robotslist = objPlayBattleRepo.fetchAllRobotsList();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return robotslist;
	}
	
	public String getUserName(int user_id)
	{
		String user = null;
		try
		{
			objPlayBattleRepo = new PlayBattleRepository(domain,objUser);
			user = objPlayBattleRepo.fetchUserName(user_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return user;
	}


}



