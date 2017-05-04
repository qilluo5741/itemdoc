<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>菜单角色管理</title>
	<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
  </head>
  <body>
  		<div class="panel panel-warning">
		   <div class="panel-heading">
		      <h3 class="panel-title">角色菜单分配
		       <span onclick="history.go(-1)" style="color: blue;font-size: 12px;cursor:pointer;">&nbsp;&nbsp;&nbsp;<b class="btn btn-info">返回</b></span></h3>
		   </div>
		   <div class="panel-body">
		      	 <div class="panel panel-success" style="width: 45%;float: left;margin-right: 20px;">
				   <div class="panel-heading">
				      <h3 class="panel-title">具备的菜单</h3>
				   </div>
				   <div class="panel-body" style="height: 500px;overflow:auto;">
						<table class="table table-bordered" >
						   <thead>
						      <tr>
						         <th width="50%">菜单名称</th>
						         <th width="40%">菜单类型</th>
						         <th width="10%">移除</th>
						      </tr>
						   </thead>
						   <tbody>
						   
						     <c:forEach items="${ok_menus}" var="m">
						     	 <tr>
							       <td>${m.menuName}</td>
							       <td>${m.menuTypeName}</td>
							       <td align="center">
										<button id="${m.menuId}" type="button" class="btn btn-primary btn-xs remove_">&nbsp;-&nbsp;</button>
									</td> 
							      </tr>
						     </c:forEach>

						   </tbody>
						</table>

				   </div>
				</div>
				 <div class="panel panel-success" style="width: 45%;float: left;">
				   <div class="panel-heading">
				      <h3 class="panel-title">未具备的菜单</h3>
				   </div>
				   <div class="panel-body" style="height: 500px;overflow:auto;">
						<table class="table table-bordered" >
						    <thead>
						      <tr>
						         <th width="50%">菜单名称</th>
						         <th width="40%">菜单类型</th>
						         <th width="10%">添加</th>
						      </tr>
						   </thead>
						   <tbody>
						     <c:forEach items="${no_menus}" var="m">
						     	 <tr>
							       <td>${m.menuName}</td>
							       <td>${m.menuTypeName}</td>
							       <td align="center">
										<button id="${m.menuId}" type="button" class="btn btn-primary btn-xs add_">&nbsp;+&nbsp;</button>
									</td> 
							      </tr>
						     </c:forEach>
							</tbody>
						</table>

				   </div>
				</div>
		   </div>
		</div>
		<script type="text/javascript">
			$(function(){
				$(".add_").click(function(){
					var menuId=$(this).attr("id");
					//添加
					$.ajax({
						url:'permission/addMenu',
						data:{"menuId":menuId,"roleId":"${roleId}"},
						type:"post",
						success:function(res){
							if(res.code==200){
								//刷新页面
								window.location.reload(); 
							}else{
								alert(res.message);
							}
						},
						error:function(){
							alert("后台维护中！");
						}
					});
				});
				//移除
				$(".remove_").click(function(){
					var menuId=$(this).attr("id");
					//remove
					$.ajax({
						url:'permission/removeMenu',
						data:{"menuId":menuId,"roleId":"${roleId}"},
						type:"post",
						success:function(res){
							if(res.code==200){
								//刷新页面
								window.location.reload(); 
							}else{
								alkert(res.message);
							}
						},
						error:function(){
							alert("后台维护中！");
						}
					});
				});
			})
		</script>
  </body>
</html>
