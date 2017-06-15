<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="login-bg">
<head>
	<base href=<%=basePath %> />
	
	<title>网络云盘-个人信息</title>
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
    <link rel="stylesheet" href="css/compiled/signin.css" type="text/css" media="screen" />

    <!-- open sans font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script type="text/javascript">
		function formSubmit() {
			var name = $("#name").val();
			var userName = $("#userName").val();
			var unit = $("#unit").val();
			var email = $("#email").val();
			var passWd = $("#passWd").val();
			var toPassWd = $("#toPassWd").val();
			if (name == "") {
				alert("请先输入用户名！");
				return;
			} else if (userName == "") {
				alert("请先输入姓名！");
				return;
			} else if (unit == "") {
				alert("请先输入单位！");
				return;
			} else if (email == "") {
				alert("请先输入邮箱！");
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
<body>

    <!-- navbar -->
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <a class="brand" href=""><img src="img/logo.png" /></a>
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li><a href="<%=basePath %>contacts/list.jhtml">通讯录</a></li>
                    <li><a href="<%=basePath %>notepad/list.jhtml">记事本</a></li>
                    <li><a href="<%=basePath %>favourite/list.jhtml">收藏夹</a></li>
                    <li><a href="<%=basePath %>file/list.jhtml">储物箱</a></li>
                    <li><a href="<%=basePath %>personal.jhtml">个人信息</a></li>
                </ul>
            </div>
            <ul class="nav pull-right">
                <li class="settings hidden-phone">
                    <a href="<%=basePath %>login.jsp" role="button">
                        <i class="icon-share-alt"></i>
                    </a>
                </li>
            </ul>
        </div>
    </div>
    <!-- end navbar -->

	<!-- main container .wide-content is used for this layout without sidebar :)  -->
    <div class="content wide-content">
        <div class="container-fluid">
            <div class="settings-wrapper" id="pad-wrapper">
                <!-- avatar column -->
                <div class="span3 avatar-box">
                </div>

                <!-- edit form column -->
                <div class="span7 personal-info">
                    <div class="alert alert-info">
                        <i class="icon-lightbulb"></i>
                    		    在此界面完善个人信息.
                        <br />让<strong>更加了解你!</strong>
                        <br />注：可以跳过该步骤
                    </div>
                    <h5 class="personal-title">个人信息</h5>

                    <form action="<%=basePath %>personalInfo.jhtml" id="form" method="post">
                    	 <div class="field-box">
                            <label>用户名:</label>
                            <input class="span5 inline-input" type="text"  name="name" id="name" value="${entity.name }"/>
                        </div>
                        <div class="field-box">
                            <label>姓名:</label>
                            <input class="span5 inline-input" type="text"  name="userName" id="userName" value="${entity.userName }"/>
                        </div>
                        <div class="field-box">
                            <label>单位:</label>
                            <input class="span5 inline-input" type="text"  name="unit" id="unit" value="${entity.unit }"/>
                        </div>
                        <div class="field-box">
                            <label>邮箱:</label>
                            <input class="span5 inline-input" type="text"  name="email" id="email" value="${entity.email }"/>
                        </div>
                        <div class="field-box">
                            <label>密码:</label>
                            <input class="span5 inline-input" type="password" name="passWd" id="passWd"/>
                        </div>
                        <div class="field-box">
                            <label>确认密码:</label>
                            <input class="span5 inline-input" type="password" id="toPassWd"/>
                        </div>
                        <div class="span6 field-box actions">
                            <input type="button" class="btn-glow primary" value="保存" onClick="javascript:formSubmit()"/>
                            
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- end main container -->


	<!-- scripts -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>
	<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
