<%@page import="com.utd.robocode.dto.Robots"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.utd.robocode.dto.*"%>
<%@page import="com.utd.robocode.services.*"%>
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
GetRobotService grs = new GetRobotService();
robot_code=grs.getRobotCode(robot_id,domain_id);
%>

<div id="page-wrapper">
<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">View Robot</h1>
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
      <div class="panel-heading">View Robot Code</div>
      <div class="panel-body" style="padding:0px;">
        <form id="editorForm" name="editorForm" role="form" method="post" action="createrobot2">
          <div style="width:950px;" class="form-group">
            <pre id="editor"><%=robot_code%></pre>
          </div>
          <textarea id="code_editor" name="code_editor" cols="1" rows="1" style="display:none"></textarea>
          <div align="center" style="margin-bottom:10px;">
            <button type="button" class="btn btn-success" onClick="goback();">Back</button>
          </div>
          
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
editor.setOptions({
    readOnly: true,
    highlightActiveLine: false,
    highlightGutterLine: false
})
editor.renderer.$cursorLayer.element.style.opacity=0;
</script>
<%@ include file="includes/common_bottom.jsp"%>
<%@ include file="includes/footer.jsp"%>
