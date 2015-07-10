package com.utd.robocode.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.persistence.Access;

import com.utd.robocode.dto.AccessRights;
import com.utd.robocode.dto.MossResult;
import com.utd.robocode.dto.Robots;
import com.utd.robocode.dto.Users;
import com.utd.robocode.repository.AccessRightsRepository;
import com.utd.robocode.repository.CreateRobotRepository;
import com.utd.robocode.repository.MossCalculationRepository;
import com.utd.robocode.utils.DataStoreUtils;
import com.utd.robocode.utils.URLReader;

public class MossRobotService implements Runnable{
	
	private CreateRobotRepository objRobotRepo = null;
	public Robots objRobot;
	public int domain;
	public Users objUser;
	
	public MossRobotService(Robots objRobot, int domain, Users objUser){
		this.objRobot = objRobot;
		this.domain = domain;
		this.objUser = objUser;
	}

	public void compareRobotCode1(){
		//Process p = null;
		try{			
			/*objRobotRepo = new CreateRobotRepository(objRobot,domain, objUser);
			objRobot = objRobotRepo.doCreateRobotStepOne();*/
			//String[] aCmdArgs = { "perl", "print \"Hello World, Hello Avi\"" };
			Process p = Runtime.getRuntime().exec("cmd /c perl print \"Hello World, Hello Avi\"");			

			BufferedReader output = new BufferedReader (new InputStreamReader(p.getInputStream()));
			String line = output.readLine();
			output.close();

		}catch(Exception ex){

		}	
		System.out.println();
	}

	public void compareRobotCode() throws Exception{
		String[] cmdArr = new String[]{"c:\\ProgFiles\\Perl64\\bin\\perl", "print \"Hello World, Hello Avi\""};
		int returnValue = 0;
		if (cmdArr != null) {
			Process p = null;
			Runtime rt = Runtime.getRuntime();
			try {
				// p = rt.exec(cmdArr);  // throws IOException
				p = Runtime.getRuntime().exec("c:\\ProgFiles\\Perl64\\bin\\perl c:\\ProgFiles\\apps\\moss.pl c:\\ProgFiles\\apps\\hellodei.java C:\\apps\\deidei.java");

				returnValue = p.waitFor();    // throws InterruptedException
			}
			catch (IOException xIo) {
				throw new RuntimeException("Error executing command.",xIo);
			}
			catch (InterruptedException xInterrupted) {
				throw new RuntimeException("Command execution interrupted.",xInterrupted);
			}


			{
				System.out.println("Process Executed Normally");


				InputStreamReader isr = new InputStreamReader(p.getInputStream());
				if(isr != null){
					BufferedReader stdout = null;
					stdout = new BufferedReader(isr);
					String line = null;
					try {
						StringBuffer outputURL = null;
						while ((line = stdout.readLine()) != null) {
							System.out.println(line);
							outputURL = new StringBuffer(line);
						}
						int result = URLReader.fetchResults(outputURL.toString());
					}
					catch (IOException xIo) {
						throw new RuntimeException("Error reading process output", xIo);
					}
				}
			}
		}
	}

	@Override
	public void run(){
		// TODO Auto-generated method stub
		try{
			
			
			// Create temp Robots
			
			
			MossResult mossResult = new MossResult();
			
		
			File file = new File("C:\\ProgFiles\\apps\\hellodei.java");
			PrintWriter writer = new PrintWriter(file, "UTF-8");
			writer.println(objRobot.getRobot_code());
			writer.close();
			
			mossResult.setCompare_domain_id_1(domain);
			mossResult.setCompare_robot_id_1(Integer.parseInt(objRobot.getRobot_id()));
			mossResult.setCompare_robot_name_1(objRobot.getRobot_name());
			
			// Get all robots from DB
			MossCalculationRepository objMossCalculationRepository = new MossCalculationRepository(objRobot, domain, objUser);
			List<Robots> listRobots = objMossCalculationRepository.getAllRobots();
						
			for(Robots robots : listRobots){
				if(!objRobot.getRobot_id().equalsIgnoreCase(robots.getRobot_id())){
					File file1 = new File("C:\\ProgFiles\\apps\\deidei.java");
					writer = new PrintWriter(file1, "UTF-8");
					writer.println(robots.getRobot_code());
					writer.close();
					mossResult.setCompare_domain_id_2(domain);
					mossResult.setCompare_robot_id_2(Integer.parseInt(robots.getRobot_id()));
					mossResult.setCompare_robot_name_2(robots.getRobot_name());
					
					// String[] cmdArr = new String[]{"c:\\Perl64\\bin\\perl", "print \"Hello World, Hello Avi\""};
					int returnValue = 0;
					//if (cmdArr != null) {
						Process p = null;
						Runtime rt = Runtime.getRuntime();
	
						// p = rt.exec(cmdArr);  // throws IOException
						p = Runtime.getRuntime().exec("c:\\ProgFiles\\Perl64\\bin\\perl c:\\ProgFiles\\apps\\moss.pl -l java -b c:\\ProgFiles\\apps\\RobotCodeBaseCode.java c:\\ProgFiles\\apps\\hellodei.java c:\\ProgFiles\\apps\\deidei.java");
	
						returnValue = p.waitFor();    // throws InterruptedException
						System.out.println("Process Executed Normally");
	
	
						InputStreamReader isr = new InputStreamReader(p.getInputStream());
						if(isr != null){
							BufferedReader stdout = null;
							stdout = new BufferedReader(isr);
							String line = null;
							//try {
							StringBuffer outputURL = null;
							while ((line = stdout.readLine()) != null) {
								System.out.println(line);
								outputURL = new StringBuffer(line);
							}
							Integer result = URLReader.fetchResults(outputURL.toString());
							mossResult.setPercentage(result.floatValue());
							
							objMossCalculationRepository.insertMossResult(mossResult);
						}
				}			
			}		
			
		}catch (Exception xIo) {
			throw new RuntimeException("Error reading process output", xIo);
		}
	}
}
