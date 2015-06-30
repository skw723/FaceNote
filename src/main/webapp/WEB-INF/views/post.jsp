<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Post</title>
<script src="resources/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	function readURL(input) {
		if(input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#preview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	function clickModify(index) {
		var status = $("#modifybtn" + index).attr('value');
		if(status === '수정') {
			$("#cont" + index).attr('readonly',false);
			$("#modifybtn" + index).attr('value','완료');
		}
		
		if(status === '완료') {
			$("#cont" + index).attr('readonly',true);
			var cont = $("#cont" + index).val();
			alert(cont);
			$("#modifybtn" + index).attr('value','수정');
			
			var form = document.createElement("form");
			form.setAttribute("method", "post");
		    form.setAttribute("action", "/modifypost.nhn");
		    var params = {'post_no':index,'post_cont':cont};
		    for(var key in params) {
		        var hiddenField = document.createElement("input");
		        hiddenField.setAttribute("type", "hidden");
		        hiddenField.setAttribute("name", key);
		        hiddenField.setAttribute("value", params[key]);
		        form.appendChild(hiddenField);
		    }
		    document.body.appendChild(form);
		    form.submit();
		}
	}
</script>
</head>
<body>
<form action="addpost.nhn" method="post" enctype="multipart/form-data">
내용 : <input type="text" id="post_cont" name="post_cont" /><br>
이미지 : <input type="file" name="image" accept="image/*" onchange="readURL(this);"/><br>
<input type="submit" value="등록" /><br>
미리보기<br><img id="preview" alt="preview" src="#" width="320" height="auto"><br>
</form>
<table border="1">
	<tr>
		<th>시간</th>
		<th>작성자</th>
		<th>내용</th>
		<th>이미지</th>
		<th>수정</th>
	</tr>
	<c:forEach var="post" items="${posts}" varStatus="status">
	<tr>
		<td>${post.lst_mod_ymd}</td>
		<td>${post.user_nm}</td>
		<td><input type="text" id="cont${status.index}" readonly="readonly" value="${post.post_cont}" ></td>
		<td>
			<c:if test="${post.save_file_nm != null && post.save_file_nm != ''}">
				<img src="/image/${post.save_file_nm}" width="320" height="auto"/>			
			</c:if>
		</td>
		<td>
			<c:if test="${post.user_id eq user}">
				<input type="button" id="modifybtn${status.index}" value="수정" onClick="clickModify(${status.index})" >
			</c:if>
		</td>
	</tr>
	</c:forEach>
</table>
</body>
</html>