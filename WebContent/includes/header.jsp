<% 
String msg_error = "";
String msg_success_sess = "";
if((String) session.getAttribute("msg_success")!=null)
{
	msg_success_sess = (String) session.getAttribute("msg_success");
}
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RoboCode Online</title>

    <!-- Core CSS - Include with every page -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- Page-Level Plugin CSS - Dashboard -->
    <link href="css/plugins/morris/morris-0.4.3.min.css" rel="stylesheet">
    <link href="css/plugins/timeline/timeline.css" rel="stylesheet">

    <!-- SB Admin CSS - Include with every page -->
    <link href="css/sb-admin.css" rel="stylesheet">
	<link href="css/style.css" rel="stylesheet">
     
    <!-- Core Scripts - Include with every page -->
    <script src="js/jquery-1.10.2.js"></script>
    
    <!-- pnotify-start -->
    <link href="js/pnotify-2.0/pnotify.custom.css" rel="stylesheet">
	<script src="js/pnotify-2.0/pnotify.custom.js"></script>
    <!-- pnotify-end -->
    
    <!-- marquee-start -->
    <link href="js/marquee/jquery.marquee.css" rel="stylesheet">
	<script src="js/marquee/jquery.marquee.min.js"></script>
    <!-- marquee-end -->
    
    <!-- ace-editor-start -->
    <link href="js/ace-builds-master/ace_editor.css" rel="stylesheet">
    <!-- ace-editor-end -->
    
    <!-- favicon-start -->
    <link type="image/x-icon" href="favicon.ico" rel="icon"/>
	<link type="image/x-icon" href="favicon.ico" rel="shortcut icon"/>
	<!-- favicon-end -->
</head>
<body>