<%@page import="com.utd.robocode.dto.Roles"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="includes/nocache.jsp"%>
<%@ include file="includes/logincheck.jsp"%>
<%@ include file="includes/header.jsp"%>
<%@ include file="includes/common_top.jsp"%>
<%@page import="com.utd.robocode.dto.Robots"%>
<%
Robots objRobot = new Robots();
if(request.getAttribute("objCurrentRobot") != null)
	objRobot = (Robots)request.getAttribute("objCurrentRobot");

List<Roles> listRoles = new ArrayList<Roles>();

if(request.getAttribute("roles") != null){
	listRoles = (List<Roles>)request.getAttribute("roles");
}

request.setAttribute("objCurrentRobot", objRobot);
%>
<div id="page-wrapper">
	<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Create Robot</h1>
    </div>
    <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Create your own Robot : Step-3
                </div>
                <div class="panel-body">
                    
                        <div id="msgdiv">
                        <p>
                        <% if(msg_error.length() >0){ %>
                        <div align="center" class="errorMsg"><strong><%=msg_error%></strong></div>
                        <% } else if(msg_success_sess.length() >0) { %>
                        <div align="center" class="successMsg"><strong><%=msg_success_sess%></strong></div>
                        <% } %>
                        </p>
                        </div>
                        <div class="table-responsive">
                            <form id="settingsForm" name="settingsForm" role="form" method="post" action="accessRights">
              
                                <table class="table table-bordered table-hover" border="1" style="width:600px;" align="center">
                              <tr>
                                <td colspan="2" bgcolor="#F9F9F9"><div align="center"><strong>Assign Rights Per Robot</strong></div></td>
                              </tr>
                              <tr>
                              <%
                              	for(Roles roles : listRoles){
                              %>
                                <td bgcolor="#F9F9F9"><div align="center"><strong><%=roles.getRole_name() %></strong></div></td>
                                <td><div align="center">
                                  <select name="<%=roles.getRole_name() %>" id="select">
                                    <option value="1">No Rights</option>
                                    <option value="2">Read</option>
                                    <option value="3">Read + Update</option>
                                    <option value="4">Read + Update + Delete</option>
                                  </select>
                                </div></td>
                              </tr>
                              <%
                              	}
                              %>
                              <!-- <tr>
                                <td bgcolor="#F9F9F9"><div align="center"><strong>Developer</strong></div></td>
                                <td><div align="center">
                                  <select name="developer" id="select2">
                                   <option value="1">No Rights</option>
                                    <option value="2">Read</option>
                                    <option value="5">Create + Read</option>
                                    <option value="3">Create + Read + Update</option>
                                    <option value="4">Create + Read + Update + Delete</option>
                                  </select>
                                </div></td>
                              </tr>
                              <tr>
                                <td bgcolor="#F9F9F9"><div align="center"><strong>Manager</strong></div></td>
                                <td><div align="center">
                                  <select name="manager" id="select3">
                                    <option value="1">No Rights</option>
                                    <option value="2">Read</option>
                                    <option value="5">Create + Read</option>
                                    <option value="3">Create + Read + Update</option>
                                    <option value="4">Create + Read + Update + Delete</option>
                                  </select>
                                </div></td>
                              </tr>
                              <tr> -->
                                <td>&nbsp;</td>
                                <td>&nbsp;</td>
                              </tr>
                              
                            </table>
    
                              <div align="center">
                                <button type="submit" class="btn btn-success">Save</button>
                              </div>
                              <input name="postbk" type="hidden" id="postbk" value="1" />
                              <input name="currentRobotName" type="hidden" id="postbk" value=<%=objRobot.getRobot_name()%> />
								<input name="currentRobotDesc" type="hidden" id="postbk" value=<%=objRobot.getRobot_desc()%> />
								<input name="currentRobotId" type="hidden" id="postbk" value=<%=objRobot.getRobot_id()%> />
                          </form>
                        </div>
                        <!-- /.table-responsive -->
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
