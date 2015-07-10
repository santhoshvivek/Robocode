<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import = "javax.servlet.RequestDispatcher" %>
<%@page import="com.utd.robocode.dto.*"%>
<%@page import="com.utd.robocode.services.*" %>
<%@ include file="includes/nocache.jsp"%>
<%@ include file="includes/logincheck.jsp"%>
<%@ include file="includes/header.jsp"%>
<%@ include file="includes/common_top.jsp"%>
<% 
if(request.getParameter("selected_robots")!=null)
{
	String contextrealpath = request.getRealPath("/");
	
	String selected_robots = request.getParameter("selected_robots");
	out.println(selected_robots);
	
	String battleAppletJarFileStr = contextrealpath+"battle_applet\\packaging\\battleapplet.jar";
	System.out.println(battleAppletJarFileStr);
	File battleAppletJarFile = new File(battleAppletJarFileStr);
	if(battleAppletJarFile.exists())
	{
		battleAppletJarFile.delete();
	}
	
	String signedBattleAppletJarFileStr = contextrealpath+"battle_applet\\signedbattleapplet.jar";
	System.out.println(signedBattleAppletJarFileStr);
	File signedBattleAppletJarFile = new File(signedBattleAppletJarFileStr);
	if(signedBattleAppletJarFile.exists())
	{
		signedBattleAppletJarFile.delete();
	}
		
	Runtime rt = Runtime.getRuntime();
	
	Process ps1 = null;
	String cmdcopyrobots = "cmd /k xcopy \"c:\\ProgFiles\\robocode\\robots\" \""+contextrealpath+"battle_applet\\packaging\" /e /i /h /y";
	ps1 = rt.exec(cmdcopyrobots);
	System.out.println(cmdcopyrobots);
	
	Thread.sleep(10000);

	Process ps2 = null;
	try{
	String cmdkeytool = "cmd /k keytool -genkey -noprompt -alias robocodealias1 -dname \"CN=robocode, OU=ID, O=utd, L=robocode, S=online, C=US\" -keystore c:\\ProgFiles\\keystore\\robocodekeystore -storepass password -keypass password";
	ps2 = rt.exec(cmdkeytool);
	System.out.println(cmdkeytool);
	}
	catch(Exception e)
	{
		System.out.println("keystore already exists");
	}
	
	Thread.sleep(2000);

	Process ps3 = null;
	String cmdcreatejar = "cmd /k \"c: && cd "+contextrealpath+"battle_applet\\packaging && jar cf battleapplet.jar * \" ";
	ps3 = rt.exec(cmdcreatejar);
	System.out.println(cmdcreatejar);	
	
	
	Thread.sleep(15000);
	
	Process ps4 = null;
	String cmdjarsigner = "cmd /k jarsigner -keystore \"c:\\ProgFiles\\keystore\\robocodekeystore\" -signedjar \""+contextrealpath+"/battle_applet/signedbattleapplet.jar\" \""+contextrealpath+"/battle_applet/packaging/battleapplet.jar\" -storepass password robocodealias1";
	 
	ps4 = rt.exec(cmdjarsigner);
	System.out.println(cmdjarsigner);
	
	//RequestDispatcher rd = request.getRequestDispatcher("play_battle_ui.jsp");
    //request.setAttribute("selected_robots",selected_robots);
    //rd.forward(request, response);
	
%>
<div id="page-wrapper">
	<div class="row">
	    <div class="col-lg-12">
	        <h1 class="page-header">Play Battle with UI... Processing..</h1>
	    </div>
	    <!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
	    <div class="col-lg-12">
	        <div class="panel panel-default">
	            <div class="panel-heading">
	                Play Battle with UI on your browser</div>
	            <div class="panel-body">
	                <div class="row">
	                	<div id="msgdiv">
	                    <p>
		                <% if(msg_error.length() >0){ %>
		                <div align="center" class="errorMsg"><strong><%=msg_error%></strong></div>
		                <% } else if(msg_success_sess.length() >0) { %>
		                <div align="center" class="successMsg"><strong><%=msg_success_sess%></strong></div>
		                <% } %>
		                </p>
	                    </div>
	                    <div class="col-lg-12">
                        <form id="uiprocessform" name="uiprocessform" action="play_battle_ui.jsp" method="get">
	                        <fieldset class="fieldset_custom">
	                          <legend class="fieldset_custom">&nbsp;</legend>
                                <div align="center" style="height:50px;">Processing...</div>
                                <input id="selected_robots" name="selected_robots" type="hidden" value="<%=selected_robots%>" />
	                        </fieldset>
                        </form>    
	                    </div>
	                    <!-- /.col-lg-12 (nested) -->
	                    
	                </div>
	                <!-- /.row (nested) -->
	            </div>
	            <!-- /.panel-body -->
	        </div>
	        <!-- /.panel -->
	    </div>
	    <!-- /.col-lg-12 -->
	</div>	
</div>
<!-- /#page-wrapper -->  
<script type="text/javascript">
document.getElementById('uiprocessform').submit();
</script>
<%
}
%>
<%@ include file="includes/common_bottom.jsp"%>
<%@ include file="includes/footer.jsp"%>
