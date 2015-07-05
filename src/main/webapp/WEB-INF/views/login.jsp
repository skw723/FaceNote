<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Login</title>
<script type="text/javascript">
	function formValidation() {
		
	} 
</script>
</head>
<body>
<form action="checklogin.nhn" method="post" onsubmit="return formValidation();" name="form">
<table>
	<tr>
		<td>아 이 디</td>
		<td><input type="text" name="user_id" maxlength="20" />
		<td rowspan="2"><input type="submit" value="로그인" />
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="password" name="password" maxlength="80"/></td>
	</tr>
</table>
</form>
</body>
</html>