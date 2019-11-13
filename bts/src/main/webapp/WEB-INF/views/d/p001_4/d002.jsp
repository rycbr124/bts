<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<style>
@font-face {
	src: url("/bts/resources/fonts/Nanum/NanumSquareRoundEB.ttf");
    font-family: "NanumSquareRoundEB";
}

@font-face {
    src: url("/bts/resources/fonts/Nanum/NanumSquareRoundR.ttf");
    font-family: "NanumSquareRoundR";
}

h1{
	font-family : "NanumSquareRoundEB";
	display : inline-block;
	margin-left : 30px;	
}

h2{
	font-family : "NanumSquareRoundEB";
}

*{
	font-family: "NanumSquareRoundR";
}


img.thumb_nail{
	width : 1110px;
	height : 400px;
}

div.title{
	width : 1000px;
	height : 200px;
	margin-left : 55px;
	background-color : white;
	opacity : 0.5;
	position : relative;
	top : -200px;
}

img.content_image{
	width : 500px;
	height : 300px;
}

</style>
</head>
<body>
	<div class="container">
		<c:forEach var="article" items="${result}">
			<img class="thumb_nail" src='${article.file_path }'>
			<div class="title">
				<h1>${article.title}</h1>
				<strong>등록일. ${article.register_date}</strong>
				<strong>작성자. ${article.member_id}</strong>
			</div>
		</c:forEach>
		
		<!-- javascript로 처리할 부분 -->
		<div class="content" id="day1">
			<h2>DAY1</h2>
			<hr>
			<div>
				<img class="content_image" src="${contextPath}/resources/image/itaewon.jpg">
			</div>
		</div>
	
	</div>
</body>
</html>