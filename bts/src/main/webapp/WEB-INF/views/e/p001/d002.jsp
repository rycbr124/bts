<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="result" value="${param.result }" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<script>
	var imgArray = new Array();
	imgArray[0] = "${contextPath}/resources/image/accompany/duksu.jpg";
	imgArray[1] = "${contextPath}/resources/image/accompany/olympic.jpg";
	imgArray[2] = "${contextPath}/resources/image/accompany/sakura.jpg";
	imgArray[3] = "${contextPath}/resources/image/accompany/seoul.jpg";

	function showImage() {
		var imgNum = Math.round(Math.random() * 3);
		var objImg = document.getElementById("accImage");
		objImg.src = imgArray[imgNum];

	}
</script>
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextPath}/resources/css/e/p001/d002.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap/bootstrap.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>동행 게시글 조회</title>
</head>
<body onload="showImage()">
	<div class="container" id="container">
		<img id="accImage">
		<div id="headsubject">
			<h1 id="subjectText">동행 게시글</h1>
		</div>
		<div id="contents">
		<h3>글 제목 : ${accView.acc_title }</h3><br>
			<h4>작성자 : ${accView.member_id } </h4>
			<h4>나이 : ${accView.age }</h4>
			<h4>성별 : ${accView.gender }</h4>
			<h4>교통수단 : ${accView.traffic}</h4>
			<h4>인원수 : ${accView.whlrs_no}</h4>
			<h4>태그 : ${accView.tag}</h4>
			<h4>게시글 내용 : ${accView.content}</h4>
			<h4>성향코드 : ${accView.incln_cd}</h4>
			

		</div>
	</div>
</body>
</html>