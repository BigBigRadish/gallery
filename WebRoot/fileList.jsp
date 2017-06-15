<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html class="login-bg">
<head>
	<base href=<%=basePath %> />
	<title>网络云盘-通讯录</title>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	<script src="js/jquery.js" type="text/javascript"></script>
	<script src="js/artDialog/jquery.artDialog.js?skin=default"></script>
	
		<script type="text/javascript">
			// 上传
			function addFile() {
				var html = '<form action="<%=basePath %>file/save.jhtml" method="post" enctype="multipart/form-data" id="form">\
					标题：<input type="text" name="title" id="title"/><br/>\
					选择文件：<input type="file" name="file" id="file"/>\
					</form>';
				var dialog = art.dialog({title:"上传", content:html, lock:true, opacity:0.3, width:"300px", height:"auto", button:[{name:"确定", callback:function () {
	    			var title = $("#title").val();
	    			var file = $("#file").val();
	    			if (title == "") {
	    				alert("请先输入标题！")
	    				return false;
	    			} else if (file == "") {
	    				alert("请先选择文件！");
	    				return false;
	    			}
	    			$("#form").submit();
				}, focus:true}]});
			}
		</script>
	</head>
<body>
<!-- navbar -->
    <div class="navbar navbar-inverse">
        <div class="navbar-inner">
            <button type="button" class="btn btn-navbar visible-phone"" id="menu-toggler">
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
                    <span>通讯录</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="<%=basePath %>contacts/list.jhtml">联系人列表</a></li>
                    <li><a href="<%=basePath %>contacts/updatePage.jhtml">新建联系人</a></li>
                    <li><a href="<%=basePath %>contacts/groupList.jhtml">联系人分组列表</a></li>
                </ul>
            </li>
            
            
              <li >
                <a class="dropdown-toggle" href="#">
                    <i class="icon-picture"></i>
                    <span>记事本</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="<%=basePath %>notepad/list.jhtml">记事本列表</a></li>
                    <li><a href="<%=basePath %>notepad/add.jhtml">新建记事本</a></li>
                </ul>
            </li>
            
            
            <li >
                <a class="dropdown-toggle" href="#">
                    <i class="icon-picture"></i>
                    <span>收藏夹</span>
                    <i class="icon-chevron-down"></i>
                </a>
                <ul class="submenu">
                    <li><a href="<%=basePath %>favourite/list.jhtml" class="active">收藏夹列表</a></li>
                    <li><a href="<%=basePath %>favourite/add.jhtml">新建收藏</a></li>
                </ul>
            </li>
             
            <li class="active">
                <a href="<%=basePath %>file/list.jhtml">
                    <i class="icon-calendar-empty"></i>
                    <span>储物箱</span>
                </a>
            </li>
            
          
            <li>
                <a href="<%=basePath %>personal.jhtml">
                    <i class="icon-cog"></i>
                    <span>个人信息</span>
                </a>
            </li>
          
        </ul>
    </div>
    <!-- end sidebar -->
    

	<!-- main container -->
    <div class="content">
        
        <!-- settings changer -->
        
        
        <div class="container-fluid">
            <div id="pad-wrapper" class="users-list">
                <div class="row-fluid header">
                    <h3>文件列表</h3>
                    <div class="span10 pull-right">
                        <!-- custom popup filter -->
                        <!-- styles are located in css/elements.css -->
                        <!-- script that enables this dropdown is located in js/theme.js -->
                 

                        <a href="javascript:addFile()" class="btn-flat success pull-right">
                            <span>&#43;</span>
                            上传
                        </a>
                    </div>
                </div>

                <!-- Users table -->
                <div class="row-fluid table">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th class="span3 sortable">
                                    <span class="line"></span>标题
                                </th>
                                <th class="span3 sortable">
                                    <span class="line"></span>内容
                                </th>
                                <th class="span2 sortable">
                                    <span class="line"></span>时间
                                </th>
                                <th class="span3 sortable align-right">
                                    <span class="line"></span>操作
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                        <!-- row -->
                       	<c:forEach items="${list }" var="var">
	                        <tr class="first">
	                            <td>
	                                <span class="name">${var.title }</span>
	                            </td>
	                            <td>
	                               ${var.content }
	                            </td>
	                            <td>
	                               ${var.time }
	                            </td>
	                            <td class="align-right">
	                                <a href="${var.content }" target="_blank">下载</a>
	                                <a href="<%=basePath %>file/delete.jhtml?id=${var.id}">删除</a>
	                            </td>
	                        </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <!-- end users table -->
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
