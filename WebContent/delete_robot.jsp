<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.utd.robocode.dto.*"%>
<%@page import="com.utd.robocode.services.*" %>
<%@ include file="includes/nocache.jsp"%>
<%@ include file="includes/logincheck.jsp"%>
<%@ include file="includes/header.jsp"%>
<%@ include file="includes/common_top.jsp"%>
<%
//Logic for Delete Robot
String robot_id = "0";
String domain_id = "0";
if(request.getParameter("robot_id")!=null)
{
	robot_id = request.getParameter("robot_id");
}
if(request.getParameter("domain_id")!=null)
{
	domain_id = request.getParameter("domain_id");
}

if(robot_id!=null && domain_id!=null)
{
	DeleteRobotService drs = new DeleteRobotService();
	drs.deleteRobot(robot_id,domain_id);
	
	String msg_success_val="Deleted Successfully";
	session.setAttribute("msg_success",msg_success_val);
}

//Redirect
if(request.getParameter("prevpg")!=null)
{
	String prevpg = request.getParameter("prevpg");
	response.sendRedirect(prevpg+".jsp");
}
%>