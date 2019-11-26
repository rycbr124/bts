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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>동행 게시글 조회</title>
</head>
<body onload="showImage()">
	<div class="container" id="container">
		<div id="imgContainer">
			<img id="accImage">
			<div id="imgText">
				<h1 id="h4text">&nbsp;&nbsp;${accView.acc_title }</h1>
				<br>
				<h4 id="h4text">작성자 : ${accView.nick_name}</h4>
				<h4 id="h4text">나이 : ${accView.age }</h4>
				<h4 id="h4text">교통수단 : ${accView.traffic}</h4>
				<h4 id="h4text">인원수 : ${accView.whlrs_no}</h4>
				<h4 id="h4text">태그 : ${accView.tag }</h4>
			</div>
		</div>
		<div id="headsubject">
			<h1 id="subjectText">동행 게시글</h1>
		</div>
		<div id="contents">
			<br>
			<h5>작성자의 성향을 보려면 아래 버튼을 클릭하세요.</h5>
			<button class="btn btn-outline-secondary" id="inclnBtn">회원 성향 보기</button>
			<div class="dropdown" id="dropdown" style="display: none;">
				<c:forEach var="inclnView" items="${inclnView}">
					<h5>${inclnView.group_desc}</h5>
					<label class="btn btn-info btn-lg" id="incln" style="background-color: #666666; border-color: #666666">${inclnView.name}</label>
					<br>
					<br>
				</c:forEach>
			</div>
			<script>
				$('#inclnBtn').click(function() {
					$('#dropdown').toggle('slow');
				});
			</script>
			<br> <br>
			<h3>게시글 내용 : ${accView.content}</h3>
			<br> 
			<br> 
			<a class="btn btn-success" href="#" style="background-color: #666666; border-color: #666666">매칭신청하기</a> &nbsp;&nbsp;&nbsp;
			<a class="btn btn-success" href="${contextPath}/accompany/accMain" style="background-color: #666666; border-color: #666666">목록으로 돌아가기</a>
		</div>
	</div>
	<br>
	<br>
</body>
</html>