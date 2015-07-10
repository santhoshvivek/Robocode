<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.utd.robocode.dto.*"%>
<%@page import="com.utd.robocode.services.*" %>
<%@ include file="includes/nocache.jsp"%>
<%@ include file="includes/logincheck.jsp"%>
<%@ include file="includes/header.jsp"%>
<%@ include file="includes/common_top.jsp"%>
<%
String robot_id = "0";
String domain_id = "0";
if(request.getParameter("robot_id")!=null)
{
	robot_id = request.getParameter("robot_id");
}
if(request.getParameter("domain_id")!=null)
{
	domain_id = request.getParameter("domain_id");
}

String robot_code = "";
String robot_name = "";
String robot_desc = "";
GetRobotService grs = new GetRobotService();
Robots objR = grs.getRobot(robot_id,domain_id);

robot_code = objR.getRobot_code();
robot_name = objR.getRobot_name();
robot_desc = objR.getRobot_desc();
// robot_code=grs.getRobotCode(robot_id,domain_id);
// robot_name=grs.getRobotName(robot_id,domain_id);
// robot_desc=grs.getRobotDesc(robot_id,domain_id);

if(request.getParameter("postbk")!=null)
{
	domain_id = request.getParameter("domain_id");
	robot_id = request.getParameter("robot_id");
	robot_code = request.getParameter("code_editor");
	Users x = new Users();
	x=(Users)request.getSession().getAttribute("userObj");
	
	UpdateRobotService urs = new UpdateRobotService();
	urs.updateRobot(robot_code,robot_id,domain_id,x);
	
	String msg_success_val="Updated Successfully";
	session.setAttribute("msg_success",msg_success_val);
	
	//Redirect
	if(request.getParameter("prevpg")!=null)
	{
		String prevpg = request.getParameter("prevpg");
		response.sendRedirect(prevpg+".jsp");
	}
}

%>
  <div id="page-wrapper">
    <div class="row">
      <div class="col-lg-12">
        <h1 class="page-header">Edit Robot</h1>
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
          <div class="panel-heading"> Edit Robot and Compile it
            <div style="float:right; margin:-5px;">
              <form>
                <button type="button" class="btn btn-info" onClick="do_compile();">Compile</button>
              </form>
            </div>
          </div>
          <div class="panel-body" style="padding:0px;">
            <form id="editorForm" name="editorForm" role="form" method="post" action="">
              <div style="width:950px;" class="form-group">
                <pre id="editor"><%=robot_code%></pre>
              </div>
              <textarea id="code_editor" name="code_editor" cols="1" rows="1" style="display:none"></textarea>
              <div align="center" style="margin-bottom:10px;">
                <button type="button" class="btn btn-success" onclick="editor_save();">Save</button>
              </div>
              <input name="domain_id" type="hidden" id="domain_id" value="<%=domain_id%>" />
              <input name="robot_id" type="hidden" id="robot_id" value="<%=robot_id%>" />
              <input name="postbk" type="hidden" id="postbk" value="1" />
              <input name="currentRobotName" type="hidden" id="currentRobotName" value="<%=robot_name%>" />
              <input name="currentRobotDesc" type="hidden" id="currentRobotDesc" value="<%=robot_desc%>" />
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
