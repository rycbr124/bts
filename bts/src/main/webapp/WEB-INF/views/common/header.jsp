<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${contextPath}/resources/css/layout/layout.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style>

#header{
position:relative;

}
#header a img{
	top:10px;
}
.header_logo img:hover {
	width: 130px;
	height: 70px;
	content: url(/bts/resources/image/logo/검정/logo_black.png);
}
#header .member_menu{
	position:absolute;
	top:10px;
	right:72px;
	margin:0;
	height:50px;
}
html #menu>li:hover>a, #menu>li.active>a {
	color: rgb(160,160,160);
}
.member_menu::before{
	content:url("/bts/resources/image/icon/user.png");
	position:relative;
	width:30px;
	height:30px;
}
.member_menu .welcome{
	position:relative;
	display:inline-block;
	margin:0;
	margin-bottom:10px;
	top:-10px;
}
.member_menu .memberArea{
	position:relative;
	width:150px;
	height:15px;
	margin-top:0;
}
.member_menu .memberArea span{
	color:#000;
	font-family: 'NotoSansKR', sans-serif;
}
.member_menu .memberArea span::hover{
	color:rgb(160,160,160);
}
.member_menu .memberArea .mypage::hover{
	color:rgb(160,160,160);
}
.member_menu .memberArea .logout::hover{
	color:rgb(160,160,160);
}
</style>
<meta charset="UTF-8">
<title>상단</title>
</head>
<body>
	<div id="header">
		<a href="${contextPath}/main/main" class="header_logo"> <img
			src="${contextPath}/resources/image/logo/검정/logo_black_all.png"
			alt="BTS">
		</a>
		<div class="header_control_container">
			<ul id="menu" class="menu">
				<li class="menu_recommend"><a href="${contextPath}/recommend_main">추천</a></li>
				<li class="menu_reservation"><a href="${contextPath}/resve/Info">예약</a></li>
				<li class="menu_accompany"><a href="${contextPath }/accompany/accMain">동행</a></li>
				<li class="menu_community"><a href="${contextPath}/community/plan_list">커뮤니티</a></li>
				<li class="menu_planner"><a href="${contextPath}/planner/planner">플래너</a></li>			
			</ul>
		</div>
		<!-- header_control_container -->
		<div class="member_menu">
			<c:choose>
				<c:when test="${isLogOn== true and not empty memberInfo }">
						<p class="welcome"style="display:inline-block; color:#000;font-size:13px; font-family:'Nanum Gothic';">어서오세요  ${member_id} 님</p>
						<div class="memberArea" style="position:relative; left:25px;">
						<c:set var="member" value="${member_id}"/>
						<c:choose>
						<c:when test="${member_id != 'admin'}">
						<a href="${contextPath }/my/profile" class="mypage" style=" position:absolute; display:block;font-size:10px; padding-right:7px;border-right:1px solid #000; line-height:10px;z-index:10;"><span>마이페이지</span></a>
						<a href="${contextPath }/signup/logout" class="logout" style="position:absolute; left:70px; font-size:10px; line-height:10px;z-index:10;"><span>로그아웃</span></a>
						</c:when>
						<c:when test="${member_id == 'admin'}">
						<a href="${contextPath}/admin/main" class="mypage" style=" position:absolute; display:block;font-size:10px;padding-right:5px;border-right:1px solid #000;left:-5px; line-height:10px;"><span>관리자 페이지</span></a>
						<a href="${contextPath }/signup/logout" class="logout" style="position:absolute; left:75px; font-size:10px; line-height:10px;"><span>로그아웃</span></a>
						</c:when>
						</c:choose>
						
						</div>
					</c:when>
				<c:otherwise>
					<a href="#popUpWindow" class="signup" data-toggle="modal"><span>LOGIN</span></a>
					<a href="${contextPath }/signup/signup" class="signup"><span>SIGNUP</span></a>
				</c:otherwise>
			</c:choose>
		</div>
		<!-- member_menu -->
	</div>
	<!-- header -->


</body>
</html>