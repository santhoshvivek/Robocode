<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="includes/nocache.jsp"%>
<%@ include file="includes/logincheck.jsp"%>
<%@ include file="includes/header.jsp"%>
<%@ include file="includes/common_top.jsp"%>
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
        <div class="panel panel-default">
          <div class="panel-heading"> Create your own Robot : Step-1 </div>
          <div class="panel-body">
            <div class="row">
              <div id="msgdiv">
                <p>
                <% 
                
                if(request.getAttribute("msg_error") != null)
                {	
                	msg_error = request.getAttribute("msg_error").toString();
                }
                if(msg_error.length() >0){ %>
                <div align="center" class="errorMsg"><strong><%=msg_error%></strong></div>
                <% } else if(msg_success_sess.length() >0) { %>
                <div align="center" class="successMsg"><strong><%=msg_success_sess%></strong></div>
                <% } %>
                </p>
              </div>
              <div class="col-lg-6">
                <form id="settingsForm" name="settingsForm" role="form" method="post" action="createrobot">
                  <div class="form-group">
                    <label>Robot Name:</label>
                    <input id="robot_name" name="robot_name"  type="text" class="form-control" value=""/>
                  </div>
                  <div class="form-group">
                    <label>Robot Description:</label>
                    <input id="robot_desc" name="robot_desc"  type="text" class="form-control" value=""/>
                  </div>
                  <input name="postbk" type="hidden" id="postbk" value="1" />
                  <button type="submit" class="btn btn-success">Next</button>
                </form>
              </div>
              <!-- /.col-lg-6 (nested) --> 
              
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
