<%@page import="com.utd.robocode.dto.*"%>
<%@page import="java.util.*" %>
<%@page import="com.utd.robocode.services.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="includes/nocache.jsp"%>
<%@ include file="includes/logincheck.jsp"%>
<%@ include file="includes/header.jsp"%>
<%@ include file="includes/common_top.jsp"%>
<%
List<Robots> AnaRob = null;
Users x = new Users();
x=(Users)request.getSession().getAttribute("userObj");

AnalyzeRobotService ars = new AnalyzeRobotService();
AnaRob=(List<Robots>) ars.Analyze(x); 

Robots objRobot = new Robots();
if(request.getAttribute("objCurrentRobot") != null)
	objRobot = (Robots)request.getAttribute("objCurrentRobot");
boolean selectedRobot = true;
String robotSelected = "";
if(request.getAttribute("selectedRobot") != null)
	robotSelected = request.getAttribute("selectedRobot").toString();

List<MossResult> result = null;
if(request.getAttribute("mossResults") != null)
	result = (List<MossResult>)request.getAttribute("mossResults");

String selectedRobotName="";
%>
<form id="analyzeForm" name="analyzeForm" method="post" action="analyzeRobot">
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Analyze My Robots</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
		
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Compare your robot with other robots in your domain</div>
                <div class="panel-body">
                	<div style="min-height:350px;">
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
                        	<div class="form-group" align="center">
                            Select Robot : 
                              <select id="select_robot" name="select_robot" class="form-control" style="width:auto;display: inline;">
                              <% for( Robots robots : AnaRob ){ 
                              	if(robots.getRobot_id().equalsIgnoreCase(robotSelected)){
									selectedRobotName = robots.getRobot_name();
                              	%>
                                <option value="<%=robots.getRobot_id()%>" selected><%=robots.getRobot_name()%></option> 
                                <% }
                                else{                          	
                                
                                %>
                                <option value="<%=robots.getRobot_id()%>"><%=robots.getRobot_name() %></option>
                                <%} 
                              	}%>
                              </select>
                              <input id="btnsubmit" name="btnsubmit" type="submit" class="btn btn-success" value="Submit"/> 
                            </div>
                            <% if(result != null){ %>  
                            <fieldset class="fieldset_custom">                      
                               <legend class="fieldset_custom"><%=selectedRobotName%></legend> 
                                <table class="table table-striped table-bordered table-hover" style="margin-bottom:0px;">
                                  <thead>
                                    <tr>
                                      <th>Robot Id</th>
                                      <th>Robot Name</th>
                                      <th>Dependency</th>
                                    </tr>
                                  </thead>
                                  <tbody>
                                  <%
								  if(result.size()>0)
								  { 
								  	for(int i = 0 ; i < result.size(); i++)
									{ 
								  %>
                                    <tr>
                                      <td><%=result.get(i).compare_robot_id_2 %></td>
                                      <td><%=result.get(i).compare_robot_name_2 %></td>
                                      <td><%=result.get(i).percentage %>%</td>
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
                                   <% } %> 
                                  </tbody>
                                </table> 
                            </fieldset>
                             <% } %>
                            <br/>
                        </div>
                        <!-- /.col-lg-12 (nested) -->
                        
                    </div>
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
</form>
  <!-- /#page-wrapper -->
<%@ include file="includes/common_bottom.jsp"%>
<%@ include file="includes/footer.jsp"%>
