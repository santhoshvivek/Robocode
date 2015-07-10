<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import = "javax.servlet.RequestDispatcher" %>
<%@page import="com.utd.robocode.dto.*"%>
<%@page import="com.utd.robocode.services.*" %>
<%@ include file="includes/nocache.jsp"%>
<%@ include file="includes/logincheck.jsp"%>
<%@ include file="includes/header.jsp"%>
<%@ include file="includes/common_top.jsp"%>
<div id="page-wrapper">
	<div class="row">
	    <div class="col-lg-12">
	        <h1 class="page-header">About Us</h1>
	    </div>
	    <!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
	    <div class="col-lg-12">
	        <div class="panel panel-default">
	            <div class="panel-heading"></div>
	            <div class="panel-body">
	                <div class="row">
	                	<div id="msgdiv">
	                    <p>
		                <% if(msg_error.length() >0){ %>
		                <div align="center" class="errorMsg"><strong><%=msg_error%></strong></div>
		                <% } else if(msg_success_sess.length() >0) { %>
		                <div align="center" class="successMsg"><strong><%=msg_success_sess%></strong></div>
		                <% } %>
		                </p>
	                    </div>
	                    <div class="col-lg-12">
                        <form id="uiprocessform" name="uiprocessform" action="play_battle_ui.jsp" method="get">
	                        <fieldset class="fieldset_custom">
	                          <legend class="fieldset_custom">&nbsp;</legend>
                                <div align="center">
                                <br/>
                                <p style="font-size:16px"><strong>Robocode Online</strong></p><br/>
                                <strong>Robocode Online</strong> is a project by students of <strong>The University of Texas at Dallas</strong> 
                                <br/>as part of the <strong>Cloud Computing Course CS 6301.501.</strong><br/>
                                <strong>Robocode Online</strong> is a port of Robocode (Desktop Application) to a Web Application and 
                                <br/>has been deployed to <strong>Windows Azure Cloud Platform.</strong>
                                  <br/><br/>                              
                                  Instructor : <strong>Dr. I-Ling Yen</strong> (ilyen@utdallas.edu)<br/>
                                  TA : <strong>Nidhi Solanki</strong> (nidhi.solanki@utdallas.edu)<br/>
                                  <br/>
                                  Student Development Team<br/>
                                  &raquo; <strong>Deepak Pancras</strong> (dxp124330@utdallas.edu)<br/>
                                  &raquo; <strong>Maneesh Abraham</strong> (mea130130@utdallas.edu)<br/>
                                  &raquo; <strong>Santhosh Vivekanandan</strong> (sxv135530@utdallas.edu)<br/>
                                  &raquo; <strong>Archit Trivedi</strong> (amt130330@utdallas.edu)<br/>
                                  &raquo; <strong>Naimish Patel</strong> (njp130330@utdallas.edu)<br/>
                                  <br/>
                                  <strong>Robocode</strong> is an open source educational game started by <strong>Mathew Nelson</strong>.<br/>
                                  Contributions are being made by people including <strong>Flemming N. Larsen</strong> and <strong>Pavel Savara</strong> 
                                  <br/>who work on Robocode to keep it current and fix the bugs. <br>
                                  Website - <a href="http://robocode.sourceforge.net" target="_blank">http://robocode.sourceforge.net/</a> [Source: - <a href="http://en.wikipedia.org/wiki/Robocode" target="_blank">Wikipedia</a>]
                                  <br/>
                                  <br/>
                                  <strong>Robocode Version</strong> [For Robot Compilation and Run Battle on Server] : <strong>1.9.2.0</strong><br/>
                                  <strong>Robocode Version</strong> [To Run Battle UI] : <strong>1.3</strong><br/>                                  
                              </div>
	                        </fieldset>
                        </form>    
	                    </div>
	                    <!-- /.col-lg-12 (nested) -->
	                    
	                </div>
	                <!-- /.row (nested) -->
	            </div>
	            <!-- /.panel-body -->
	        </div>
	        <!-- /.panel -->
	    </div>
	    <!-- /.col-lg-12 -->
	</div>	
</div>
<!-- /#page-wrapper -->  
<%@ include file="includes/common_bottom.jsp"%>
<%@ include file="includes/footer.jsp"%>
