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
RankingService rks = new RankingService(domainx_sess);
robotslist=(List<Robots>)rks.getAllRobotsList(); 
%>
<div id="page-wrapper">
	<div class="row">
	    <div class="col-lg-12">
	        <h1 class="page-header">Robot Ranking</h1>
	    </div>
	    <!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
	    <div class="col-lg-12">
	        <div class="panel panel-default">
	            <div class="panel-heading">
	                Robot Ranking for My Domain</div>
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
	                                  <th>Rank</th>
                                      <th>Robot Name</th>
	                                  <th>Developed By</th>
                                      <th>Robot Points</th>
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
										  String robot_created_by_name = rks.getUserName(robot_created_by); 
										  String[] robotusernameparts = robot_created_by_name.split("@"); 
										  String robot_userpackage = robotusernameparts[0];
										  int points = robotslist.get(rc).getRobot_points();
	                              %>
	                                <tr>
	                                  <td><%=rc+1%></td>
                                      <td><% out.print(robot_name); %></td>
	                                  <td><% out.print(robot_userpackage); %></td>
                                      <td><% out.print(points); %></td>
	                                </tr>
								 <%
									  }
	                        	 }
								 else
								 {
	                        	 %>
                                    <tr>
	                                  <td colspan="4" align="center">No Data Available</td>
	                                </tr>
                                 <%
								 }
								 %>
	                              </tbody>
	                            </table>
	                            <p>&nbsp;</p>
                                <input id="rowcount" name="rowcount" type="hidden" value="<%=rc%>" />
	                        </fieldset>
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
<%@ include file="includes/common_bottom.jsp"%>
<%@ include file="includes/footer.jsp"%>
