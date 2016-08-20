<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    
<!DOCTYPE html>
<html lang="zh-cn">

<head>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<%=basePath%>css/bootstrap.min.css" rel="stylesheet">
<link href="<%=basePath%>css/signin.css" rel="stylesheet">
<title>登录</title>
</head>
<body>

<div class="signin">
	<div class="signin-head"><img src="<%=basePath%>images/head_120.png" alt="" class="img-circle"></div>
	<form class="form-signin" role="form" method="post" action="<%=basePath%>/userinfos/login" >
		<input   type="text" class="form-control" placeholder="用户名" required autofocus name="username"/><label>用户名</label></br>
		<input type="password" class="form-control" placeholder="密码" required name="password" /><label>密码</label></br>
		<input type="text" class="form-control" placeholder="昵称" required autofocus name="username"/><label>昵称</label></br>
		<button class="btn btn-lg btn-warning btn-block" type="submit" >注册</button>
	</form>
</div>
</body>
</html>