<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%
try
{
	Runtime rt = Runtime.getRuntime();
	Process ps1, ps2 = null;
	ps1 = rt.exec("cmd /K \"cd C:\\ProgFiles\\robocode\\\" && java -Xmx512M -Dsun.io.useCanonCaches=false -cp libs/robocode.jar robocode.Robocode -battle battles/test.battle -results results.txt");
	//ps2 = rt.exec("cmd /c "); 
}
catch(Exception e)
{
	System.out.println("Error Message : " + e.getMessage());
	e.printStackTrace();
}
%>