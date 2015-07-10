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
import com.utd.robocode.dto.Users;
import com.utd.robocode.services.LoginService;
import com.utd.robocode.utils.DataStoreUtils;



/**
 * Servlet implementation class Login
 */
//@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String userName = req.getParameter("username");
		String password = req.getParameter("password");
		String domain = req.getParameter("domain");
		
		Users objUser = new Users();
		objUser.setUser_name(userName);
		objUser.setUser_pwd(password);
		objUser.setUser_domain_id(domain);
				
		objUser = new LoginService().isAValidUser(objUser);
		HttpSession session = null;
		if(objUser != null){
			session = req.getSession(true); 
			session.setAttribute("userx",userName);
			session.setAttribute("userObj", objUser);
			session.setAttribute("domainx",domain);
			
			String[] usernameparts = userName.split("@"); 
			String userpackage = usernameparts[0]; 
			session.setAttribute("userpackagex",userpackage);

			resp.sendRedirect("dashboard.jsp");		
		}else{
			//TODO: Redirect to login page
			req.setAttribute("msg_error", "User credentials does not match!!! Try Again");
			req.getRequestDispatcher("index.jsp").forward(req,resp);
		}
		
	}
}
