<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/styles2.css">
</head>
<body >
<div class="htmleaf-container">
	<div class="wrapper">
		<div class="container">
			<h1>用户注册账号</h1>
			
		<form class="form" action="${pageContext.request.contextPath }/register"  method="post" >
			<table>
				<tr>
         	<td><label>学号: </label></td>
             <td><input type="text" name="userId" ></td>
         </tr>
         <tr>
         	<td><label>用户名: </label></td>
             <td><input type="text" id="userName" name="userName" ></td>
         </tr>
         <tr>
         	<td><label>密  码: </label></td>
             <td><input type="password" id="password" name="password" ></td>
         </tr>
         <tr>
         	<td><label>单位（高校）: </label></td>
             <td><input type="text" id="user_dept1" name="user_dept1" ></td>
         </tr>
          <tr>
         	<td><label>部门（学院）: </label></td>
             <td><input type="text" id="user_dept2" name="user_dept2" ></td>
         </tr>
          <tr>
         	<td><label>用户身份: </label></td>
             <td><input type="text" id="user_kind" name="user_kind" ></td>
         </tr>          
          <tr>
         	<td><label>邮箱: </label></td>
             <td><input type="text" id="email" name="email" ></td>
         </tr>
         <tr>
         	<td><label>电话: </label></td>
             <td><input type="text" id="phone" name="phone" ></td>
         </tr>
          <tr>
         	<td><label>其他(备注): </label></td>
             <td><input type="text" id="others" name="others" ></td>
         </tr>           
         <tr>
         <td></td>
             <td><button type="submit" id="login-button">提交</button></td>          
         </tr>
         </table>
			</form>
	
	</div>
</div>
</div>
</body>
</html>