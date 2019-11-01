<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 사이드바</title>
<link rel="stylesheet" href="/bts/resources/library/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<div id="side">
<div class="containers">
            <div class="roww">
   <div class="col-md-3">
    <ul class="submenu">
        <li class="active">
            <a href="profile.html">
                <i class="icon icon-update-profile"></i>
                <span>여행자 정보 조회/수정</span>
            </a>
        </li>
        <li>
            <a href="order.html">
                <i class="icon icon-post-list"></i>
                <span>내 글목록 관리</span>
            </a>
        </li>
        <li>
            <a href="wishlist.html">
                <i class="icon icon-reservation"></i>
                <span>예약 내역 관리</span>
            </a>
        </li>
        <li>
            <a href="cart.html">
                <i class="icon icon-question"></i>
                <span>문의 내역 관리</span>
            </a>
        </li>
        <li>
            <a href="coupon.html">
                <i class="icon icon-wishlist"></i>
                <span>위시리스트 관리</span>
            </a>
        </li>
        <li>
            <a href="point.html">
                <i class="icon icon-message"></i>
                <span>쪽지 관리</span>
            </a>
        </li>
         <li>
            <a href="point.html">
                <i class="icon icon-matching"></i>
                <span>매칭 신청 목록 관리</span>
            </a>
        </li>
        <!--<li >
			<a href="reviews.html">
				<i class="fa fa-comments-o"></i>
				<span>Reviews</span>
			</a>
		</li>-->
      
    </ul>
</div>
</div>
</div>
</div>
</body>
</html>