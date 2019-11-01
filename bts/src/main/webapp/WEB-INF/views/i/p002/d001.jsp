<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
       isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextPath}/resources/css/plan/plan.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/recommend.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6b2f7da39af5c9b3e7839e09fedbc28a"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/plan/plan.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/plan/plan_kakaoMap.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6b2f7da39af5c9b3e7839e09fedbc28a"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<title>Best Travel Seoul[플랜 작성]</title>


</head>
<body>
<div class="map_controller" id="map_controller">
	<div class="plan_tab" id="plan_tab">
		<a href="${contextPath}/main/main"><img src="${contextPath}/resources/image/logo/흰색/logo_white_all.png" class="logo" alt="홈화면이동"></a>
		<div class="select_date" id="select_date">
			<div class="calendar" id="calendar">
				<img src="${contextPath}/resources/image/icon/calendar.png">
				<input type="text" name="daterange"class="daterange" size="20" placeholder="날짜를 입력해주세요."/>
			</div><!-- calendar -->
		</div><!-- select_date -->
	</div><!-- plan_tab -->
	
	<div class="content_container" id="content_container"></div><!-- content_container -->
</div><!-- map_controller -->


<div class="plan_list_container" id="plan_list_container">
	<div class="plan_list_header" id="plan_list_header"></div>
	<ul class="plan_box"></ul>
</div>
<div class="map_area" id="map_area">
<div id="map" style="width:auto;height:100vh;">
	<input type="text" placeholder="제목을 입력하세요."  size="20" style="position:absolute; text-align:center;z-index:2; outline:none; height:30px; margin:10px; border-radius:9px;"/>

</div>
</div>


</body>
</html>