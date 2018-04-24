<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>客户端注册</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath }/css/styles2.css">
</head>
<script type="text/javascript">
	function Check1() {
		var p1 = document.client_form.client_secret.value;
		var p2 = document.client_form.client_secret1.value;
		if (p1 != p2) {
			alert("两次输入的密码不一致，请重新输入！");
		} else if (p1.length < 5) {
			alert("密码长度太短，请输入长度大于5的密码！")
		} else if (p1.length > 20) {
			alert("密码长度太长，请输入长度小于20的密码")
		}
	}
</script>
<body>
	<div class="htmleaf-container">
		<div class="wrapper">
			<div class="container">
				<h1>客户端账号注册</h1>
				<form name="client_form" class="form"
					action="${pageContext.request.contextPath }/client/send " method="post">
					<table>
						<tr>
							<td><label>客户端名称: </label></td>
							<td><input type="text" id="client_name" name="client_name"></td>
						</tr>
						<tr>
							<td><label>客户端密码: </label></td>
							<td><input type="password" 
								name="client_secret1"></td>
						</tr>
						<tr>
							<td><label>确认密码: </label></td>
							<td><input type="password" 
								name="client_secret" onBlur="Check1()"></td>
						</tr>
						<tr>
							<td><label>重定向地址: </label></td>
							<td><input type="text" id="redirect_url" name="redirect_url"></td>
						</tr>
						<tr>
							<td><label>申请人名字: </label></td>
							<td><input type="text" id="applicant_name"
								name="applicant_name"></td>
						</tr>
						<tr>
							<td><label>身份证: </label></td>
							<td><input type="text" id="id_card" name="id_card"></td>
						</tr>
						<tr>
							<td><label>电话号码: </label></td>
							<td><input type="text" id="phone" name="phone"></td>
						</tr>
						<tr>
							<td><label>客户端功能介绍: </label></td>
							<td><input type="text" id="client_description"
								name="client_description"></td>
						</tr>
						<tr>
							<td><label>申请邮箱: </label></td>
							<td><input type="text" id="email" name="email"></td>
						</tr>
						<tr>
							<td><label>备注: </label></td>
							<td><input type="text" id="others" name="others"></td>
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