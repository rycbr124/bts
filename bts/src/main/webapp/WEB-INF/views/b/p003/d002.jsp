<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
<link rel="stylesheet" href="${contextPath }/resources/css/b/p003/d001.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>
<body>
	<div>
		<h1 style="text-align: center;">Best Travel Seoul</h1>
		<br> <br>
		<h2 style="text-align: center;">비밀번호 찾기</h2>
	</div>
	<br>
	<br>
	<div class="container">
		<form class="form-horizontal" role="form" method="post" action="${contextPath }/find/findPw">
			<h3>가입한 이메일로 비밀번호를 보내드립니다.</h3>
			<div class="form-group" id="divEmail">
				<label for="inputID" class="col-lg-2 control-label">ID</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" id="ID" name="member_id" placeholder="아이디 입력" maxlength="40">
				</div>
			</div>
			<div class="form-group" id="divEmail">
				<label for="inputEmail" class="col-lg-2 control-label">이메일</label>
				<div class="col-lg-10">
					<input type="email" class="form-control" id="email" name="email" data-rule-required="true" placeholder="가입시 이메일 입력" maxlength="40">
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<button type="submit" class="btn btn-default" id="findPwBtn">인증메일 보내기</button>
				</div>
			</div>
		</form>
	</div>
	<br>
	<br>
	<br>
</body>
</html>