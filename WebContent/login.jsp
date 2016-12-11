<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 获取相关路径 -->    
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Page</title>
</head>
<body>
	<center>
		<h1>Please login first</h1><br>
		<!-- 登录时form表单提交给checkUser Action处理 -->
		<form action="<%=basePath%>user!checkUser.action" method="post">
			User ID: <input type="text" name="user.id" style="width: 100px"><br>
			User Name: <input type="text" name="user.name" style="width: 100px"><br>
			<input type="submit" value="Login">
		</form>
	</center>
</body>
</html>