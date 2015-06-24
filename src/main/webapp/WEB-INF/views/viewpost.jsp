<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View Post</title>
</head>
<body>
조회 결과 : ${result}
<c:if test="${result eq 'success'}">
	사용자 : ${user}
</c:if>
<table border="1">
	<tr>
		<th>시간</th>
		<th>작성자</th>
		<th>내용</th>
		<th>이미지</th>
	</tr>
	<c:forEach var="post" items="${posts}">
	<tr>
		<td>${post.createTime}</td>
		<td>${post.writer}</td>
		<td>${post.content}</td>
		<td>
			<img src="${post.imageName}" width="320" height="auto"/>
		</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>