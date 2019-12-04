<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextPath}/resources/css/planner/planner.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/plan/planner.js"></script>
<title>Best Travel Seoul[플래너]</title>
<script>
$(document).ready(function(){
	var planList = ${list};
	plan_list(planList);
});
</script>
</head>
<body>
	<div class="body" id="body">
		<div class="top_content" id="top_content">
			<img src="${contextPath}/resources/image/planner/plan.jpg">
			<div class="plan_desc" id="plan_desc">
				<h1>PLANNER</h1>
				<span style="color:gray; font-size:20px;">상상속 여행을 현실로 실행하세요!</span>
				<p>여행 루트와 여행비용,시간을 계산해 <br>
				나만의 여행을 계획하세요.</p>
				<button class="plan_btn" id="plan_btn" onclick="location='${contextPath}/plan/plan'">플랜 작성</button>
			</div>
		</div> <!-- top_content -->
		
		<div class="plan_myPlan"id="plan_myPlan">
			<h1>MY PLAN</h1>
			<div class="my_plan">
			</div>
		</div>
		<div style="display: block; text-align: center;">		
		<c:if test="${paging.startPage != 1 }">
			<a href="${contsxtPath}/planner/planner?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a>
		</c:if>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage }">
					<b>${p}</b>
				</c:when>
				<c:when test="${p != paging.nowPage }">
					<a href="${contsxtPath}/planner/planner?nowPage=${p }&cntPerPage=${paging.cntPerPage}">${p}</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
			<a href="${contsxtPath}/planner/planner?nowPage=${paging.endPage+1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
		</c:if>
	</div>
	</div>
</body>
</html>