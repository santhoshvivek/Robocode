package com.utd.robocode.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
import com.utd.robocode.dto.Roles;
import com.utd.robocode.dto.Users;
import com.utd.robocode.services.CreateRobotService;
import com.utd.robocode.services.MossRobotService;


/**
 * Servlet implementation class Login
 */
//@WebServlet("/createrobot")
public class CreateRobotStep2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String robotName = req.getParameter("currentRobotName");
		String robotDesc = req.getParameter("currentRobotDesc");

		
		Robots objRobot = new Robots();
		objRobot.setRobot_name(robotName);
		objRobot.setRobot_desc(robotDesc);

		HttpSession session = req.getSession();
		int domain = Integer.parseInt((String) session.getAttribute("domainx"));
		Users objUser = (Users)session.getAttribute("userObj");		
		objRobot.setRobot_code(req.getParameter("code_editor"));
		objRobot.setCreated_date(Calendar.getInstance().getTime());
		objRobot.setUpdated_date(Calendar.getInstance().getTime());
		
		CreateRobotService objCreateRobo = new CreateRobotService();
		
		// Check if the robo with the given name already exist
		boolean checkAlreadyExistingRobo = objCreateRobo.checkExistingRobo(objRobot, domain, objUser);
		
		String errorMsg = "";
		
		
		if(!checkAlreadyExistingRobo)
			objRobot = objCreateRobo.createRobotStepOne(objRobot,domain, objUser);
		else
			errorMsg = "Robot already existing. Use another robo name";
			
		
		// Analyze robot using MOSS
		// Create temp robot file for the MOSS script
		MossRobotService objRoboService = new MossRobotService(objRobot,domain, objUser);
		objRoboService.objRobot = objRobot;
		
		List<Roles> objRoles = new ArrayList<Roles>();
		try{
			objRoles = objCreateRobo.getRoles(objRobot,domain, objUser);
		}catch(Exception ex){
			ex.printStackTrace();
		}
				
		Thread th = new Thread(objRoboService);
		th.start();
		
		if(objRobot != null || objRoles != null){
			req.setAttribute("objCurrentRobot", objRobot);
			req.setAttribute("currentRobotId", objRobot.getRobot_id());
			req.setAttribute("roles", objRoles);
			req.getRequestDispatcher("create_robot3.jsp").forward(req,resp);
			//resp.sendRedirect("create_robot2.jsp");		
		}else{
			//TODO: Redirect to login page
			req.setAttribute("msg_error", errorMsg);
			req.getRequestDispatcher("create_robot.jsp").forward(req,resp);
		}	
	}
}
