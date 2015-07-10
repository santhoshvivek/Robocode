package com.utd.robocode.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.bind.v2.TODO;
import com.utd.robocode.dto.*;
import com.utd.robocode.services.MyRobotsService;
import com.utd.robocode.utils.DataStoreUtils;




/**
 * Servlet implementation class my_robots
 */
//@WebServlet("/MyRobots")
public class MyRobots extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Users objUser = null;
    public MyRobots(){
	super();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		response.setContentType("text/html");
		Users a = new Users();
		a=(Users)request.getSession().getAttribute("userObj");
		PrintWriter out = response.getWriter();
		MyRobotsService service = new MyRobotsService();
		List<Robots> myrobo = service.robots1(a);
		//request.setAttribute("Dom1",AccDom1);
		request.getRequestDispatcher("my_robots.jsp").forward(request,response);
	}
}
