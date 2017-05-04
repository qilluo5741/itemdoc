<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>我的主页</title>
    <link rel="shortcut icon" href="favicon.ico">
    <link href="item/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="item/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="item/css/animate.css" rel="stylesheet">
    <link href="item/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="item/css/layui/css/layui.css" rel="stylesheet"  media="all"/>
    <link href="item/css/font-awesome.min.css" rel="stylesheet" >
</head>
<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs" style="font-size:20px;text-align:center;">
                                        <strong class="font-bold"><i class="fa fa-linux"></i>&nbsp;&nbsp;item</strong>
                                    </span>
                                </span>
                            </a>
                        </div>
                        <div class="logo-element"><i class="fa fa-linux"></i>item</div>
                    </li>
                     <c:forEach var="mt" items="${menuTypes}">
                      <li>
		             	<a><i class="${mt.menuTypeIcon}"></i>&nbsp;&nbsp;&nbsp;&nbsp;<span class="nav-label">${mt.menuTypeName}</span><span class="label label-warning pull-right">10.0</span></a>
		                  <ul class="nav nav-second-level">
		                     <c:forEach items="${mt.menus}" var="menu">
		                     		<li><a href="<%=basePath%>${menu.url}" target="${menu.target}"><i class="${menu.menuIcon}">&nbsp;&nbsp;${menu.menuName}</i></a></li>
		                     </c:forEach>
		                  </ul>
		              </li>
		             </c:forEach>
		             <li>
		             	<a href=""><i class="fa fa-cogs"></i>&nbsp;&nbsp;&nbsp;<span class="nav-label">我的设置</span><span class="label label-warning pull-right">16.0</span></a>
	                    <ul class="nav nav-second-level">
	                    	 <li><a href="javascript:void(0)" target="menuFrame"><i class="fa fa-linux">&nbsp;&nbsp;&nbsp;&nbsp;修改密码</i></a></li>
	                       	 <li><a href="exit"><i class="fa fa-sign-in">&nbsp;&nbsp;&nbsp;&nbsp;退出登录</i></a></li>
            			</ul>
	                </li>
                </ul>
                 <div class="text-center-folded">
		              <span class="hidden-folded">清除缓存</span><span class="pull-right pull-none-folded">60%</span>
			       </div>
		           <div class="progress progress-xxs m-t-sm dk"  style="height: 4px">
		              <div class="progress-bar progress-bar-info" style="width:60%;"></div>
		           </div>
		           <div class="text-center-folded">
			          <span class="hidden-folded">历史记录</span><span class="pull-right pull-none-folded">35%</span>
				   </div>
				   <div class="progress progress-xxs m-t-sm dk" style="height: 4px">
			           <div class="progress-bar progress-bar-primary" style="width: 35%;"></div>
				   </div>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-info "><i class="fa fa-bars"></i> </a>
                        <form role="search" class="navbar-form-custom" method="post" action="">
                            <div class="form-group">
                                <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">
                            </div>
                        </form>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li class="dropdown">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                                <i class="fa fa-envelope"></i> <span class="label label-warning">16</span>
                            </a>
                            <ul class="dropdown-menu dropdown-messages">
                                <li>
                                    <div class="text-center link-block">
                                        <a  href="item/data/mailbox.html" target="rightFrame">
                                            <i class="fa fa-envelope"></i> <strong> 查看所有消息</strong>
                                        </a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                                <i class="fa fa-bell"></i> <span class="label label-primary">8</span>
                            </a>
                        </li>
                        <li class="dropdown">
                            <a href="exit">
                                <span><i class="fa fa-sign-in"></i>&nbsp;退出</span>
                            </a>
                            </li>
                             <li class="dropdown">
                            <a class="dropdown-toggle count-info" data-toggle="dropdown" href="#">
                                 <span><img src="${user.imageurl}" style="width:38px;height:38px;border-radius:18px;"/><i class="on md b-white bottom"></i></span>
                            </a>
                          </li>
                    </ul>
                </nav>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe id="rightFrame" name="rightFrame" width="100%" height="100%" src="item/data/spinners.html" frameborder="0" data-id="index_v1.html" seamless></iframe>
            </div>
        </div>
        <!--右侧部分结束-->
    </div>

    <!-- 全局js -->
    <script src="item/js/jquery.min.js?v=2.1.4"></script>
    <script src="item/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="item/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="item/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="item/js/plugins/layer/layer.min.js"></script>
    <!-- 自定义js -->
    <script src="item/js/hAdmin.js?v=4.1.0"></script>
    <script type="text/javascript" src="item/js/index.js"></script>
    <!-- 第三方插件 -->
    <script src="item/js/plugins/pace/pace.min.js"></script>
	<div style="text-align:center;">
		<p><a href="javascript:void(0)" target="_blank">版权归属个人</a></p>
	</div>
</body>
</html>