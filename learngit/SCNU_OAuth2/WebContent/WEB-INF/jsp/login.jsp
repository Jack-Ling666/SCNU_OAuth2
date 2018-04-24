<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/styles.css">
</head>
<body >
<div class="htmleaf-container">
	<div class="wrapper">
		<div class="container">
			<h1>统一身份认证登录</h1>
			
			<form class="form" action="${list}"  method="post" >
				<input type="text" name="userId"   placeholder="Username">
				<input type="password" name="password"  placeholder="Password">
				<button type="submit" id="login-button">登录</button><br><br><br>
				<a href="${pageContext.request.contextPath }/reg">注册帐号</a>
			</form>
		</div>

	</div>
</div>

<div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';color:#000000">
<h1></h1>
</div>
</body>
</html>