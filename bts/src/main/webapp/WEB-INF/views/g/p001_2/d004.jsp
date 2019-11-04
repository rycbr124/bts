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
<script src="http://code.jquery.com/jquery-1.10.2.js"></script> <!-- jQuery -->
<script type="text/javascript" src="${contextPath}/resources/js/recommend4.js"></script>  <!-- 커스텀 js --> 
<script>
	$(document).ready(function(){
		var id = ${contentid};
		image_init(id);
	});

</script>

<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap/bootstrap.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6b2f7da39af5c9b3e7839e09fedbc28a"></script>

<style>
@font-face {
	src: url("/bts/resources/fonts/Nanum/NanumSquareRoundEB.ttf");
    font-family: "NanumSquareRoundEB";
}

@font-face {
    src: url("/bts/resources/fonts/Nanum/NanumSquareRoundR.ttf");
    font-family: "NanumSquareRoundR";
}

@font-face{
	src: url("/bts/resources/fonts/Nanum/NanumSquareRoundB.ttf");
    font-family: "NanumSquareRoundB";
}

h1{
	font-family: "NanumSquareRoundEB";
}

h3{
	font-family: "NanumSquareRoundB";
}

p{
	font-family: "NanumSquareRoundR";
}

img.card-img{
	width : 450px;
	height : 250px;
	display : inline-block;
}

</style>
</head>
<body>
<form name="content" method="post">

<!-- Page Content -->
  <div class="container">

    <!-- Heading Row -->
    <div class="row align-items-center my-5">
      <div class="col-lg-7">
  
      </div>
      <!-- /.col-lg-8 -->
      <div class="col-lg-5">

      
      </div>
      <!-- /.col-md-4 -->
    </div>
    <!-- /.row -->
	
	<hr>
    
    <!-- Call to Action Well -->
      <div class="card-body" style="background-color:white; padding:0; margin-right:50%; transform:translate(20%);">
       	<div id="map" style="width:900px;height:300px; position:relative;">
      </div>
    <!-- Content Row -->

	  <div class="content">

	  </div>    	

  </div>
  <!-- /.container -->
</div>
</form>
</body>
</html>