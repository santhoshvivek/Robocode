package com.utd.robocode.services;

import java.util.List;

import com.utd.robocode.dto.Robots;
import com.utd.robocode.dto.Users;
import com.utd.robocode.repository.PlayBattleRepository;
import com.utd.robocode.repository.RankingRepository;

public class RankingService {
	
	private RankingRepository objRankingRepo;
	List<Robots> robotslist = null;
	
	public String domain;

	public RankingService(String domain){
		this.domain = domain;
	}
	
	public boolean updatePoints(String name, String domain, int points)
	{
		RankingRepository rr = new RankingRepository(domain);
		return rr.setPoints(name, domain, points);
	}

	public List<Robots> getAllRobotsList()
	{
		try
		{
			objRankingRepo = new RankingRepository(domain);
			robotslist = objRankingRepo.fetchAllRobotsList();
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
			objRankingRepo = new RankingRepository(domain);
			user = objRankingRepo.fetchUserName(user_id);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return user;
	}
	
}
