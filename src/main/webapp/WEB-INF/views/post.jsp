<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
	function clickDelete(post_no) {
		var url = "/deletepost.nhn?post_no=" + post_no;
		location.href = url;
	}
	function clickModify(index, post_no) {
		var status = $("#modifybtn" + index).attr('value');
		if(status === '수정') {
			$("#cont" + index).attr('readonly',false);
			$("#modifybtn" + index).attr('value','완료');
		}
		
		if(status === '완료') {			
			var cont = $("#cont" + index).val();
			if(cont.bytes() > 1000) {
				alert("1000byte 초과");
				return false;
			}
			
			$("#cont" + index).attr('readonly',true);
			$("#modifybtn" + index).attr('value','수정');
			
			var form = document.createElement("form");
			form.setAttribute("method", "post");
		    form.setAttribute("action", "/modifypost.nhn");
		    var params = {'post_no':post_no,'post_cont':cont};
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
	function formValidation() {
		var post_cont = document.form.post_cont.value;
		if(post_cont.bytes() > 1000) {
			alert("1000byte 초과");
			return false;
		}
		return true;
	}
	String.prototype.bytes = function() {
		var str = this;
		var l = 0;
		for (var i=0; i<str.length; i++) {
			l += (str.charCodeAt(i) > 128) ? 3 : 1;			
		} 
		return l;
	}
	function changeBytes() {
		var text = document.getElementById("post_cont").value;
		var count = text.bytes();
		document.getElementById("count").innerHTML = count;
	}
</script>
</head>
<body>
<form action="addpost.nhn" method="post" enctype="multipart/form-data" onsubmit="return formValidation();" name="form">
내용 : <textarea rows="5" cols="20" name="post_cont" id="post_cont" onkeyup="changeBytes();"></textarea><span id="count">0</span>/1000 bytes<br> 
이미지 : <input type="file" name="image" accept="image/*" onchange="readURL(this);"/><br>
<input type="submit" value="등록" /><br>
미리보기<br><img id="preview" alt="preview" src="" width="320" height="auto"><br>
</form>
<table border="1">
	<tr>
		<th>시간</th>
		<th>작성자</th>
		<th>내용</th>
		<th>이미지</th>
		<th>수정</th>
		<th>삭제</th>
	</tr>
	<c:forEach var="post" items="${posts}" varStatus="status">
	<tr>
		<td>${post.lst_mod_ymdt}</td>
		<td>${post.user_nm}</td>
		<td><input type="text" id="cont${status.index}" readonly="readonly" value="${post.post_cont}" ></td>
		<td>
			<c:if test="${post.save_file_nm != null && post.save_file_nm != ''}">
				<img src="/image/${post.save_file_nm}" width="320" height="auto"/>			
			</c:if>
		</td>
		<c:if test="${post.user_id eq user}">
			<td><input type="button" id="modifybtn${status.index}" value="수정" onClick="clickModify(${status.index}, ${post.post_no})" ></td>
			<td><input type="button" value="삭제" onClick="clickDelete(${post.post_no})" ></td>
		</c:if>
	</tr>
	</c:forEach>
</table>
</body>
</html>