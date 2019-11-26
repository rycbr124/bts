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
<script type="text/javascript" src="${contextPath}/resources/js/recommend/recommend.js"></script> <!-- 커스텀 js -->


<!-- 부트스트랩 -->
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

h5{
	font-family: "NanumSquareRoundEB";
}

p{
	font-family: "NanumSquareRoundR";	
}

div.box{
	background-color : #F8F8FA;
	padding-top : 20px;
}
</style>



<script>

$(document).ready(function (){
	var catArray = ${result};

	optionInit(catArray);
	result_init();
	
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
	
	function paging_click(){//페이징 버튼 눌렀을 때
		var pageNo = $(this).text();//자식노드중에 텍스트노드만 가져온다.
		if(pageNo == 'Next'){
			pageNo = 6;
		}
		console.log(pageNo);
		image_init(pageNo);
		 
	}
	
});


</script>
</head>
<body>
	<div class="container">	
		<h1 class="title">지역별 추천</h1>
		<p>서울 내 명소들을 지역별로 추천</p>
		<hr>
		<div class="box">
			<strong>지역선택 : </strong>
			<select class="form-control" id="sigungucode" style="display:inline-block;">
				<option value="" selected>지역선택</option>
			</select>
		
			<strong>관광타입 : </strong>
			<select class="form-control" id="contenttypeid" style="display:inline-block;">
				<option value="" selected>타입선택</option>
			</select>
			
			<select class="form-control" id="cat3" style="display:inline-block;">
				<option value="">분류</option>
			</select>
			<button type="button" class="btn btn-outline-secondary btn-sm" id="search">검색</button> 
			 
			<div class="row" id="image_grid">
				
			</div>
		</div>
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