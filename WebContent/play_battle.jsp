<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@page import="com.utd.robocode.dto.*"%>
<%@page import="com.utd.robocode.services.*" %>
<%@ include file="includes/nocache.jsp"%>
<%@ include file="includes/logincheck.jsp"%>
<%@ include file="includes/header.jsp"%>
<%@ include file="includes/common_top.jsp"%>
<% 
List<Robots> robotslist = null;
PlayBattleService ps = new PlayBattleService(domainx_sess,userobj_sess);
robotslist=(List<Robots>)ps.getAllRobotsList(); 
%>
<div id="page-wrapper">
	<div class="row">
	    <div class="col-lg-12">
	        <h1 class="page-header">Play Battle</h1>
	    </div>
	    <!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
	    <div class="col-lg-12">
	        <div class="panel panel-default">
	            <div class="panel-heading">
	                Select Robots and Play Battle</div>
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
	                        <fieldset class="fieldset_custom">
	                          <legend class="fieldset_custom">My Domain Robots</legend>
	                            <table class="table table-striped table-bordered table-hover" style="margin-bottom:0px;">
	                              <thead>
	                                <tr>
	                                  <th width="7%"><div align="center"><input id="selectAllRobot" name="selectAllRobot" type="checkbox" value="1"  onclick="doSelectAllChkbox('Robot');"></div></th>
	                                  <th width="38%">Robot Name</th>
	                                  <th width="55%">Developed By</th>
        						    </tr>
	                              </thead>
	                              <tbody>
	                              <%
								  int rc=0;
								  if(robotslist.size()>0)
								  {
									  for(rc=0;rc<robotslist.size();rc++)
									  {
										  String robot_name = robotslist.get(rc).getRobot_name();
										  int robot_created_by = robotslist.get(rc).getCreated_by();
										  String robot_created_by_name = ps.getUserName(robot_created_by); 
										  String[] robotusernameparts = robot_created_by_name.split("@"); 
										  String robot_userpackage = robotusernameparts[0]; 
	                              %>
	                                <tr>
                                      <td><div align="center"><input id="selectEachRobot<%=rc%>" name="selectEachRobot[]" type="checkbox" value="<% out.print(robot_userpackage+"."+robot_name); %>"></div></td>
	                                  <td><% out.print(robot_name); %></td>
	                                  <td><% out.print(robot_created_by_name); %></td>
	                                </tr>
								 <%
									  }
	                        	 }
								 else
								 {
	                        	 %>
                                 	<tr>
                                      <td colspan="3" align="center">No Data Available</td>
	                                </tr>
                                 <%
								 }
								 %>
	                              </tbody>
	                            </table>
	                            <p>&nbsp;</p>
                                <p><div align="center">
                                  <button type="button" class="btn btn-info" onClick="start_battle_without_ui();">Start Battle without UI</button>
                                  &nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-info" onClick="start_battle_with_ui();">Start Battle with UI</button></div></p>
                                <input id="rowcount" name="rowcount" type="hidden" value="<%=rc%>" />
	                        </fieldset>
                            <form id="battleuiform" name="battleuiform" style="display:none" action="play_battle_ui_process.jsp" method="get">
                            	<input id="selected_robots" name="selected_robots" type="hidden" value="" />
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

function start_battle_with_ui()
{
	var selected_robots=[];
	var robot_selected_count = 0;
	var rowcount = document.getElementById("rowcount").value;
	
		for(var rc=0;rc<rowcount;rc++)
		{
			var selectEachChkbox = "selectEachRobot"+rc;

			if(document.getElementById(selectEachChkbox).checked == true)
			{
				selected_robots.push(document.getElementById(selectEachChkbox).value);
				robot_selected_count = robot_selected_count + 1;
			}

		}
		
	if(robot_selected_count>1)
	{	
		selected_robots_csv = selected_robots.join();
		document.getElementById('selected_robots').value = selected_robots.join();
		
		document.getElementById('battleuiform').submit(); 
	}
	else
	{
		pnotify_call('Play Battle','error','Please select atleast 2 robots to battle.');
	}	

}

function start_battle_without_ui()
{		
	var selected_robots=[];
	var robot_selected_count = 0;
	var rowcount = document.getElementById("rowcount").value;
	
		for(var rc=0;rc<rowcount;rc++)
		{
			var selectEachChkbox = "selectEachRobot"+rc;

			if(document.getElementById(selectEachChkbox).checked == true)
			{
				selected_robots.push(document.getElementById(selectEachChkbox).value+"*");
				robot_selected_count = robot_selected_count + 1;
			}

		}
		
	if(robot_selected_count>1)
	{	
		selected_robots_csv = selected_robots.join();
		document.getElementById('selected_robots').value = selected_robots.join();
		
		pnotify_call('Play Battle','info','Executing Battle On Server..');
		
		$.post("misc/run_battle.jsp",{ selected_robots: selected_robots_csv }, function( data )	
		{
			PNotify.removeAll();
			pnotify_call2('Battle Results','notice',trim(data));	
		}); 
	}
	else
	{
		pnotify_call('Play Battle','error','Please select atleast 2 robots to battle.');
	}
	
}
</script>  
<%@ include file="includes/common_bottom.jsp"%>
<%@ include file="includes/footer.jsp"%>
