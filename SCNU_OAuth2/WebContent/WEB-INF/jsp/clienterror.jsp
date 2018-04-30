<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/css/styles.css">
<title>发送失败</title>
</head>
<body>
<div class="htmleaf-container">
	<div class="wrapper">
		<div class="container">
			<form name="client_form" class="form"
					action="${pageContext.request.contextPath }/client//register " method="get">
					<table>
						
						<tr>
							<td><a>邮件发送失败，请点击返回注册页面</a></td><td></td>
							
						</tr>
						<tr>
							
							<td><button type="submit" id="login-button">返回</button></td>
						</tr>
					</table>
				</form>


		</div>

	</div>
</div>



</body>
</html>