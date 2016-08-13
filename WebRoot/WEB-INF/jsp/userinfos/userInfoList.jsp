<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">


<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
  $("#btnTest").click(function(){
  $.ajax(
		{
			type:'post',//这里改为get也可以正常执行
			
			contentType: "application/json; charset=utf-8",     
			url:'${pageContext.request.contextPath}/userinfos/queryUserInfosOutJson/lllqs/123',
			data:'{"username":"lqs","password":"123456"}',
			success:function(data){
				alert(data);
			}
			
		}	
	);
 
  });
  $("#btnTest2").click(function(){
  $.ajax(
		{
			type:'post',//这里改为get也可以正常执行
			url:'${pageContext.request.contextPath}/userinfos/queryUserInfosOnlyOutJson/lllqs/123',
			data:'username=lqs&password=123456&aliasName=别名',
			success:function(data){
				alert(data);
			}
			
		}	
	);
 
  });
  
});
</script>
</head>
<body>
商品列表：
<table  class="table">
<tr>
	<td>姓名</td>
	<td>密码</td>
	
</tr>
<c:forEach items="${userinfos }" var="info">
<tr>
	<td>${info.username}</td>
	<td>${info.password}</td>
</tr>
</c:forEach>

</table>
<button id="btnTest">测试json和restful</button>
<button id="btnTest2">测试json输出(参数不是json)和restful</button>
</body>
</html>