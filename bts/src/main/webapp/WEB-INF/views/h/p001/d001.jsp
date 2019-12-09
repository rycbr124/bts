<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/css/reserv/reservMain.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<title>숙박예약</title>
</head>
<body>
	<div id="reservcontainer">
		<img src="${contextPath}/resources/image/reserv/hotelReserv.jpg"
			id="reservMainImg"><br>
		<br>
		<div id="headsubject">
			<h1 id="headsubjectText">숙박예약하기</h1>
		</div>

		<c:forEach var="hotelResult" items="${hotelList }" varStatus="status">
			<div class="card" style="width: 18rem;">
  				<img src="${contextPath}${hotelResult.lodging_image}" class="card-img-top" alt="...">
  				<div class="card-body">
    				<h5 class="card-title">${hotelResult.name}</h5>
    				<p class="card-text">${hotelResult.address}</p>
    				<a href="#" class="btn btn-primary">예약하기</a>
  				</div>
			</div>
		</c:forEach>
		
	</div>
</body>
</html>