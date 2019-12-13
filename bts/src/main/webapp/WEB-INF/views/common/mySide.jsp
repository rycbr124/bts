<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 사이드바</title>
<link rel="stylesheet" href="/bts/resources/library/bootstrap/css/bootstrap.min.css" />
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script>
$(function(){
	var top = $(window).scrollTop();//현재 위치 반환
	var speed = 300;//속도
	var easing = 'linear';//따라다니는 방법
	var layer = $('#side'); //레이어 선택
	var layerTopOffset = 100; //레이어 높이 상한선
	if(top > 0)
		$(window).scrollTop(layerTopOffset + top);
	else
		$(window).scrollTop(0);
	//스크롤 이벤트 발생시 실행
	$(window).scroll(function(){
		var position = $(window).scrollTop()-100;
		if(position < 0){
			position = 0;
		}
		$('#side').animate({'top':position},{duration:speed, easing, queue:false});
	});
	$('#side ul li a').on('click',function(){
		var className = $(this).parent().attr('class')
		if(className != 'active'){
			$(this).parent().addClass('active');
		}else{
			$(this).parenr().removeClass();
		}
	});
});
</script>
<style>
.submenu{
	width:100%;
}
.submenu li{
	font-size:14px;
	width:100%;
}
.submenu li a{
	width:100%;
}
.sideTitle{
	color:#fff;
	letter-spacing:2px;
}
.active{
	background-color:rgb(210,210,210);
	color:#fff;
	width:100%;
}
</style>
</head>
<body>
		<span class="sideTitle">마이페이지</span>
		<ul class="submenu">
			<li><a href="${contextPath}/my/profile"> <i
					class="icon icon-update-profile"></i> <span>여행자 정보 조회/수정</span>
			</a></li>
			<li><a href="${contextPath}/my/myBoardList"> <i class="icon icon-post-list"></i>
					<span>내 글목록 관리</span>
			</a></li>
			<li><a href="wishlist.html"> <i
					class="icon icon-reservation"></i> <span>예약 내역 관리</span>
			</a></li>
			<li><a href="${contextPath}/question/questionMain"> <i class="icon icon-question"></i>
					<span>문의 내역 관리</span>
			</a></li>
			<li><a href="${contextPath}/my/wishList"> <i class="icon icon-wishlist"></i>
					<span>위시리스트 관리</span>
			</a></li>
			<li><a href="${contextPath}/my/message/main"> <i class="icon icon-message"></i>
					<span>쪽지 관리</span>
			</a></li>
			<li><a href="${contextPath}/my/accompany/accList"> <i class="icon icon-matching"></i>
					<span>매칭 신청 목록 관리</span>
			</a></li>
		
		</ul>
</body>
</html>