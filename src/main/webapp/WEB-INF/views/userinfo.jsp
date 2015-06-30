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
		<th>아이디</th>
		<th>이름</th>
		<th>생년월일</th>
		<th>이메일</th>
		<th>가입일</th>
	</tr>
	<tr>
		<td>${user.user_id}</td>
		<td>${user.user_nm}</td>
		<td>${user.bymd}</td>
		<td>${user.email_addr}</td>
		<td>${user.join_ymd}</td>
	</tr>
</table>
</body>
</html>