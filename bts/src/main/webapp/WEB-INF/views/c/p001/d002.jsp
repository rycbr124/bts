<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${contextPath}/resources/css/mypage/d001.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<h1>회원탈퇴</h1>
	<hr />
	<form name="delFrm" id="delFrm" method="post" action="${contextPath}/my/exitMem">
		<input type="hidden" name="userId" value="${sessionScope.userId}">
		<table border=1>
			<tr>
				<td>패스워드</td>
				<td><input type="password" name="password" id="password"></td>
			</tr>
			<tr>
				<td>패스워드 확인</td>
				<td><input type="password" name="passwdCheck" id="passwdCheck"></td>
			</tr>
			<tr>
				<td colspan=2 align="center"><a href="${contextPath}/my/passCheck" id="secession">탈퇴하기</a></td>
			</tr>
		</table>
	</form>
	<br><br><br><br><br><br><br><br><br><br>
</body>

<script type="text/javascript">
	$(document).ready(function(e){
		$('#secession').click(function(){
			
			//패스워드 입력 확인
			if($('#password').val() == ''){
				alert("패스워드를 입력해 주세요.");
				$('#passwd').focus();
				return;
			}else if($('#passwdCheck').val() == ''){
				alert("패스워드를 입력해 주세요.");
				$('#passwdCheck').focus();
				return;
			}
			
			//입력한 패스워드가 같인지 체크
			if($('#passwdCheck').val() != $('#password').val()){
				alert("패스워드가 일치하지 않습니다.");
				$('#passwdCheck').focus();
				return;
			}
			
			//패스워드 맞는지 확인
			$.ajax({
				url: "${contextPath}/my/passCheck",
				type: "POST",
				data: $('#delFrm').serializeArray(),
				success: function(data){
					if(data==null){
						alert("패스워드가 틀렸습니다.");
						return;
					}else{
						//탈퇴
						var result = confirm('정말 탈퇴 하시겠습니까?');
						if(result){
							$('#delFrm').submit();
						}
					}	alert("회원탈퇴가 완료되었습니다.");
				},
				error: function(){
					alert("서버 에러.");
				}
			});
		});
	});
</script>

</html>