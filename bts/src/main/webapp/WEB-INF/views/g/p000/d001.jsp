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
<title>추천</title>
<style>
@font-face {
	src: url("/bts/resources/fonts/Nanum/NanumSquareRoundEB.ttf");
    font-family: "NanumSquareRoundEB";
}

@font-face {
    src: url("/bts/resources/fonts/Nanum/NanumSquareRoundR.ttf");
    font-family: "NanumSquareRoundR";
}


#content{
	width : 1200px;
	height : 800px;
	margin : 0 auto;
	margin-top : 100px;
	
}
.main_img{
	width : 600px;
	height : 350px;
	display : inline-block;
	float : left;
}

.main_img2{
	width : 600px;
	height : 350px;
	display : inline-block;
	float : right;
	
}

#body a{
	display : inline-block;
	text-align : center;
	text-decoration : none;
}
#body h1{
	font-family: "NanumSquareRoundEB";
}
#body p{
	font-family: "NanumSquareRoundR";	
}

#recommend{
	width : 1200px;
	height : 350px;
	border : 1px solid #bcbcbc;
	margin-bottom : 30px;
}

#text{
	display : inline-block;
	width : 350px;
	margin : 100px 100px 100px 100px;
	text-align : center;
}

</style>
</head>
<body>
	<div id="content">
		<div id="recommend">
			<img class="main_img" src="${contextPath}/resources/image/recommend/main_image_2.jpg">
			<div id="text">
				<a href="${contextPath}/recommend/recommend_place"><h1>지역별 추천</h1></a>
				<p>서울 내 명소들을 지역별로 추천</p>
			</div>
		</div>
		
		<div id="recommend">
			<div id="text">
				<a href="${contextPath}/recommend/recommend_course"><h1>코스별 추천</h1></a>
				<p>서울 내 명소들을 코스별로 추천</p>			
			</div>
			<img class="main_img2" src="${contextPath}/resources/image/recommend/main_image_3.jpg">
		</div>
	</div>
</body>
</html>