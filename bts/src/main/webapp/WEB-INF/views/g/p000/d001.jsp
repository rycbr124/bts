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

<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style>
img{
	width : 375px;
	height : 279px;
}

img.mini{
	width : 95px;
	height : 90px;
}

h4{
	color : black;
}

.btn btn-info btn-sm{
	float : right;
}

</style>

</head>
<body>
	<div class="container">
	    <div class="col-md-8">
	        <div class="row">
	            <div class="col-md-6 zero_mp">
	                <div class="event_item">
	                    <div class="event_img">
	                        <img src="${contextPath}/resources/image/recommend/main_image_1.jpg" alt="">
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-6 zero_mp">
	                <div class="event_item">
	                    <div class="event_text text-center">
	                        <a href="${contextPath}/recommend/recommend_place"><h4>지역별 추천</h4></a>
	                        <h6>15-16 May in Dhaka</h6>
	                        <p>Lorem ipsum dolor sit amet, consectetur adip scing elit. Lorem ipsum dolor sit amet,consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
	                        <a href="" class="event_btn">read more</a>
	                    </div>
	                </div>
	            </div>
	        </div>
	        <!--End of row-->
	        <div class="row">
	            <div class="col-md-6 zero_mp">
	                <div class="event_item">
	                    <div class="event_text text-center">
	                        <a href="${contextPath}/recommend/recommend_course"><h4>코스별 추천</h4></a>
	                        <h6>15-16 May in Dhaka</h6>
	                        <p>Lorem ipsum dolor sit amet, consectetur adip scing elit. Lorem ipsum dolor sit amet,consectetur adipiscing elit. Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
	                        <a href="" class="event_btn">read more</a>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-6 zero_mp">
	                <div class="event_item">
	                    <div class="event_img">
	                        <img src="${contextPath}/resources/image/recommend/main_image_1.jpg" alt="">
	                    </div>
	                </div>
	            </div>
	        </div>
	        <!--End of row-->
	    </div>
	    
	    <!--End of col-md-8-->
	    <div class="col-md-4">
	        <div class="event_news">
	            <div class="event_single_item fix">
	                <div class="event_news_img floatleft">
	                    <img src="${contextPath}/resources/image/recommend/main_image_1.jpg" alt="" class="mini">
	                </div>
	                <div class="event_news_text">
	                    <a href="#"><strong>Let’s plant 200 tree each...</strong></a>
	                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, veniam.</p>
	                </div>
	            </div>
	        </div>
	        <div class="event_news">
	            <div class="event_single_item fix">
	                <div class="event_news_img floatleft">
	                    <img src="${contextPath}/resources/image/recommend/main_image_1.jpg" alt="" class="mini">
	                </div>
	                <div class="event_news_text">
	                    <a href="#"><strong>Keep your house envirome..</strong></a>
	                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, veniam.</p>
	                </div>
	            </div>
	        </div>
	        <div class="event_news">
	            <div class="event_single_item fix">
	                <div class="event_news_img floatleft">
	                    <img src="${contextPath}/resources/image/recommend/main_image_1.jpg" alt="" class="mini">
	                </div>
	                <div class="event_news_text">
	                    <a href="#"><strong>Urgent Clothe Needed Needed</strong></a>
	                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aliquid, veniam.</p>
	                </div>
	            </div>
	        </div>


	    <!--End of col-md-4-->
    </div>
    
    </div>
</body>
</html>