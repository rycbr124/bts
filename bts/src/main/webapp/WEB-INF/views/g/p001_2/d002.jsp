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
<title>코스 별 추천</title>

<link rel="stylesheet" href="${contextPath}/resources/css/custom.css"> <!-- 커스텀 css -->
<link rel="stylesheet" href="${contextPath}/resources/library/bootstrap/css/bootstrap-grid.min.css">

<script src="http://code.jquery.com/jquery-1.10.2.js"></script> <!-- jQuery -->
<script type="text/javascript" src="${contextPath}/resources/js/recommend2.js"></script> <!-- 커스텀 js -->


<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<script>

$(document).ready(function (){
	var catArray = ${course};

	optionInit(catArray);

	function optionInit(catArray){
		for(var i in catArray.course){
			var option = document.createElement("option");
			$(option).prop('value',catArray.course[i].category_cd);
			$(option).text(catArray.course[i].name);
			$("#cat3").append(option);
		}
	};
	
});


</script>

<style>
@font-face {
	src: url("/bts/resources/fonts/Nanum/NanumSquareRoundEB.ttf");
    font-family: "NanumSquareRoundEB";
}

h5{
	font-family: "NanumSquareRoundEB";
}
</style>

</head>
<body>
	<div class="container">
		<h1 class="title">코스별 추천</h1>
	
		<strong>코스타입 : </strong>
		<select class="form-control" id="cat3" style="display:inline-block;">
			<option value="" selected>타입선택</option>
		
		</select>
		<button type="button" class="btn btn-outline-secondary btn-sm" id="search">검색</button> 
	</div>

	<div class="row" id="image_grid">
		
		
	</div>
	
	<div class="container">
	<div id="pagination">
		<ul class="pagination justify-content-center">
			<li class="page-item"><span class="page-link">Prev</span></li>
			<li class="page-item"><span class="page-link">1</span></li>
			<li class="page-item"><span class="page-link">2</span></li>
			<li class="page-item"><span class="page-link">3</span></li>
			<li class="page-item"><span class="page-link">4</span></li>
			<li class="page-item"><span class="page-link">5</span></li>
			<li class="page-item"><span class="page-link">Next</span></li>
			
		</ul>
	</div>
	</div>
</body>
</html>