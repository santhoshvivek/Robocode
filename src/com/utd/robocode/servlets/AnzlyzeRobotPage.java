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
import com.utd.robocode.utils.DataStoreUtils;
import com.utd.robocode.dto.MossResult;
import com.utd.robocode.dto.Robots;
import com.utd.robocode.dto.Users;
import com.utd.robocode.repository.AnalyzeRobotRepository;
import com.utd.robocode.services.AnalyzeRobotService;
import com.utd.robocode.services.CreateRobotService;


/**
 * Servlet implementation class Login
 */
//@WebServlet("/createrobot")
public class AnzlyzeRobotPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	//	String robotName = req.getParameter("robot_name");
		//String robotDesc = req.getParameter("robot_desc");

		
		Robots objRobot = new Robots();
//		objRobot.setRobot_name(robotName);
	//	objRobot.setRobot_desc(robotDesc);

		HttpSession session = req.getSession();
		int domain = Integer.parseInt((String) session.getAttribute("domainx"));
		Users objUser = (Users)session.getAttribute("userObj");
		
		Integer selectedRobot = Integer.parseInt(req.getParameter("select_robot"));
		
	
		List<MossResult> result = null;
		AnalyzeRobotRepository ars = new AnalyzeRobotRepository(objUser);
		try{
			result = ars.getResult(selectedRobot, objUser);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
			
		if(objRobot != null){
			req.setAttribute("mossResults", result);
			req.setAttribute("objCurrentRobot", objRobot);		
			req.setAttribute("selectedRobot", selectedRobot);
			req.getRequestDispatcher("analyze_my_robots.jsp").forward(req,resp);
			//resp.sendRedirect("create_robot2.jsp");		
		}/*else{
			//TODO: Redirect to login page
			req.setAttribute("msg_error", errorMsg);
			req.getRequestDispatcher("create_robot.jsp").forward(req,resp);
		}*/
		
	}
}
