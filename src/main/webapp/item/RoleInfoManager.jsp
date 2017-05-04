<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>角色管理</title>
    <link href="http://libs.baidu.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet">
    <script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
    <script src="http://libs.baidu.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  </head>
  <body style='padding:20px 0 0 50px'>
<button class="btn btn-primary" data-toggle="modal" data-target="#myModal" id="addBtn" >添加角色</button>
<table class="table table-striped">
   <thead>
      <tr>
         <th>角色名字</th>
         <th>操作</th>
      </tr>
   </thead>
   <tbody>
   <c:forEach items="${roles}" var="role">
      <tr>
         <td class="roleName" width="50%">${role.roleName}</td>
         <td class="menusManager">
         	<button id="${role.roleId}" type="button" class="btn btn-success update_">修改</button>
			<button id="${role.roleId}" type="button" class="btn btn-danger delete_" >删除</button>
			<button id="${role.roleId}" type="button" class="btn btn-info manager_" >管理</button>
		</td>
      </tr>
   </c:forEach>
   </tbody>
</table>
<script type="text/javascript">
	$(function(){
		//修改改变该文字状态
		$(".update_").click(function(){
			//得到文本对象
			var $testObj=$(this).parent().parent().children(".roleName");
			//得到原始值
			var name=$testObj.text();
			//嵌入文本框
			var testVal="<div style='width: 100%'> <div class='input-group'> " +
			"<input type='text' class='form-control ' value='"+name+"'>" +
			"<span class='input-group-btn'> <button class='btn btn-default' id='"+$(this).attr("id")+"' onclick='javascript:$.confirm(this)' type='button'>确定  </button>" +
			" <button class='btn btn-default' type='button' val_id="+name+" onclick='javasscript:$.cancel(this);'> 取消  </button> " +
			"</span> </div> </div>";
			//设置值
			$testObj.html(testVal);
			//禁用修改按钮
			$(this).attr("disabled","disabled");
		});
		//点击取消
		$.cancel=function(obj){
			var $del_=$(obj).parent().parent().parent().parent(".roleName");
			$del_.html($(obj).attr("val_id"));
			$del_.next().children(".update_").removeAttr("disabled");
		}
		//点击确认
		$.confirm=function(obj){
			//得到id
			var id=$(obj).attr("id");
			//得到修改的值
			var val=$(obj).parent().parent().children("input").val();
			//验证修改的值
			if(val==""){
				alert("角色名字不能为空！");
			}else{
				//执行修改
				$.ajax({
					url:'permission/updateRoleName',
					data:{"roleId":id,"roleName":val},
					type:"post",
					success:function(res){
						if(res.code==200){
							var $del_=$(obj).parent().parent().parent().parent(".roleName");
							$del_.html(val);
							$del_.next().children(".update_").removeAttr("disabled");
						}else{
							alert(res.message);
						}
					},
					error:function(){
						alert("后台维护中！");
					}
				});
			}
		}
		//删除
		$(".delete_").click(function(){
			if(confirm("你确定要删除吗？")){
				var $rem=$(this).parent().parent();
				$.ajax({
				url:'permission/deleteRole',
				data:{"roleId":$(this).attr("id")},
				type:"post",
				success:function(res){
					if(res.code==200){
						//得到当前这一行对象删除
						$rem.remove();
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
		//添加角色
		$("#addSbm").click(function(){
			//得到参数值
			var roleName=$("#roleName").val();
			//验证参数是否合法
			if(roleName==""){
				alert("角色名字不能为空哟！");
			}else{
				//添加菜单
				$.ajax({
					url:'permission/addRole',
					data:{"roleName":roleName},
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
		$(".manager_").bind("click",function(){
			window.location.href="permission/RoleMenuView?roleId="+$(this).attr("id");
		})
	});
</script>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog">
      <div class="modal-content">
         <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title" id="myModalLabel">添加角色</h4>
         </div>
         <div class="modal-body">
			 <input id="roleName" type="text"  placeholder="请输入角色名称！" class="form-control">
         </div>
         <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal" id="quxiao">关闭 </button>
            <button type="button" class="btn btn-primary"  id="addSbm">提交</button>
         </div>
      </div>
</div>
</div>
  </body>
</html>
