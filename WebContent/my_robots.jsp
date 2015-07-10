<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.utd.robocode.dto.*"%>
<%@page import = "com.utd.robocode.services.*" %>
<%@page import="java.util.*" %>
<%@ include file="includes/nocache.jsp"%>
<%@ include file="includes/logincheck.jsp"%>
<%@ include file="includes/header.jsp"%>
<%@ include file="includes/common_top.jsp"%>
<%
List<Robots> mr= null;
Users x = new Users();
x=(Users)request.getSession().getAttribute("userObj");
MyRobotsService ms = new MyRobotsService();
mr = (List<Robots>) ms.robots1(x);
%>
  <div id="page-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header">My Robots</h1>
      </div>
      <!-- /.col-lg-12 --> 
    </div>
    <!-- /.row -->
    <div class="row">
      <div class="col-lg-12">
        <div class="panel panel-default">
          <div class="panel-heading">List of My Robots</div>
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
                <table class="table table-striped table-bordered table-hover" style="margin-bottom:0px;">
                  <thead>
                    <tr>
                      <th>Robot Name</th>
                      <th>Description</th>                 
                      <th><img src="images/contributors.png" width="20" height="20" /></th>
                      <th><img src="images/view.png" width="20" height="20" /></th>
                      <th><img src="images/edit.png" width="20" height="20" /></th>
                      <th><img src="images/delete.png" width="20" height="20" /></th>
                    </tr>
                  </thead>
                  <tbody>
                    <% 
					if(mr.size()>0)
    	            {
                        int i,id;
                        for(i=0;i<mr.size();i++)
                        {
                          //Robots a = new Robots();
							String link_contrib="my_robots_contributors.jsp?robot_id="+mr.get(i).getRobot_id()+"&domain_id="+x.getUser_domain_id()+"&robot_name="+mr.get(i).getRobot_name()+"&prevpg=my_robots";
							String link_view="view_robot.jsp?robot_id="+mr.get(i).getRobot_id()+"&domain_id="+x.getUser_domain_id()+"&prevpg=my_robots";
							String link_edit="edit_robot.jsp?robot_id="+mr.get(i).getRobot_id()+"&domain_id="+x.getUser_domain_id()+"&prevpg=my_robots";
							String link_delete="delete_robot.jsp?robot_id="+mr.get(i).getRobot_id()+"&domain_id="+x.getUser_domain_id()+"&prevpg=my_robots";
  		         	%>
                    <tr>
                      <td><% out.print(mr.get(i).getRobot_name());  %></td>
                      <td><% out.print(mr.get(i).getRobot_desc());  %></td>
                      <td><a href="<%=link_contrib%>"><img src="images/contributors.png" width="20" height="20" /></a></td>
                      <td><a href="<%=link_view%>"><img src="images/view.png" width="20" height="20" /></a></td>
                      <td><a href="<%=link_edit%>"><img src="images/edit.png" width="20" height="20" /></a></td>
                      <td><a href="<%=link_delete%>" onclick="return confirm('Are you sure you want to delete?');"><img src="images/delete.png" width="20" height="20" /></a></td>
                    </tr>
                    <% }
					} else { 
					%>
                    <tr>
                      <td colspan="7" align="center">No Data Available</td>
                    </tr>
                    <% } %>
                  </tbody>
                </table>
                
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
<%
session.setAttribute("msg_success","");
%>  
<%@ include file="includes/common_bottom.jsp"%>
<%@ include file="includes/footer.jsp"%>
