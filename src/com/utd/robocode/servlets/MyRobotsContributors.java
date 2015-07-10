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
import com.utd.robocode.services.MyRobotsContributorsService;
import com.utd.robocode.utils.DataStoreUtils;




/**
 * Servlet implementation class modifiers
 */
//@WebServlet("/modifiers")
public class MyRobotsContributors extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public Users objUser = null;
   
    public MyRobotsContributors() {
	super();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Users ab = new Users();
		ab=(Users)request.getSession().getAttribute("userObj");
		PrintWriter out = response.getWriter();
		String a= request.getParameter("robot_id");
		response.setContentType("text/html");
		MyRobotsContributorsService service = new MyRobotsContributorsService();
		List<Modifier> myrobo = service.Modif(ab,a);
		request.setAttribute("myrobo",myrobo);
		request.getRequestDispatcher("my_robot_contributor.jsp").forward(request,response);
		
	}
}
