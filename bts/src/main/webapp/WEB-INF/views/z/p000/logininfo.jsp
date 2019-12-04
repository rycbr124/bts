<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<style>
.modal {
	font-family: -apple-system, BlinkMacSystemFont, avenir next, avenir,
		helvetica neue, helvetica, ubuntu, roboto, noto, segoe ui, arial,
		sans-serif;
}

.modal__overlay {
	position: fixed;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background: rgba(0, 0, 0, 0.6);
	display: flex;
	justify-content: center;
	align-items: center;
}

.modal__container {
	background-color: #fff;
	padding: 30px;
	max-width: 500px;
	max-height: 100vh;
	border-radius: 4px;
	overflow-y: auto;
	box-sizing: border-box;
}

.modal__header {
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.modal__title {
	margin-top: 0;
	margin-bottom: 0;
	font-weight: 600;
	font-size: 1.25rem;
	line-height: 1.25;
	color: #00449e;
	box-sizing: border-box;
}

.modal__close {
	background: transparent;
	border: 0;
}

.modal__header .modal__close:before {
	content: "\2715";
}

.modal__content {
	margin-top: 2rem;
	margin-bottom: 2rem;
	line-height: 1.5;
	color: rgba(0, 0, 0, .8);
}

.modal__btn {
	font-size: .875rem;
	padding-left: 1rem;
	padding-right: 1rem;
	padding-top: .5rem;
	padding-bottom: .5rem;
	background-color: #e6e6e6;
	color: rgba(0, 0, 0, .8);
	border-radius: .25rem;
	border-style: none;
	border-width: 0;
	cursor: pointer;
	-webkit-appearance: button;
	text-transform: none;
	overflow: visible;
	line-height: 1.15;
	margin: 0;
	will-change: transform;
	-moz-osx-font-smoothing: grayscale;
	-webkit-backface-visibility: hidden;
	backface-visibility: hidden;
	-webkit-transform: translateZ(0);
	transform: translateZ(0);
	transition: -webkit-transform .25s ease-out;
	transition: transform .25s ease-out;
	transition: transform .25s ease-out, -webkit-transform .25s ease-out;
}

.modal__btn:focus, .modal__btn:hover {
	-webkit-transform: scale(1.05);
	transform: scale(1.05);
}

.modal__btn-primary {
	background-color: #00449e;
	color: #fff;
}

/**************************\
  Demo Animation Style
\**************************/
@
keyframes mmfadeIn {from { opacity:0;
	
}

to {
	opacity: 1;
}

}
@
keyframes mmfadeOut {from { opacity:1;
	
}

to {
	opacity: 0;
}

}
@
keyframes mmslideIn {from { transform:translateY(15%);
	
}

to {
	transform: translateY(0);
}

}
@
keyframes mmslideOut {from { transform:translateY(0);
	
}

to {
	transform: translateY(-10%);
}

}
.micromodal-slide {
	display: none;
}

.micromodal-slide.is-open {
	display: block;
}

.micromodal-slide[aria-hidden="false"] .modal__overlay {
	animation: mmfadeIn .3s cubic-bezier(0.0, 0.0, 0.2, 1);
}

.micromodal-slide[aria-hidden="false"] .modal__container {
	animation: mmslideIn .3s cubic-bezier(0, 0, .2, 1);
}

.micromodal-slide[aria-hidden="true"] .modal__overlay {
	animation: mmfadeOut .3s cubic-bezier(0.0, 0.0, 0.2, 1);
}

.micromodal-slide[aria-hidden="true"] .modal__container {
	animation: mmslideOut .3s cubic-bezier(0, 0, .2, 1);
}

.micromodal-slide .modal__container, .micromodal-slide .modal__overlay {
	will-change: transform;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/micromodal/dist/micromodal.min.js"></script>
</head>
<body>
	<!-- login popup -->
	<!--  <div id="login-box" class="login-popup">
		<a href="#" class="close"><img src="${contextPath}/resources/image/main/close_pop.png" alt="close" /></a>
		<form method="post" role="form" class="signin" action="${contextPath}/signup/login">
		
			<fieldset class="textbox">
				<p id="h2text" style="color: white;">BTS 로그인</p>
				<label class="member_id"> <span>ID</span> <input id="member_id" name="member_id" type="text" autocomplete="on" placeholder="ID">
				</label> <label class="password"> <span>Password</span> <input id="password" name="password" type="password" placeholder="Password">
				</label> <input type="submit" value="LOGIN" class="submit button" type="button" id="button">
				<p>
					<a class="forgotpw" href="${contextPath }/find/findPwMain" style="color:white">비밀번호 찾기</a>&nbsp;&nbsp;&nbsp;
					<a class="forgotid" href="${contextPath }/find/findIdMain" style="color:white">아이디 찾기</a>
				</p>
				<div id="kakao_id_login" style="text-align: center">
					<a href="https://kauth.kakao.com/oauth/authorize?client_id=6a0602e55acf9e0f00406d7fb1f93b3d&redirect_uri=http://localhost:8088/bts/signup/kakaoLogin&response_type=code"> 
					<img width="223" src="${contextPath}/resources/image/main/kakao_login.png" /></a>
					<a href="${contextPath}/signup/naverLogin"><img width="223" src="${contextPath}/resources/image/main/naver_login.PNG" /></a>  
				</div>
			</fieldset>
		</form>
	</div>-->
	<div class="modal__overlay" tabindex="-1" id="modal-1" data-micromodal-close>
		<div class="modal__container w-90 w-40 - ns" role="dialog" aria-modal="true" aria-labelledby="modal-2-title">
			<header class="modal__header">
				<h3 class="modal__title" id="modal-2-title">BTS Login</h3>
				<button class="modal__close" aria-label="Close modal" data-custom-close></button>
			</header>
			<form class="black-80" action="${contextPath }/signup/login">
				<div class="modal__content">
					<div class="measure">
						<label for="name" class="f6 b db mb2 mt3">ID: </label> <input id="name" class="input-reset ba b--black-20 pa2 mb2 db w-100 js-nameInput" type="text" name="member_id"> <label for="password" class="f6 b db mb2 mt3">Password</label> <input id="password" class="input-reset ba b--black pa2 mb2 db w-100" type="password" required>
					</div>
				</div>
				<footer class="modal__footer">
					<input type="submit" class="modal__btn modal__btn-primary" value="Login">
				</footer>
				<div id="kakao_id_login" style="text-align: center">
					<a href="https://kauth.kakao.com/oauth/authorize?client_id=6a0602e55acf9e0f00406d7fb1f93b3d&redirect_uri=http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/signup/kakaoLogin&response_type=code"> <img width="223" src="${contextPath}/resources/image/main/kakao_login.png" /></a> <a href="${contextPath}/signup/naverLogin"><img width="223" src="${contextPath}/resources/image/main/naver_login.PNG" /></a>
				</div>
			</form>
		</div>
	</div>
</body>
</html>