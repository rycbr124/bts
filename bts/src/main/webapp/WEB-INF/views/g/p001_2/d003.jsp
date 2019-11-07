
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
<script type="text/javascript" src="${contextPath}/resources/js/recommend3.js"></script>  <!-- 커스텀 js --> 
<script>
	$(document).ready(function(){
		var id = ${contentid};
		image_init(id);
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

h1{
	font-family: "NanumSquareRoundEB";
}

p{
	font-family: "NanumSquareRoundR";	
}
col-lg-7{
	height:665px;
	width:580pd;
}
.my-5{
height:600px;
border-bottom:1px solid rbg(127,127,127);

}
.mb-lg-0{
	width:603px;
	height:480px;
}
                                                                                             
.detail_image_container{
	width:603px;	
	height:auto;
	
}
#detail_image{
	width:80px;
	height:50px;
}

.title{
	float : left;
}

.content{
	float : right;
	display : inline-block;
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



</style>

</head>
<body>
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
	


    <!-- Call to Action Well -->
    <div class="card-body" style="background-color:white; padding:0; margin-right:50%; transform:translate(20%);">
     	<div id="map" style="width:900px;height:300px; position:relative;">
    </div>
    <!-- Content Row -->
    <div>
    
	    <div class="title">
	    	<h1>소개</h1>
	    </div>
	    
	    
	    <div class="content">
	    	
	    	
	    </div>
    </div>

  </div>
  <!-- /.container -->
</div>
</body>
</html>