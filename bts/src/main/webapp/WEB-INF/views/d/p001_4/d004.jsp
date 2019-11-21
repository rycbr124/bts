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
<title>커뮤니티 글 저장</title>

<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- alert 라이브러리 -->
<script src="${contextPath}/resources/js/alert/sweetalert2.all.min.js"></script>
<script src="https://cdn.jsdlivr.net/npm/promise-polyfill"></script>

<script src="${contextPath}/resources/js/alert/sweetalert2.min.js"></script>
<link rel="stylesheet" href="sweetalert2.min.css">
<script>
$(document).ready(function (){
	$(document).on('click', '#btnSave', function(){
		Swal.fire({
			  position: 'top-end',
			  icon: 'success',
			  title: 'Your work has been saved',
			  showConfirmButton: false,
			  timer: 1500
			})
	});
	
	
	
});
</script>

<style>
@font-face {
    src: url("/bts/resources/fonts/Nanum/NanumSquareRoundR.ttf");
    font-family: "NanumSquareRoundR";
}

*{
	font-family: "NanumSquareRoundR";
}

h2{
	font-family: "NanumSquareRoundR";
	margin-bottom : 50px;
}
div.container{
	text-align : center;
}

img.check{
	margin-bottom : 100px;
	margin-top : 200px;
	width : 300px;
	height : 300px;
}
</style>


</head>
<body>
<div class="container">
	<img class="check" src="${contextPath}/resources/image/community/check.png">
	<h2>게시글이 성공적으로 등록되었습니다.</h2>
	<button type="button" class="btn btn-default" onclick="location.href='${contextPath}/community/plan_list'">목록으로 돌아가기</button>
<!-- <button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>  -->

</div>
</body>
</html>