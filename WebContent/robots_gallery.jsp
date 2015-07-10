<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@page import="com.utd.robocode.dto.*"%>
<%@page import = "com.utd.robocode.services.*" %>
<%@ include file="includes/nocache.jsp"%>
<%@ include file="includes/logincheck.jsp"%>
<%@ include file="includes/header.jsp"%>
<%@ include file="includes/common_top.jsp"%>
<% 
List<AccessControl> AccDom1 = null;
List<AccessControl> AccDom2 = null;
Users x = new Users();
x=(Users)request.getSession().getAttribute("userObj");
GalleryService gs = new GalleryService();
%>
  <div id="page-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header">Robots Gallery</h1>
      </div>
      <!-- /.col-lg-12 --> 
    </div>
    <!-- /.row -->
    <div class="row">
      <div class="col-lg-12">
        <div class="panel panel-default">
          <div class="panel-heading"> Cross Domain List of Robots </div>
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
<% if(x.getUser_id().equals("1")){ %>
                <div class="row">
                	<div class="col-lg-12" align="center" style="min-height:250px; padding:150px;">
                	<p style="font-size:18px"><strong>Creator Role is unauthorized to view this page.</strong></p>
                	</div>     
                </div>        
<%
}
else
{
AccDom1=(List<AccessControl>) gs.Access1(x); 
AccDom2=(List<AccessControl>) gs.Access2(x);
%>              
				<% if(AccDom1!=null){ %>
                <fieldset class="fieldset_custom">
                 
                  <legend class="fieldset_custom">Domain-1</legend>
                  <table class="table table-striped table-bordered table-hover" style="margin-bottom:0px;">
                    <thead>
                      <tr>
                        <th>Robot Name</th>
                        <th><img src="images/view.png" width="20" height="20" /></th>
                        <th><img src="images/edit.png" width="20" height="20" /></th>
                        <th><img src="images/delete.png" width="20" height="20"/></th>
                      </tr>
                    </thead>
                    <tbody>
                    <%
					if(AccDom1.size()>0)
					{ 
					  int i;
					  for(i=0;i<AccDom1.size();i++)
					  {
						  AccessControl a = new AccessControl();
						  a=AccDom1.get(i);
						  int acc = a.getAccess();
						  boolean view = false;
						  boolean edit=false;
						  boolean delete = false;
						  switch(acc)
						  {	                            	  
						  	case 1: break;
							  case 2: view= true;
							  break;
							  case 3: view = true;
							  edit=true;
							  break;
							  case 4: view = true;
							  edit = true;
							  delete = true;
							  break;
						  }
						  String link_view = "view_robot.jsp?robot_id=" + a.getRobot_id()+"&domain_id=1&prevpg=robots_gallery" ;
	                      String link_edit = "edit_robot.jsp?robot_id=" + a.getRobot_id()+"&domain_id=1&prevpg=robots_gallery";
	                      String link_delete = "delete_robot.jsp?robot_id=" + a.getRobot_id()+"&domain_id=1&prevpg=robots_gallery";
					  %>
                      <tr>
                        <td><% out.println(a.getRobot_name()); %></td>
                        <td><%if(view){ %>
                          <a href="<%=link_view%>"><img src="images/view.png" width="20" height="20" /></a>
                          <%} else { %>
                          <img src="images/disabled.png" width="20" height="20" />
                          <%} %></td>
                        <td><%if(edit){ %>
                          <a href="<%=link_edit%>"><img src="images/edit.png" width="20" height="20" /></a>
                          <%} else { %>
                          <img src="images/disabled.png" width="20" height="20" />
                          <%} %></td>
                        <td><%if(delete){ %>
                          <a href="<%=link_delete%>" onclick="return confirm('Are you sure you want to delete?');"><img src="images/delete.png" width="20" height="20" /></a>
                          <%} else { %>
                          <img src="images/disabled.png" width="20" height="20" />
                          <%} %></td>
                      </tr>
                    <% 
						}
					} else {
					%>
                    <tr>
                        <td colspan="4" align="center">No Data Available</td>
                    </tr>
                    <% } %>
                    </tbody>
                  </table>
                </fieldset>
                <br/>
				<%
                }
                
				if(AccDom2!=null)
                {   
                %>
                <fieldset class="fieldset_custom">
                  <legend class="fieldset_custom">Domain-2</legend>
                  <table class="table table-striped table-bordered table-hover" style="margin-bottom:0px;">
                    <thead>
                      <tr>
                        <th>Robot Name</th>
                        <th><img src="images/view.png" width="20" height="20" /></th>
                        <th><img src="images/edit.png" width="20" height="20" /></th>
                        <th><img src="images/delete.png" width="20" height="20"/></th>
                      </tr>
                    </thead>
                    <tbody>
                     <%
					 if(AccDom2.size()>0)
					 { 
						  int i;
						  for(i=0;i<AccDom2.size();i++)
						  {
							  AccessControl a = new AccessControl();
							  a=AccDom2.get(i);
							  int acc = a.getAccess();
							  boolean view = false;
							  boolean edit=false;
							  boolean delete = false;
							  switch(acc)
							  {	                            	  
							  case 1: break;
							  case 2: view= true;
							  break;
							  case 3: view = true;
							  edit=true;
							  break;
							  case 4: view = true;
							  edit = true;
							  delete = true;
							  break;
							  }
							  String link_view = "view_robot.jsp?robot_id=" + a.getRobot_id()+"&domain_id=2&prevpg=robots_gallery" ;
	                          String link_edit = "edit_robot.jsp?robot_id=" + a.getRobot_id()+"&domain_id=2&prevpg=robots_gallery";
	                          String link_delete = "delete_robot.jsp?robot_id=" + a.getRobot_id()+"&domain_id=2&prevpg=robots_gallery";
						  %>
                      <tr>
                        <td><% out.println(a.getRobot_name()); %></td>
                        <td><%if(view){ %>
                          <a href="<%=link_view%>"><img src="images/view.png" width="20" height="20" /></a>
                          <%} else { %>
                          <img src="images/disabled.png" width="20" height="20" />
                          <%} %></td>
                        <td><%if(edit){ %>
                          <a href="<%=link_edit%>"><img src="images/edit.png" width="20" height="20" /></a>
                          <%} else { %>
                          <img src="images/disabled.png" width="20" height="20" />
                          <%} %></td>
                        <td><%if(delete){ %>
                          <a href="<%=link_delete%>" onclick="return confirm('Are you sure you want to delete?');"><img src="images/delete.png" width="20" height="20" /></a>
                          <%} else { %>
                          <img src="images/disabled.png" width="20" height="20" />
                          <%} %></td>
                      </tr>
                      <% 
						}
					} else {
					%>
                    <tr>
                        <td colspan="4" align="center">No Data Available</td>
                    </tr>
                    <% } %>
                    </tbody>
                  </table>
                  <% } %>
                </fieldset>
                <% } %>
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
