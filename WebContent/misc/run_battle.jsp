<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@page import="com.utd.robocode.dto.*"%>
<%@page import="com.utd.robocode.services.*" %>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.tools.*" %>
<%
String domainx_sess = (String) session.getAttribute("domainx");
%>
<%
//Create Robot File - Start

String robocodeDirStr = "C:\\ProgFiles\\robocode\\";

String battlesDirStr = robocodeDirStr + "battles\\";
File battlesDir = new File(battlesDirStr);
// if the directory does not exist, create it
if (!battlesDir.exists()) {
System.out.println("creating directory: " + battlesDir);
boolean battlesDirMkdirResult = battlesDir.mkdir();  
}

String resultsDirStr = robocodeDirStr + "results\\";
File resultsDir = new File(resultsDirStr);
//if the directory does not exist, create it
if (!resultsDir.exists()) {
System.out.println("creating directory: " + resultsDir);
boolean resultsDirMkdirResult = resultsDir.mkdir();  
}

String selected_robots = request.getParameter("selected_robots");

String UserpackageStr = (String) session.getAttribute("userpackagex");
long unixTime = System.currentTimeMillis() / 1000L;
String timestampvalstr = Long.toString(unixTime);
//String timestampvalstr = "2342344";
//String timestampvalstr = (String)new Timestamp(System.currentTimeMillis();
System.out.println(new Timestamp(System.currentTimeMillis()));

String battleFileStr = UserpackageStr+"_"+timestampvalstr+".battle";
String battleFileFullPathFileStr = battlesDirStr+battleFileStr;
System.out.println(battleFileFullPathFileStr);

String resultsFileStr = UserpackageStr+"_"+timestampvalstr+"_results.txt";
String resultsFileFullPathFileStr = resultsDirStr+resultsFileStr;
System.out.println(resultsFileFullPathFileStr);

//File creation
File battleFileFullPathFile = new File(battleFileFullPathFileStr);
boolean battleFileCreatedResult = battleFileFullPathFile.createNewFile();

//File creation
File resultsFileFullPathFile = new File(resultsFileFullPathFileStr);
boolean resultsFileCreatedResult = resultsFileFullPathFile.createNewFile();

//File appending
Writer objWriter = new BufferedWriter(new FileWriter(battleFileFullPathFileStr));

StringBuilder sb1 = new StringBuilder ();
sb1.append("#Battle Properties\n");
sb1.append("robocode.battleField.width=800\n");
sb1.append("robocode.battleField.height=600\n");
sb1.append("robocode.battle.numRounds=10\n");
sb1.append("robocode.battle.gunCoolingRate=0.1\n");
sb1.append("robocode.battle.rules.inactivityTime=450\n");
sb1.append("robocode.battle.hideEnemyNames=true\n");
sb1.append("robocode.battle.selectedRobots="+selected_robots);
//sb1.append("robocode.battle.selectedRobots=test.mtest12*,test.mtest14*");

String battleFileContent = sb1.toString();
objWriter.write(battleFileContent);
objWriter.flush();
objWriter.close();

//Create Robot File - End



try
{
	System.setProperty("user.dir", robocodeDirStr);
	
	Runtime rt = Runtime.getRuntime();
	Process ps1 = null;
	//String cmdrunbattle = "cmd /k \"cd "+robocodeDirStr+"\" && java -Xmx512M -Dsun.io.useCanonCaches=false -cp libs/robocode.jar robocode.Robocode -battle battles/"+battleFileStr+" -nodisplay -results results/"+resultsFileStr+"";
	//String cmdrunbattle = "cmd /c \"java -Xmx512M -Dsun.io.useCanonCaches=false -cp libs/robocode.jar robocode.Robocode -battle battles/"+battleFileStr+" -nodisplay -results results/"+resultsFileStr+"\"";
	String cmdrunbattle = "cmd /k \"c: && cd "+robocodeDirStr+" && java -Xmx512M -Dsun.io.useCanonCaches=false -cp libs/robocode.jar robocode.Robocode -battle battles/"+battleFileStr+" -nodisplay -results results/"+resultsFileStr+"\"";

	ps1 = rt.exec(cmdrunbattle);
	System.out.println(cmdrunbattle);
	//int outPut = ps1.waitFor();	
	//System.out.println(outPut);
	Thread.sleep(5000);
 	
  BufferedReader br = new BufferedReader(new FileReader(resultsFileFullPathFileStr));
  System.out.println(resultsFileFullPathFileStr);
    try {
        StringBuilder sb2 = new StringBuilder();
        String line = br.readLine();
        
        while (line != null) {
        	System.out.println(line);
			sb2.append(line);
            sb2.append(System.lineSeparator());
            line = br.readLine();
        }
        String everything = sb2.toString();
		
		
		
		
		System.out.println("here:"+everything);
		out.println(everything);
		 
      
		ArrayList<String> robots_win_order = new ArrayList<String>();
		
		String[] dataRows;
		String outputrows = everything;
		
		String delimiter = "\n";
		//given string will be split by the argument delimiter provided.
		dataRows = outputrows.split(delimiter);
		
		//System.out.println("Rows:"+dataRows.length);
		
		int i;
		for(i=0; i < dataRows.length ; i++)
		{
			String outputcols = dataRows[i];
			
			String[] dataCols;
			String delimiter2 = "\t";
			//given string will be split by the argument delimiter provided.
			dataCols = outputcols.split(delimiter2);
			//System.out.println("Cols:"+dataCols.length);
			
			int j;
			for(j=0; j < dataCols.length ; j++)
			{
				if(j==0 && i>1){
					
					//System.out.println("val-"+i+"-val-j-"+j+"-"+dataCols[j].trim());
					//System.out.println("-");
					//out.println(dataCols[j].trim());
					
					String[] resultparts;
					String robotresultval = dataCols[j].trim();
					resultparts = robotresultval.split("\\.");
					String ext_robot_name = resultparts[1];
					String ext_robot_name_clean = ext_robot_name.substring(0,ext_robot_name.length()-1);
					robots_win_order.add(ext_robot_name_clean);	
					
				
				}
			}
		}

		int max_robots = robots_win_order.size();
		int battle_points = max_robots;
		for(int k=0; k<max_robots;k++)
		{
			System.out.println(robots_win_order.get(k));
			String robot_name = robots_win_order.get(k);
			RankingService rks = new RankingService(domainx_sess);
			rks.updatePoints(robot_name,domainx_sess,battle_points);
			battle_points = battle_points-1;
		}
						
		
    } finally {
        br.close();
    }


}
catch(Exception e)
{
	System.out.println("Error Message : " + e.getMessage());
	e.printStackTrace();
}


%>