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

p{
	font-family: "NanumSquareRoundR";
}

img.thumb_nail{
	width : 600px;
	height : 300px;
}

</style>
</head>
<body>
<div class="container">
<c:forEach var="article" items="${result}">
<div>
<img class="thumb_nail" src='${article.file_path }'>
<h1>${article.title}</h1>
</div>
<p>${article.contents}</p>
<p>${article.member_id}</p>
<p>${article.register_date}</p>
<p>${article.file_name}</p>
</c:forEach>
</div>
</body>
</html>