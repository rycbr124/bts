<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />	
<c:set var="tourKey"><spring:eval expression="@file.getProperty('tour.key')" /></c:set>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${contextPath}/resources/css/plan_detail/plan_detail.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6b2f7da39af5c9b3e7839e09fedbc28a"></script>
<script type="text/javascript" src="${contextPath}/resources/js/plan/plan_detail.js"></script>
<meta charset="UTF-8">
<title>Best Travel Seoul[MY PLAN]</title>
<script>
setGlobal_de('${tourKey}');
$(document).ready(function(){
	var plan_root = ${root};
	var planner = ${planner};
	planRoot(plan_root,planner);
	
});
</script>
</head>
<body>
	<form  name="desc">
	<input type="hidden" name="plan_no"/>
	<input type="hidden" name="detail_no"/>
	<input type="hidden" name="plan_desc"/>
	<div class="body">
		<div class="plan_detail_header">
			<img class="thumbnail_image" style="position:relative; display:inline-block;">
		</div>
	<div class="plan_detail_info">
		<div id="map" style="width:346px; height:303px;">
		<select class="date_select"></select>
		</div>
	</div>
	</div>
	</form>
</body>
</html>