package com.utd.robocode.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.*;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.bind.v2.TODO;
import com.utd.robocode.utils.DataStoreUtils;
import com.utd.robocode.dto.*;
import com.utd.robocode.services.GalleryService;


/**
 * Servlet implementation class Gallery
 */
@WebServlet("/Gallery")
public class Gallery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Gallery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub		    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		Users a = new Users();
		a=(Users)request.getSession().getAttribute("userObj");
		PrintWriter out = response.getWriter();
		GalleryService service = new GalleryService();
		List<AccessControl> AccDom1 = service.Access1(a);
		List<AccessControl> AccDom2 = service.Access2(a);
		request.setAttribute("Dom1",AccDom1);
		request.setAttribute("Dom2", AccDom2);
		request.getRequestDispatcher("robots_gallery.jsp").forward(request,response);
		
		
	}

}
