<%@page import="com.utd.robocode.dto.Robots"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="includes/nocache.jsp"%>
<%@ include file="includes/logincheck.jsp"%>
<%@ include file="includes/header.jsp"%>
<%@ include file="includes/common_top.jsp"%>
<%
Robots objRobot = new Robots();
objRobot.setRobot_name(request.getParameter("robotName").toString());
objRobot.setRobot_desc(request.getParameter("robotDesc").toString());
/* if(request.getAttribute("objCurrentRobot") != null)
	objRobot = (Robots)request.getAttribute("objCurrentRobot"); */

request.setAttribute("objCurrentRobot", objRobot);


String robotname = request.getParameter("robotName").toString();
%>
<div id="page-wrapper">
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Create Robot</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
            <div id="msgdiv">
            <p>
            <% if(msg_error.length() >0){ %>
            <div align="center" class="errorMsg"><strong><%=msg_error%></strong></div>
            <% } else if(msg_success_sess.length() >0) { %>
            <div align="center" class="successMsg"><strong><%=msg_success_sess%></strong></div>
            <% } %>
            </p>
            </div>
			<div class="panel panel-default">
				<div class="panel-heading">
					Create your own Robot : Step-2
                    <div style="float:right; margin:-5px;">
                    <form>
                    <button type="button" class="btn btn-info" onClick="do_compile();">Compile</button>
                    </form>
                    </div>
				</div>
				<div class="panel-body" style="padding:0px;">
					<form id="editorForm" name="editorForm" role="form" method="post" action="createrobot2">
								<div style="width:950px;" class="form-group">
<pre id="editor">package <%=userpackagex_sess%>;
import robocode.*;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Test1 - a robot by (your name here)
 */
public class <%=robotname%> extends Robot
{
    /**
     * run: Test1's default behavior
     */
    public void run() {
        // Initialization of the robot should be put here

        // After trying out your robot, try uncommenting the import at the top,
        // and the next line:

        // setColors(Color.red,Color.blue,Color.green); // body,gun,radar

        // Robot main loop
        while(true) {
            // Replace the next 4 lines with any behavior you would like
            ahead(100);
            turnGunRight(360);
            back(100);
            turnGunRight(360);
        }
    }

    /**
     * onScannedRobot: What to do when you see another robot
     */
    public void onScannedRobot(ScannedRobotEvent e) {
        // Replace the next line with any behavior you would like
        fire(1);
    }

    /**
     * onHitByBullet: What to do when you're hit by a bullet
     */
    public void onHitByBullet(HitByBulletEvent e) {
        // Replace the next line with any behavior you would like
        back(10);
    }
    
    /**
     * onHitWall: What to do when you hit a wall
     */
    public void onHitWall(HitWallEvent e) {
        // Replace the next line with any behavior you would like
        back(20);
    }	
}
</pre>
	
	

								  
								   
								</div>
								<textarea id="code_editor" name="code_editor" cols="1" rows="1" style="display:none"></textarea>
								<div align="center" style="margin-bottom:10px;">
								<button type="button" class="btn btn-success" onclick="editor_save();">Next</button>
								</div>
								<input name="postbk" type="hidden" id="postbk" value="1" />
								<input name="currentRobotName" type="hidden" id="currentRobotName" value="<%=objRobot.getRobot_name()%>" />
								<input name="currentRobotDesc" type="hidden" id="currentRobotDesc" value="<%=objRobot.getRobot_desc()%>" />
							</form>
						
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
</div>
<!-- /#page-wrapper -->
<script src="js/ace-builds-master/src-noconflict/ace.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    var editor = ace.edit("editor");
    editor.setTheme("ace/theme/crimson_editor");
    editor.getSession().setMode("ace/mode/java");
</script>
<script type="text/javascript">
function editor_save()
{
    document.getElementById("code_editor").value = editor.getValue();
    document.getElementById("editorForm").submit();
}
</script>
<script type="text/javascript">
function do_compile()
{		
    robocode_file_name = document.getElementById("currentRobotName").value;
    robocode_file_content = editor.getValue();

    $.post("misc/compile.jsp",{ robocode_file_name: robocode_file_name, robocode_file_content: robocode_file_content }, function( data ) 		{
      compilestatus = trim(data);
      if(compilestatus == "true")
      {
          pnotify_call('Compile','success','Compilation Successful');	
      }
      else
      {
          pnotify_call('Compile','error','Compilation Failed');
      }
    }); 
    
}
</script>  
<%@ include file="includes/common_bottom.jsp"%>
<%@ include file="includes/footer.jsp"%>

