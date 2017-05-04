<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <base href="<%=basePath%>">
	<meta charset="UTF-8">
	<title>login</title>
	<meta name="author" content="DeathGhost" />
	<meta name="viewport" content="width=device-width, initial-scale=1"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<meta name="keywords" content="Welcome to the Information Technology Growth Platform"/>
	<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
	<link type="text/css" href="<%=basePath%>img/login.ico" rel="shortcut icon"/>
	<link rel="stylesheet" href="<%=basePath%>css/styles.css" type="text/css" />
		<style type="text/css">
			body{height:600%;overflow:hidden;}
			canvas{z-index:-1;position:absolute;}
		</style>
    </head>
   	<body>
		<h1>用户登录</h1>
		<div class="main-agileinfo">
			<form action="" method="post">
				<input type="text" id="username" name="username" class="name" placeholder="账号/手机号"/>
				<input type="password" id="password" name="password" class="password" placeholder="密码"/>
				<ul>
					<li>
						<input type="checkbox" checked="checked" value=""/>
						<label for="brand1"><span></span>记住密码</label>
					</li>
				</ul>
	            <a href="javascript:void(0)">忘记密码?</a><br/>
				<div class="clear"></div>
				<input type="button" value="Login" id="login"/>
			</form>
		</div>
		<div class="footer-w3l"><p class="agile">Copyright &copy; 2017-2020 版权归个人所有</p></div>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/Particleground.js"></script>
		<script type="text/javascript">
		$(document).ready(function() {
		  //粒子背景特效
		  $('body').particleground({
		    dotColor: '#5cbdaa',
		    lineColor: '#5cbdaa'
		  });
		});
		</script>
		<script type="text/javascript">
        	$(function(){
        		$("#login").click(function(){
        			//得到用户名密码
        			var userName=$("#username").val();
        			var password=$("#password").val();
        			if(userName==""){
        					//验证登录
        					$("#username").css("borderColor","red");
        					$("#username").hide(100);
        					$("#username").show(100);
        				
        			}else if(password==""){
        				//验证登录
        					$("#password").css("borderColor","red");
        					$("#password").hide(100);
        					$("#password").show(100);
        			}
        			else{
        				//执行登录操作前 清空密码框
        				$("#password").val("");
        				$.ajax({
							url:'Auth/login',
							data:{"userName":userName,"userPwd":password},
							type:"post",
							success:function(res){
								  if(res.code=="200"){
									//跳转首页
									window.location.href="Auth/index";
								}else if(res.code=="1001"){
									$("#msg").html("用户名/密码 错误！");
								} 
							},
							error:function(){
								$("#msg").html("后台维护中。。请稍候再试！");
							}
						});
        			}
        		});
        		$("input").keyup(function(){
        			//返回颜色
        			$("#password").css("borderColor","#F0FFFF");
        			$("#username").css("borderColor","#F0FFFF");
        			$("#msg").html("<Br/>");
        		})	
        	})
        </script>
    </body>
</html>