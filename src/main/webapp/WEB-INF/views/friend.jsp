<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Friend</title>
<script src="resources/jquery-1.11.3.min.js"></script>
<script type="text/javascript">
	var sendFlag = false;
	var sendFlagAccp = false;
	
	function addFriend() {
		if(sendFlag == true) {
			return;
		}
		
		sendFlag = true;
		
		var frnd_id = $('#frnd_id').val();
		var param = "frnd_id=" + frnd_id;
		$.ajax({
            url:'/addfriend.nhn',
            dataType: "JSON",
            data: param,
            success:function(data) {
            	sendFlag = false;
            	alert(decodeURIComponent(data.msg));
            	if(data.result == "success") {
	            	insertTable(frnd_id);        		
            	}
            },
            error:function(data) {
            	sendFlag = false;
            	alert("통신실패");
            }
        })
	}
	
	function insertTable(frnd_id) {
		var table = document.getElementById('accp_tb');
        var newRow = table.insertRow(2);
        
        // 첫번째 TD
        var newCell = newRow.insertCell(0);
        newCell.innerHTML = frnd_id;
 
        // 만약 TD가 두개 이상 있을 경우
        newCell = newRow.insertCell(1);
        newCell.innerHTML = "대기중";
	}
	
	function accpFrnd(frnd_id) {
		if(sendFlagAccp == true) {
			return;
		}
		
		sendFlagAccp = true;
		
		var param = "frnd_id=" + frnd_id;
		$.ajax({
            url:'/accpfrnd.nhn',
            dataType: "JSON",
            data: param,
            success:function(data) {
            	sendFlagAccp = false;
            	alert(decodeURIComponent(data.msg));
            	if(data.result == "success") {
	            	location.reload();      		
            	}
            },
            error:function(data) {
            	sendFlagAccp = false;
            	alert("통신실패");
            }
        })
	}
</script>
</head>
<body>
	<form action="addfriend.nhn" method="post">
		친구ID : <input type="text" id="frnd_id" name="frnd_id" maxlength="20" /> 
		<input type="button" value="친구추가" onclick="addFriend()"/>
	</form>
	<c:if test="${friends ne null}">
		<table style="border: 1px solid; float: left; text-align: center;">
			<colgroup>
				<col width="100">
			</colgroup>
			<tr>
				<th>친구목록</th>
			</tr>
			<tr>
				<th>아이디</th>
			</tr>
			<c:forEach var="friend" items="${friends}">
				<tr>
					<td><c:choose>
							<c:when test="${user eq friend.user_id}">
					${friend.frnd_id}
				</c:when>
							<c:otherwise>
					${friend.user_id}
				</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</table>
		<table id="accp_tb" style="border: 1px solid; float: left; text-align: center;">
			<colgroup>
				<col width="100">
				<col width="100">
			</colgroup>
			<tr>
				<th colspan="2">대기목록</th>
			</tr>
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
							<td><input type="button" onclick="accpFrnd('${notaccp.user_id}')" value="수락"></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>