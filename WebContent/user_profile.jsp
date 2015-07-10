<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ include file="includes/nocache.jsp"%>
<%@ include file="includes/logincheck.jsp"%>
<%@ include file="includes/header.jsp"%>
<%@ include file="includes/common_top.jsp"%>
<div id="page-wrapper">
	<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">User Profile</h1>
    </div>
    <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    Change Password
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
                        <div class="col-lg-6">
                            <form id="userprofileForm" name="userprofileForm" role="form" method="post" action="">
                                <div class="form-group">
                                    <label>User Name :</label>
                                    <input class="form-control" value="" disabled="disabled">
                                </div>
                                <div class="form-group">
                                    <label>Old Password :</label>
                                    <input id="oldpwd" name="oldpwd" class="form-control" type="password">
                                </div>
                                <div class="form-group">
                                    <label>New Password :</label>
                                    <input id="newpwd" name="newpwd" class="form-control" type="password">
                                </div>
                                <div class="form-group">
                                    <label>Confirm New Password :</label>
                                    <input id="cnfnewpwd" name="cnfnewpwd" class="form-control" type="password"/>
                                </div>
                                <input name="userprofilepostbk" type="hidden" id="userprofilepostbk" value="1" />
                                <button type="submit" class="btn btn-default">Save</button>
                                <button type="reset" class="btn btn-default">Reset</button>
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
