<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Friend</title>
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
</script>
</head>
<body>
<form action="writepost.nhn" method="post" enctype="multipart/form-data">
사용자 : <input type="text" name="user" /><br>
내용 : <input type="text" name="content" /><br>
이미지 : <input type="file" name="image" accept="image/*" onchange="readURL(this);"/><br>
<input type="submit" value="등록" /><br>
미리보기<br><img id="preview" alt="preview" src="#" width="320" height="auto"><br>
</form>
</body>
</html>