<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Friend</title>
</head>
<body>
<c:if test="${friends ne null}">
	친구목록
	<table border="1">
		<tr>
			<th>아이디</th>
		</tr>
		<c:forEach var="friend" items="${friends}">
		<tr>
			<td>
				<c:choose>
				<c:when test="${user eq friend.user_id}">
					${friend.frnd_id}
				</c:when>
				<c:otherwise>
					${friend.user_id}
				</c:otherwise>
				</c:choose>
			</td>
		</tr>
		</c:forEach>
	</table>
	대기목록
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>상태</th>
		</tr>
		
		<c:forEach var="notaccp" items="${notaccps}">
		<tr>
		<c:choose>
		<c:when test="${user eq notaccp.user_id}">
			<td>${notaccp.frnd_id}</td>
			<td>대기중</td>
		</c:when>
		<c:otherwise>
			<td>${notaccp.user_id}</td>
			<td><a href="accpfrnd.nhn?frnd_id=${notaccp.user_id}">수락</a></td>
		</c:otherwise>
		</c:choose>
		</tr>
		</c:forEach>
	</table>
</c:if>
<form action="addfriend.nhn" method="post">
친구id : <input type="text" name="frnd_id" />
<input type="submit" value="추가" />
</form>
</body>
</html>