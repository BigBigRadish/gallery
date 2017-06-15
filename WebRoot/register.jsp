<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="login-bg">
<head>
	<base href=<%=basePath %> />
	
	<title>网络云盘-注册</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <!-- bootstrap -->
    <link href="css/bootstrap/bootstrap.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-responsive.css" rel="stylesheet" />
    <link href="css/bootstrap/bootstrap-overrides.css" type="text/css" rel="stylesheet" />

    <!-- global styles -->
    <link rel="stylesheet" type="text/css" href="css/layout.css" />
    <link rel="stylesheet" type="text/css" href="css/elements.css" />
    <link rel="stylesheet" type="text/css" href="css/icons.css" />

    <!-- libraries -->
    <link rel="stylesheet" type="text/css" href="css/lib/font-awesome.css" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/signup.css" type="text/css" media="screen" />

    <!-- open sans font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript">
		var msg = "${msg}";
		if (msg != "") {
			alert(msg);
		}
		function register() {
			var name = $("#name").val();
			var passWd = $("#passWd").val();
			var toPassWd = $("#toPassWd").val();
			if (name == "") {
				alert("请先输入用户名！");
				return;
			} else if (passWd == "") {
				alert("请先输入密码！");
				return;
			} else if (toPassWd == "") {
				alert("请先输入确认密码！");
				return;
			} else if (passWd != toPassWd) {
				alert("俩次输入的密码不一致，请重新输入！");
				return;
			}
			$("#form").submit();
		}
	</script>
</head>
<body>
    <div class="header">
        <a href="">
            <img src="img/logo.png" class="logo" />
        </a>
    </div>
    <div class="row-fluid login-wrapper">
        <div class="box">
        	<form action="<%=basePath %>register.jhtml" id="form" method="post">
	            <div class="content-wrap">
	                <h6>注册</h6>
	                <input class="span12" type="text" placeholder="用户名" name="name" id="name"/>
	                <input class="span12" type="password" placeholder="输入密码" name="passWd" id="passWd"/>
	                <input class="span12" type="password" placeholder="再次确认" id="toPassWd"/>
	                <div class="action">
	                    <a class="btn-glow primary signup" href="javascript:register()">注册</a>
	                </div>                
	            </div>
            </form>
        </div>

        <div class="span4 already">
            <p>已经拥有一个账号？</p>
            <a href="<%=basePath %>login.jsp">登录</a>
        </div>
    </div>

	<!-- scripts -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
