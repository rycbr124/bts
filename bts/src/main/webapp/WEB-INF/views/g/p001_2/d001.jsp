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
<title>지역별 추천</title>

<link rel="stylesheet" href="${contextPath}/resources/css/custom.css"> <!-- 커스텀 css -->
<link rel="stylesheet" href="${contextPath}/resources/library/bootstrap/css/bootstrap-grid.min.css">

<script src="http://code.jquery.com/jquery-1.10.2.js"></script> <!-- jQuery -->

<script type="text/javascript" src="${contextPath}/resources/js/recommend.js"></script> <!-- 커스텀 js -->

<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

</head>
<body>
	<div class="container">
		<h1 class="title">지역별 추천</h1>
		<strong>지역선택 : </strong>
		<select class="form-control" id="sigungucode" style="display:inline-block;">
			<option value="">지역선택</option>
			<option value="1">강남구</option>
			<option value="2">강동구</option>
			<option value="3">강북구</option>
			<option value="4">강서구</option>
			<option value="5">관악구</option>
			<option value="6">광진구</option>
			<option value="7">구로구</option>
			<option value="8">금천구</option>
			<option value="9">노원구</option>
			<option value="10">도봉구</option>
			<option value="11">동대문구</option>
			<option value="12">동작구</option>
			<option value="13">마포구</option>
			<option value="14">서대문구</option>
			<option value="15">서초구</option>
			<option value="16">성동구</option>
			<option value="17">성북구</option>
			<option value="18">송파구</option>
			<option value="19">양천구</option>
			<option value="20">영등포구</option>
			<option value="21">용산구</option>
			<option value="22">은평구</option>
			<option value="23">종로구</option>
			<option value="24">중구</option>
			<option value="25">중랑구</option>
			
		</select>
	
		<strong>관광타입 : </strong>
		<select class="form-control" id="contenttypeid" onchange="selectChange()" style="display:inline-block;">
			<option value="">타입선택</option>
			<option value="12">관광지</option>
			<option value="14">문화시설</option>
			<option value="15">축제공연행사</option>
			<option value="28">레포츠</option>
			<option value="32">숙박</option>
			<option value="38">쇼핑</option>
			<option value="39">음식점</option>
		</select>
		
		
		<select class="form-control" id="cat3" style="display:inline-block;">
			<option value="">분류</option>

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