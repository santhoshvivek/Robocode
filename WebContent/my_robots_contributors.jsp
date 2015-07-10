<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.utd.robocode.dto.*"%>
<%@page import = "com.utd.robocode.services.*" %>
<%@ page import="java.util.*" %>
<%@ include file="includes/nocache.jsp"%>
<%@ include file="includes/logincheck.jsp"%>
<%@ include file="includes/header.jsp"%>
<%@ include file="includes/common_top.jsp"%>

<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">My Robots - Contributors</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Contributors for Robot - 
                    <%String robo_name=request.getParameter("robot_name");
                    out.print(robo_name);%>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div id="msgdiv">
                		<% if(msg_error.length()>0){ %>
                		<div align="center" class="errorMsg"><strong><%=msg_error%></strong></div>
                		<% } else if(msg_success_sess.length() >0) { %>
                		<div align="center" class="successMsg"><strong><%=msg_success_sess%></strong></div>
                		<% } %>
              			</div>
                        <div class="col-lg-12">
                                <table class="table table-striped table-bordered table-hover" style="margin-bottom:0px;">
                                <thead>
                                    <tr>
                                    <th>Name</th>
                                    <th>Domain id</th>
                                    <th>Updated Date</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                Users ab = new Users();
                        		ab=(Users)request.getSession().getAttribute("userObj");
                        		String a= request.getParameter("robot_id");
                        		MyRobotsContributorsService service = new MyRobotsContributorsService();
                        		List<Modifier> mr = null;
                        		mr=(List<Modifier>)service.Modif(ab,a);
                        		int i;
                        		for(i=0;i<mr.size();i++)
                        		{
                          		//Robots a = new Robots();
                          		
                  				%>
                                  <tr>
                                     <td><% out.print(mr.get(i).getUser_name());  %></td>
                     				 <td><% out.print(mr.get(i).getRu_domain_id());  %></td>
                    			     <td><% out.print(mr.get(i).getUpdated_date());  %></td>
                                  </tr>
                                  <%} %>
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
<%@ include file="includes/common_bottom.jsp"%>
<%@ include file="includes/footer.jsp"%>
