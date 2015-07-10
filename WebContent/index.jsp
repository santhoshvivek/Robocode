<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@page import="com.utd.robocode.dto.*"%>
<%@ include file="includes/nocache.jsp"%>
<%@ include file="includes/logincheck.jsp"%>
<%@ include file="includes/header.jsp"%>
<%

if(request.getAttribute("msg_error") != null)
{	
	msg_error = request.getAttribute("msg_error").toString();
}
%>
<div class="row" style="margin:0 !important">
  <div class="col-md-4 col-md-offset-4">
    <h3 style="font-size:18px;text-align:center;padding-top:30px;"><strong>Robocode Online</strong></h3>
    <div class="login-panel panel panel-default" style="margin-top:10% !important">
      <div class="panel-heading">
        <h3 class="panel-title">Please Sign In</h3>
      </div>
      <div class="panel-body">
        <form id="frmLogin" name="frmLogin" action="login" method="POST" onSubmit="return dologin();">
          <fieldset>
            <div class="form-group">
              <div id="errDiv" align="center" style="color:#FF3333"><%=msg_error%></div>
            </div>
            <div class="form-group">
              <input id="username" name="username" class="form-control" placeholder="Username" type="text" autofocus>
            </div>
            <div class="form-group">
              <input id="password" name="password" class="form-control" placeholder="Password" type="password">
            </div>
            <div class="form-group">
              <label>Domain</label>
              <select class="form-control" id="domain" name="domain">
                <option value="1">Domain-1</option>
                <option value="2">Domain-2</option>
              </select>
            </div>
            <button type="submit" class="btn btn-lg btn-success btn-block">Login</button>
          </fieldset>
          <input name="checkloginpostbk" type="hidden" id="checkloginpostbk"  value="1" />
        </form>
      </div>
    </div>
  </div>
</div>
<%@ include file="includes/footer.jsp"%>
