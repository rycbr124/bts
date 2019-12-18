<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${contextPath}/resources/css/layout/layout.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script>
$(document).ready(function(){
	var img = document.createElement('img');
	$(img).prop('src', '${contextPath}' + '${sessionScope.memberInfo.profile_image}');
	$(img).prop('class', 'profileImg');
	$('.welcome').before(img);
	
});
</script>

<style>
@font-face{
	src: url('/bts/resources/fonts/Nanum/NanumSquareRoundR.ttf');
	font-family:"nanum";
}
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
   right:90px;
   margin:0;
   height:50px;
   width:200px;
}
#header .member_menu .memberArea{
	position:absolute;
	width:100%;
	height:auto;
	top:20px;
	left:15px;
}
html #menu>li:hover>a, #menu>li.active>a {
   color: rgb(160,160,160);
}

#header .member_menu .welcome{
   position:absolute;
   display:inline-block;
   margin:0;
}
.member_menu a{
    font-size: 12px;
    font-weight: 600;
    line-height: 20px;
    letter-spacing: 0.025em;
	color : #000;
    position:relative;
    display: inline-block;
    vertical-align: middle;
  	text-decoration: none;
  	font-family:"nanum";
}
#header .member_menu .memberArea .mypage{
	position:absolute;
	top:3px;
}
#header .member_menu .memberArea .logout{
	position:absolute;
	left:80px;
	top:3px;
}
#header .member_menu .memberArea span::hover{
   color:rgb(160,160,160);
}
#header .member_menu .memberArea .mypage::hover{
   color:rgb(160,160,160);
}
#header .member_menu .memberArea .logout::hover{
   color:rgb(160,160,160);
}
#header img.profileImg{
	border-radius : 50%;
	width : 30px;
	height : 30px;
	margin-right : 5px;
}
#header p{
	font-color : black;
}
</style>
<meta charset="UTF-8">
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
            		
                  <p class="welcome"style="display:inline-block; color:#000;font-size:13px; font-family:'Nanum Gothic';">어서오세요  ${sessionScope.memberInfo.member_id} 님</p>
                  <div class="memberArea">
                  <c:set var="member" value="${sessionScope.memberInfo.member_id}"/>
                  <c:choose>
                  <c:when test="${member_id != 'admin'}">
                  <a href="${contextPath }/my/profile" class="mypage"><span>마이페이지</span></a>
                  <a href="${contextPath }/signup/logout" class="logout"><span>로그아웃</span></a>
                  </c:when>
                  <c:when test="${member_id == 'admin'}">
                  <a href="${contextPath}/admin/main" class="mypage" style=" position:absolute; display:block;font-size:10px;padding-right:5px;border-right:1px solid #000;left:-5px; line-height:10px;"><span>관리자 페이지</span></a>
                  <a href="${contextPath}/signup/logout" class="logout" style="position:absolute; left:75px; font-size:10px; line-height:10px;"><span>로그아웃</span></a>
                  </c:when>
                  </c:choose>
                  
                  </div>
               </c:when>
            <c:otherwise>
               <a href="#popUpWindow" class="login" data-toggle="modal"><span>LOGIN</span></a>
               <a href="${contextPath }/signup/signup" class="signup"><span>SIGNUP</span></a>
            </c:otherwise>
         </c:choose>
      </div>
      <!-- member_menu -->
   </div>
   <!-- header -->


</body>
</html>