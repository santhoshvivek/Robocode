package com.utd.robocode.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.bind.v2.TODO;
import com.utd.robocode.utils.DataStoreUtils;
import com.utd.robocode.dto.Robots;
import com.utd.robocode.dto.Users;
import com.utd.robocode.services.CreateRobotService;


/**
 * Servlet implementation class Login
 */
//@WebServlet("/createrobot")
public class CreateRobot extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String robotName = req.getParameter("robot_name");
		String robotDesc = req.getParameter("robot_desc");

		
		Robots objRobot = new Robots();
		objRobot.setRobot_name(robotName);
		objRobot.setRobot_desc(robotDesc);

		HttpSession session = req.getSession();
		int domain = Integer.parseInt((String) session.getAttribute("domainx"));
		Users objUser = (Users)session.getAttribute("userObj");
		
		/*CreateRobotService objCreateRobo = new CreateRobotService();
		
		// Check if the robo with the given name already exist
		boolean checkAlreadyExistingRobo = objCreateRobo.checkExistingRobo(objRobot, domain, objUser);
		
		String errorMsg = "Error!!! Try Again";
		
		
		if(!checkAlreadyExistingRobo)
			objRobot = objCreateRobo.createRobotStepOne(objRobot,domain, objUser);
		else
			errorMsg = "Robot already existing. Use another robo name";*/
			
		if(objRobot != null){
			req.setAttribute("objCurrentRobot", objRobot);			
			// req.getRequestDispatcher("create_robot2.jsp").forward(req,resp);
			resp.sendRedirect("create_robot2.jsp?robotName="+objRobot.getRobot_name()+"&robotDesc="+objRobot.getRobot_desc());		
		}/*else{
			//TODO: Redirect to login page
			req.setAttribute("msg_error", errorMsg);
			req.getRequestDispatcher("create_robot.jsp").forward(req,resp);
		}*/
		
	}
}
