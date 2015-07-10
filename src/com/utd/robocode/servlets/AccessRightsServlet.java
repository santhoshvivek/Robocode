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
import com.utd.robocode.dto.AccessRights;
import com.utd.robocode.dto.Robots;
import com.utd.robocode.dto.Users;
import com.utd.robocode.services.CreateRobotService;


/**
 * Servlet implementation class Login
 */
//@WebServlet("/createrobot")
public class AccessRightsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		
		HttpSession session = req.getSession();
		int domain = Integer.parseInt((String) session.getAttribute("domainx"));
		Users objUser = (Users)session.getAttribute("userObj");	
		
		String robotName = req.getParameter("currentRobotName");
		String robotDesc = req.getParameter("currentRobotDesc");
		Integer robotId = Integer.parseInt(req.getParameter("currentRobotId"));
		
		List<AccessRights> listAccessRights = null;
		
		// Domain 1
		String viewerRights = req.getParameter("VIEWER");
		String developerRights = req.getParameter("DEVELOPER");
		String managerRights = req.getParameter("MANAGER");
		String contributor = req.getParameter("CONTRIBUTOR");
		
		if(domain == 1){
			listAccessRights = new ArrayList<AccessRights>();
			AccessRights accessRights = new AccessRights();
			accessRights.setAr_domain_id(domain);
			accessRights.setAr_right_id(Integer.parseInt(viewerRights));
			accessRights.setAr_robot_id(robotId);
			accessRights.setAr_role_id(2);
			accessRights.setCreated_date(Calendar.getInstance().getTime());
			accessRights.setUpdated_date(Calendar.getInstance().getTime());
			
			listAccessRights.add(accessRights);
			
			accessRights = new AccessRights();
			accessRights.setAr_domain_id(domain);
			accessRights.setAr_right_id(Integer.parseInt(developerRights));
			accessRights.setAr_robot_id(robotId);
			accessRights.setAr_role_id(3);
			accessRights.setCreated_date(Calendar.getInstance().getTime());
			accessRights.setUpdated_date(Calendar.getInstance().getTime());
			
			listAccessRights.add(accessRights);
			
			accessRights = new AccessRights();
			accessRights.setAr_domain_id(domain);
			accessRights.setAr_right_id(Integer.parseInt(managerRights));
			accessRights.setAr_robot_id(robotId);
			accessRights.setAr_role_id(4);
			accessRights.setCreated_date(Calendar.getInstance().getTime());
			accessRights.setUpdated_date(Calendar.getInstance().getTime());
			
			listAccessRights.add(accessRights);
		}else{
			listAccessRights = new ArrayList<AccessRights>();
			AccessRights accessRights = new AccessRights();
			accessRights.setAr_domain_id(domain);
			accessRights.setAr_right_id(Integer.parseInt(viewerRights));
			accessRights.setAr_robot_id(robotId);
			accessRights.setAr_role_id(2);
			accessRights.setCreated_date(Calendar.getInstance().getTime());
			accessRights.setUpdated_date(Calendar.getInstance().getTime());
			
			listAccessRights.add(accessRights);
			
			accessRights = new AccessRights();
			accessRights.setAr_domain_id(domain);
			accessRights.setAr_right_id(Integer.parseInt(developerRights));
			accessRights.setAr_robot_id(robotId);
			accessRights.setAr_role_id(3);
			accessRights.setCreated_date(Calendar.getInstance().getTime());
			accessRights.setUpdated_date(Calendar.getInstance().getTime());
			
			listAccessRights.add(accessRights);
			
			accessRights = new AccessRights();
			accessRights.setAr_domain_id(domain);
			accessRights.setAr_right_id(Integer.parseInt(managerRights));
			accessRights.setAr_robot_id(robotId);
			accessRights.setAr_role_id(5);
			accessRights.setCreated_date(Calendar.getInstance().getTime());
			accessRights.setUpdated_date(Calendar.getInstance().getTime());
			
			listAccessRights.add(accessRights);
			
			accessRights = new AccessRights();
			accessRights.setAr_domain_id(domain);
			accessRights.setAr_right_id(Integer.parseInt(contributor));
			accessRights.setAr_robot_id(robotId);
			accessRights.setAr_role_id(4);
			accessRights.setCreated_date(Calendar.getInstance().getTime());
			accessRights.setUpdated_date(Calendar.getInstance().getTime());
			
			listAccessRights.add(accessRights);
		}
						
		CreateRobotService objCreateRobo = new CreateRobotService();
		
		try{
			objCreateRobo.assignAccessRightToRobot(listAccessRights, domain);
			
			String msg_success_val="Created Successfully";
			session.setAttribute("msg_success",msg_success_val);
			resp.sendRedirect("my_robots.jsp");
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
