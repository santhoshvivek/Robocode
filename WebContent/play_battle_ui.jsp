<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*" %>
<%@page import="com.utd.robocode.dto.*"%>
<%@page import="com.utd.robocode.services.*" %>
<%@ include file="includes/nocache.jsp"%>
<%@ include file="includes/logincheck.jsp"%>
<%@ include file="includes/header.jsp"%>
<%@ include file="includes/common_top.jsp"%>
<% 
String selected_robots = "";
if(request.getParameter("selected_robots")!=null)
{
	selected_robots = request.getParameter("selected_robots");
}
%>
<script type="text/javascript">
function writeSummary(summary) {
	summaryElem=document.getElementById("summary");
	summaryElem.innerHTML += "<br>";
	summaryElem.innerHTML += summary;
}
</script>
<script src="http://www.java.com/js/deployJava.js"></script>
<script> 
<!-- ... -->
deployJava.runApplet(attributes,parameters,'1.7'); 
</script> 
<div id="page-wrapper">
	<div class="row">
	    <div class="col-lg-12">
	        <h1 class="page-header">Play Battle with UI</h1>
	    </div>
	    <!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
	    <div class="col-lg-12">
	        <div class="panel panel-default">
	            <div class="panel-heading">
	                Play Battle with UI on your browser
                    <!--<div style="float:right; margin:-5px;">
                    <form>
                    <button type="button" class="btn btn-info" onClick="update_scores();">Update Scores</button>
                    </form>
                    </div>-->
                </div>
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
	                        <fieldset class="fieldset_custom">
	                          <legend class="fieldset_custom">&nbsp;</legend>
                                <noscript>A browser with JavaScript enabled is required for this page to operate properly.</noscript>
                                <applet alt = "Robocode Online" 
                                    code = 'robocode.Robocode'
                                    archive = "signedbattleapplet.jar" 
                                    codebase = "http://localhost:8080/robocode_online/battle_applet" 
                                    width = 800
                                    height = 600 >
                                    <param name="sel_robots" value="<%=selected_robots%>"> 
                                </applet>
                                <br/><br/>
                                <p id="summary"></p>
	                        </fieldset>
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
<script type="text/javascript">
function update_scores()
{
	var battle_results = document.getElementById('summary').innerHTML;
	var battle_results_arr = battle_results.split("<br>==========");
	alert(battle_results_arr[battle_results_arr.length-1]);
}
</script>
<!-- /#page-wrapper -->  
<%@ include file="includes/common_bottom.jsp"%>
<%@ include file="includes/footer.jsp"%>
