<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><tiles:insertAttribute name="title" /></title>
<link rel="stylesheet" href="${contextPath}/resources/css/main/loginPopup.css">
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="${contextPath }/resources/js/b/p001/login.js"></script>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
</head>
<body>
<div id = "container">
<div id="header">
<tiles:insertAttribute name="header" />
</div>
<div id="body">
<tiles:insertAttribute name="body" />
</div>

<div id="footer" >
<tiles:insertAttribute name="footer" />
</div>
</div>
   	<!-- login popup -->
	<div id="login-box" class="login-popup">
		<a href="#" class="close"><img src="${contextPath}/resources/image/main/close_pop.png" alt="close" /></a>
		<form method="post" role="form" class="signin" action="${contextPath}/signup/login">
			<fieldset class="textbox">
				<p id="h2text" style="color: white;">BTS 로그인</p>
				<label class="member_id"> <span>ID</span> <input id="member_id" name="member_id" type="text" autocomplete="on" placeholder="ID">
				</label> <label class="password"> <span>Password</span> <input id="password" name="password" type="password" placeholder="Password">
				</label> <input type="submit" value="LOGIN" class="submit button" type="button" id="button">
				<p>
					<a class="forgot" href="#">비밀번호 찾기</a>
				</p>
				<div id="kakao_id_login" style="text-align: center">
					<a href="https://kauth.kakao.com/oauth/authorize?client_id=6a0602e55acf9e0f00406d7fb1f93b3d&redirect_uri=http://localhost:8088/bts/signup/kakaoLogin&response_type=code"> <img width="223" src="${contextPath}/resources/image/main/kakao_login.png" /></a>
				</div>
			</fieldset>
		</form>
	</div>
</body>
</html>