<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@page import="com.utd.robocode.dto.*"%>
<%@page import="com.utd.robocode.services.*" %>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="javax.tools.*" %>
<%
String domainx_sess = (String) session.getAttribute("domainx");
%>
<%

int max_robots = request.getParamter("max_robots");
int battle_points = max_robots;
for(int k=0; k<max_robots;k++)
{
    String robot_name = robots_win_order.get(k);
    RankingService rks = new RankingService(domainx_sess);
    rks.updatePoints(robot_name,domainx_sess,battle_points);
    battle_points = battle_points-1;
}

%>