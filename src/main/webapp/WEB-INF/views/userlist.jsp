<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UserList</title>
</head>
<body>
<table border="1">
	<tr>
		<th>사용자</th>
		<th>친구목록</th>
	</tr>
	<c:forEach var="user" items="${users}">
	<tr>
		<td>${user.userId}</td>
		<td>
			<c:forEach var="friend" items="${user.friends}">
				${friend}
			</c:forEach>
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td></td>
	</tr>
</table>
</body>
</html>