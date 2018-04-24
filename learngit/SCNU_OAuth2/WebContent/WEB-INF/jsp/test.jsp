<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
       <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link type="text/css" rel="stylesheet" href="table.css" media="all" />
<title>Insert title here</title>
</head>
<body>
  <table align="center" width="60%" border="1">

<tr>
<td>id </td>
<td>name</td>
</tr>

  <c:forEach items="${list}" var="item">

<tr>
<td>${item.id}</td>
<td>${item.name }</td>
</tr>

</c:forEach>
</tbody>

</table>
</body>
</html>