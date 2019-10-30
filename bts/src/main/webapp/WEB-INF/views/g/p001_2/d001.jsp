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
<script>

$(document).ready(function (){
	var catArray = ${result};

	optionInit(catArray);
	
	$("#contenttypeid").change(function(){
		$("#cat3").empty();
		var initOption = document.createElement('option');
		var option_name = document.createTextNode('분류');
		initOption.appendChild(option_name);
		$(initOption).prop('value', '');
		
		
		
		$("#cat3").append(initOption);
		
		
		var check=Number($(this).val());
		for(var i in catArray.lower){
			if(check === catArray.lower[i].upper_category_cd){
				var option = document.createElement("option");
				$(option).prop('value',catArray.lower[i].category_cd);
				$(option).text(catArray.lower[i].name);
				$("#cat3").append(option);
			}
		}
	});

	function optionInit(catArray){
		for(var i in catArray.upper){
			var option = document.createElement("option");
			$(option).prop('value',catArray.upper[i].category_cd);
			$(option).text(catArray.upper[i].name);
			$("#contenttypeid").append(option);
		}
	};
	
});


</script>
<body>
	<div class="container">
		<h1 class="title">지역별 추천</h1>
		<strong>지역선택 : </strong>
		<select class="form-control" id="sigungucode" style="display:inline-block;">
			<option value="" <c:if test="${sigungucode==''}"> selected </c:if>>지역선택</option>
			<option value="1" <c:if test="${sigungucode=='1'}"> selected </c:if>>강남구</option>
			<option value="2" <c:if test="${sigungucode=='2'}"> selected </c:if>>강동구</option>
			<option value="3" <c:if test="${sigungucode=='3'}"> selected </c:if>>강북구</option>
			<option value="4" <c:if test="${sigungucode=='4'}"> selected </c:if>>강서구</option>
			<option value="5" <c:if test="${sigungucode=='5'}"> selected </c:if>>관악구</option>
			<option value="6" <c:if test="${sigungucode=='6'}"> selected </c:if>>광진구</option>
			<option value="7" <c:if test="${sigungucode=='7'}"> selected </c:if>>구로구</option>
			<option value="8" <c:if test="${sigungucode=='8'}"> selected </c:if>>금천구</option>
			<option value="9" <c:if test="${sigungucode=='9'}"> selected </c:if>>노원구</option>
			<option value="10" <c:if test="${sigungucode=='10'}"> selected </c:if>>도봉구</option>
			<option value="11" <c:if test="${sigungucode=='11'}"> selected </c:if>>동대문구</option>
			<option value="12" <c:if test="${sigungucode=='12'}"> selected </c:if>>동작구</option>
			<option value="13" <c:if test="${sigungucode=='13'}"> selected </c:if>>마포구</option>
			<option value="14" <c:if test="${sigungucode=='14'}"> selected </c:if>>서대문구</option>
			<option value="15" <c:if test="${sigungucode=='15'}"> selected </c:if>>서초구</option>
			<option value="16" <c:if test="${sigungucode=='16'}"> selected </c:if>>성동구</option>
			<option value="17" <c:if test="${sigungucode=='17'}"> selected </c:if>>성북구</option>
			<option value="18" <c:if test="${sigungucode=='18'}"> selected </c:if>>송파구</option>
			<option value="19" <c:if test="${sigungucode=='19'}"> selected </c:if>>양천구</option>
			<option value="20" <c:if test="${sigungucode=='20'}"> selected </c:if>>영등포구</option>
			<option value="21" <c:if test="${sigungucode=='21'}"> selected </c:if>>용산구</option>
			<option value="22" <c:if test="${sigungucode=='22'}"> selected </c:if>>은평구</option>
			<option value="23" <c:if test="${sigungucode=='23'}"> selected </c:if>>종로구</option>
			<option value="24" <c:if test="${sigungucode=='24'}"> selected </c:if>>중구</option>
			<option value="25" <c:if test="${sigungucode=='25'}"> selected </c:if>>중랑구</option>		
		</select>
	
		<strong>관광타입 : </strong>
		<select class="form-control" id="contenttypeid" style="display:inline-block;">
			<option value="" selected>타입선택</option>
		
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