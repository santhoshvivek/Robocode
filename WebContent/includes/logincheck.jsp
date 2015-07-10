<%@page import="com.utd.robocode.dto.*"%>
<%

String uri = request.getRequestURI();
//out.println("uri:"+uri);
String pageName = uri.substring(uri.lastIndexOf("/")+1);
//out.println("pagename:"+pageName);


String userx_sess = (String) session.getAttribute("userx");
String domainx_sess = (String) session.getAttribute("domainx");
Users userobj_sess = (Users)session.getAttribute("userObj");
String userpackagex_sess = (String) session.getAttribute("userpackagex");

	
	if((userx_sess!=null) && ( (pageName.equals("")==true) || (pageName.equals("index.jsp")==true) ))
	{
		response.sendRedirect("dashboard.jsp");
	}
	else if((userx_sess==null) && (pageName.equals("index.jsp")==false))
	{
		response.sendRedirect("index.jsp");
	}


%>
