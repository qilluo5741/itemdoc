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
    <title>用户管理</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
	<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js" type="text/javascript"></script>
	<script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js" type="text/javascript"></script>
  </head>
  <body style='padding:20px 0 0 50px'>
  <button class="btn btn-success" data-toggle="modal" data-target="#myModal" id="addBtn" >创建用户</button>
  		<table class="table table-striped">
		   <thead>
		      <tr>
		         <th>用户账号</th>
		         <th>用户角色</th>
		         <th>创建时间</th>
		         <th>管理</th>
		      </tr>
		   </thead>
		   <tbody>
		    	<c:forEach  items="${cuser}" var="u">
		    		  <tr>
				         <td>${u.userAccount}</td>
				         <td>
				         	<c:if test="${empty u.roleName}">
				         		未分配角色
				         		<button id="${u.userId}" name="${u.roleId}" type="button"
								class="btn btn-info btn-xs updateRole_">分配</button>
						</c:if>
				         	<c:if test="${not empty u.roleName}">
				         		${u.roleName}
				         		<button id="${u.userId}"  name="${u.roleId}" type="button" class="btn btn-info btn-xs updateRole_">修改</button>
				         	</c:if>
				         </td>
						<td>${u.createTime}</td>
				        <td>
						<button id="${u.userId}" type="button" class="btn btn-danger deletePwd">删除用户</button> 
						<button id="${u.userId}" type="button" class="btn btn-warning resetPwd">重置密码 </button>
					</td>
				      </tr>
		    	</c:forEach>
		   </tbody>
		</table>
	 <script type="text/javascript">
		$(function(){
			//重置密码
			$(".resetPwd").click(function(){
				//得到当前的userId
				var userId=$(this).attr("id");
				if(confirm("确认重置密码吗？重置密码后新密码为‘123456’。")){
					$.ajax({
						url:"permission/resetPwd",
						type:"POST",
						data:{"userId":userId},
						dataType:"json",
						success:function(res){
							if(res.code==200){
								window.location.reload();
							}else{
								alert(res.message);
							}
						},
						error:function(res){
							alert("后台维护中！！");
						}
					});
				}
			});
			//删除用户
			$(".deletePwd").click(function(){
				//得到当前的userId
				var userId=$(this).attr("id");
				if(confirm("确认删除用户吗？")){
					$.ajax({
						url:"permission/deleteUser",
						type:"POST",
						data:{"userId":userId},
						dataType:"json",
						success:function(res){
							if(res.code==200){
								window.location.reload();
							}else{
								alert(res.message);
							}
						},
						error:function(res){
							alert("后台维护中！！");
						}
					});
				}
			});
			//修改
			$(".updateRole_").click(function(){
				$("#roleVal").val($(this).attr("name"));
				$("#update_r").attr("name",$(this).attr("name"));
				//用户id
				$("#update_r").attr("title",$(this).attr("id"));
				$("#updateShowRole_").click();
			});
			//提交修改角色
			$("#update_r").click(function(){
				$.ajax({
					url:"permission/updateRole",
					type:"POST",
					data:{"userId":$(this).attr("title"),"roleId":$("#roleVal").val()},
					dataType:"json",
					success:function(res){
						if(res.code==200){
							window.location.reload();
						}else{
							alert(res.message);
						}
					},
					error:function(res){
						alert("后台维护中！！");
					}
				});
			});
			//添加用户
			$("#addSbm").click(function(){
				//得到参数值
				var userAccount=$("#userAccount").val();
				var userPassword=$("#userPassword").val();
				//验证参数是否合法
				if(userAccount==""||userPassword==""){
					alert("用户名或密码不能为空!");
				}else{
					//添加菜单
					$.ajax({
						url:'permission/addcreateUser',
						data:{"userAccount":userAccount,"userPassword":userPassword},
						type:"post",
						success:function(res){
							if(res.code==200){
								//方案1：自定追加添加一列值
								 //方案2:刷新页面
								window.location.reload(); 
							}else{
								alert(res.message);
							}
						},
						error:function(){
							alert("后台维护中！");
						}
					});
				}
			});
		})
	</script>	
	
	<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">创建用户</h4>
         </div>
         <div class="modal-body">
			<b>用户账号：</b>
			 <input id="userAccount" type="text"  placeholder="请输入账号" class="form-control">
			<b>用户密码：</b>
			  <input id="userPassword" type="password"  placeholder="请输入密码" class="form-control">
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="quxiao">关闭 </button>
					<button type="button" class="btn btn-primary" id="addSbm">提交</button>
			</div>
      </div>
</div>
</div>	
<!-- 按钮触发模态框 -->
<button class="btn btn-primary btn-lg" id="updateShowRole_" data-toggle="modal"  style="display: none;"  data-target="#updateShowRole"> </button>
<!-- 模态框（Modal） -->
<div class="modal fade" id="updateShowRole" tabindex="-1" role="dialog" 
   aria-labelledby="updateShowRolelLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" 
               data-dismiss="modal" aria-hidden="true">
                  &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
              	修改角色
            </h4>
         </div>
         <div class="modal-body">
			      <select id="roleVal" class="form-control">
			         <c:forEach items="${roles}" var="r">
			         	<option value="${r.roleId}">${r.roleName}</option>
			         </c:forEach>
			      </select>
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" 
               data-dismiss="modal">关闭
            </button>
            <button id="update_r" title="" name="" type="button" class="btn btn-primary">
               		提交更改
            </button>
         </div>
      </div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
<!-- 按钮触发模态框 -->
<button class="btn btn-primary btn-lg" data-toggle="modal" id="showRemark_" style="display: none;" data-target="#showRemark">
</button>
<!-- 按钮触发模态框 -->
<button class="btn btn-primary btn-lg" data-toggle="modal" id="authComm_" style="display: none;" data-target="#autoComm">授权停车场</button>
<!-- 模态框（Modal） -->
<div class="modal fade" id="autoComm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" id="authCommClose" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">用户授权
                <span style="color: red;font-size: 13px;">* 如果给用户授权后，当前用户只能查看个人数据.如果不授权就无法查看主要信息</span>
                </h4>
            </div>
            <div id="authBody" class="modal-body"></div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" name="" id="addAuth" class="btn btn-primary">提交更改</button>
            </div>
        </div>
    </div>
</div>
  </body>
</html>
