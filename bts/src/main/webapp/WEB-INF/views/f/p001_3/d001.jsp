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

table{
	text-align : center;
	width:100%;
}

th{
	font-family: "NanumSquareRoundEB";
}

td{
	font-family: "NanumSquareRoundR";	
}

h2{
	font-family: "NanumSquareRoundEB";
}
p {
	font-family: "NanumSquareRoundR";	
}

img{
	width : 1110px;
	height : 400px;
	margin-bottom : 50px;
}

</style>


</head>
<body>

<div class="container">
	<h2>커뮤니티</h2>
  <p>BTS와 함께 나만의 여행후기를 공유하세요!</p>
  <img src="${contextPath}/resources/image/community/community_main2.jpg">
  <ul class="nav nav-tabs">
    <li class="nav-item">
      <a class="nav-link" href="${contextPath}/community/plan_list">계획</a>
    </li>
    <li class="nav-item">
      <a class="nav-link active" href="${contextPath}/community/review_list">후기</a>
    </li>
    
  </ul>
  
  <div class="row">
  
  </div>
  




</div>
</body>
</html>