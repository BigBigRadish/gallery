<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="login-bg">
<head>
	<base href=<%=basePath %> />
	<title>网络云盘-用户管理</title>
    
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
    <link href="css/lib/font-awesome.css" type="text/css" rel="stylesheet" />
    
    <!-- this page specific styles -->
    <link rel="stylesheet" href="css/compiled/user-list.css" type="text/css" media="screen" />

    <!-- open sans font -->
    <link href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css' />

    <!--[if lt IE 9]>
      <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script src="js/Calendar/WdatePicker.js" type="text/javascript"></script>
	<script type="text/javascript">
		function formSubmit() {
			var name = $("#name").val();
			var passWd = $("#passWd").val();
			var userName = $("#userName").val();
			var unit = $("#unit").val();
			var email = $("#email").val();
			if (name == "") {
				alert("请先输入用户名！");
				return ;
			} else if (passWd == "") {
				alert("请先输入密码！");
				return ;
			} else if (userName == "") {
				alert("请先输入姓名！");
				return ;
			} else if (unit == "") {
				alert("请先输入单位！");
				return ;
			} else if (email == "") {
				alert("请先输入邮箱！");
				return ;
			} 
			$("#form").submit();
		}
	</script>
	</head>
<body>

    <!-- navbar -->
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <button type="button" class="btn btn-navbar visible-phone" id="menu-toggler">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            
            <a class="brand" href=""><img src="img/logo.png" /></a>

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
    
    

  <!-- sidebar -->
    <div id="sidebar-nav">
        <ul id="dashboard-menu">
            
            <li class="active">
                <div class="pointer">
                    <div class="arrow"></div>
                    <div class="arrow_border"></div>
                </div>
                <a class="dropdown-toggle" href="#">
                    <i class="icon-group"></i>
                    <span>用户管理</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="active submenu">
                	<li><a href="<%=basePath %>user/userList.jhtml" >用户列表</a></li>
                    <li><a href="<%=basePath %>user/userAdd.jhtml" class="active">新建用户</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <!-- end sidebar -->


	<!-- main container -->
    <div class="content">
        
       
        <div class="container-fluid">
            <div id="pad-wrapper" class="new-user">
                <div class="row-fluid header">
                    <h3>新建用户</h3>
                </div>

                <div class="row-fluid form-wrapper">
                    <!-- left column -->
                    <div class="span9 with-sidebar">
                        <div class="container">
                            <form class="new_user_form inline-input" id="form" action="<%=basePath %>user/saveUser.jhtml" method="post"/>
                            	<input type="hidden" value="${entity.id }" name="id"/>
                                <div class="span12 field-box">
                                    <label>用户名:</label>
                                    <input class="span9" type="text" name="name" id="name" value="${entity.name }"/>
                                </div>
                                <div class="span12 field-box">
                                    <label>密码:</label>
                                    <input class="span9" type="password" name="passWd" id="passWd"/>
                                </div>
                                <div class="span12 field-box">
                                    <label>姓名:</label>
                                    <input class="span9" type="text" name="userName" id="userName" value="${entity.userName }"/>
                                </div>
                                <div class="span12 field-box">
                                    <label>单位:</label>
                                    <input class="span9" type="text" name="unit" id="unit" value="${entity.unit }"/>
                                </div>
                                <div class="span12 field-box">
                                    <label>邮箱:</label>
                                    <input class="span9" type="text" name="email" id="email" value="${entity.email }"/>
                                </div>
                                <div class="span11 field-box actions">
                                    <input type="button" class="btn-glow primary" value="确认" onClick="javascript:formSubmit()"/>
                                </div>
                            </form>
                        </div>
                    </div>

                    
                </div>
            </div>
        </div>
    </div>
    <!-- end main container -->


	<!-- scripts -->
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/theme.js"></script>

    <script type="text/javascript">
        $(function () {

            // toggle form between inline and normal inputs
            var $buttons = $(".toggle-inputs button");
            var $form = $("form.new_user_form");

            $buttons.click(function () {
                var mode = $(this).data("input");
                $buttons.removeClass("active");
                $(this).addClass("active");

                if (mode === "inline") {
                    $form.addClass("inline-input");
                } else {
                    $form.removeClass("inline-input");
                }
            });
        });
    </script>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
