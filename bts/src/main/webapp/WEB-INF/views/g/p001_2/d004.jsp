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
<title>코스 상세페이지</title>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script> <!-- jQuery -->
<script type="text/javascript" src="${contextPath}/resources/js/recommend/recommend4.js"></script>  <!-- 커스텀 js --> 
<script>
	$(document).ready(function(){
		var id = ${contentid};
		var command = '${command}';
		image_init(id, command);
	});
	function wish_list(){
		
		var contentid = ${contentid};
		
		console.log(contentid);
		
		console.log("성공 !");
		var frmWish = document.wish;
		
		frmWish.action = "/bts/recommend/insert_wishlist";
		frmWish.contentid.value = contentid;
		frmWish.submit();
	};

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
.container{
	top:0;
}
.body_content{
	position:relative;
	margin:0 auto;
	width:1300px;
}
.my-5{
	position:relative;
}
col-lg-5{
	position:absolute;
	top:0;
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
#map{
margin:0 auto;
}
.card-body{
	margin:0 auto;
}
img.card-img{
	width : 450px;
	height : 250px;
}
#ul_list{
	width:100%;
	height:auto;
}
#content_list #ul_list #li_text{
display:inline-block;
width:500px;
rigth:0;
margin-left : 100px;
}
#content_list #ul_list #li_image{
	display:inline-block;
	width:450px;
	margin:0;
}
.content{
	margin-left : 80px;
}
[id='toggle-heart'] {
  position: absolute;
  left: -100vw;
  vertical-align:bottom;
}

[for='toggle-heart'] {
  color: #aab8c2;
}

[id='toggle-heart']:checked + label {
  color: #e2264d;
}

[for='toggle-heart'] {
  font-size: 2em;
  cursor: pointer;
}

[for='toggle-heart'] { 
  align-self: center; 
}
div.divHeart{
	width : 140px;
	margin-bottom : 10px;
	height : 20px;
}
img.heart{
	width : 25px;
	height : 25px;
	display : inline-block;
	float : right;
	position : absolute;
}
.divHeart > p {
	width : 105px;
	position : relative;
	margin : 0;
	float : left;
}
</style>
</head>
<body>

<!-- Page Content -->
  <div class="body_content">

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
      <div class="card-body" >
       	<div id="map" style="width:900px;height:300px; position:relative;">
      </div>
    <!-- Content Row -->

	  <div class="content">

	  </div>    	

  </div>
  <!-- /.container -->
</div>

</body>
</html>