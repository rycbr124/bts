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

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function (){
		var arr_content = new Array();
		var arr_day = new Array();
		var arr_desc = new Array();
		<c:forEach var="planner" items="${detailPlanner}" varStatus="status">
			arr_content[${status.index}] = "${planner.content_id}";
			arr_day[${status.index}] = "${planner.day_no}";
			arr_desc[${status.index}] = "${planner.plan_desc}";
		</c:forEach>
		console.log(arr_content);
		console.log(arr_day);

		for(var i in arr_content){
			console.log("1111 : " + arr_content[i]);
			var serviceKey = 'dt2Nu%2Bu9tgj6Kwy1XIKjBFD8Ns8Etgi2jM6AuzJpQ1Hs%2Fy3WN2RSZU8PnK3MG15kw2UPyDjHSnaBkw7GTASqHA%3D%3D'
			var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=' + serviceKey + '&contentId=' + arr_content[i] + '&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y';
			
			$.ajax({
			      async : false,
			      url : reqUrl,
			      dataType : 'json',
			      success : function(data, textStatus) {
			         var resultArray = data.response.body.items.item;
			         var div = document.createElement('div');
			         $(div).prop('class', 'content_div');
			         $(div).prop('id', 'content_div' + i);

			         var title = document.createElement('h3');
			         $(title).prop('class', 'font-weight-light');
			         var title_text = document.createTextNode(resultArray.title);
			         title.appendChild(title_text);
			         
			         var img= document.createElement('img');
			         $(img).prop('class', 'content_image');
			         $(img).prop('src', resultArray.firstimage);
			         
			         var text = document.createElement('div');
			         $(text).prop('class', 'content_text');
			         var desc_text = document.createTextNode(arr_desc[i]);
			         text.appendChild(desc_text);
			         
			         
			         $('.thumb_nail').prop('src', resultArray.firstimage);
			   
			         var hidden = document.createElement('input');
			         $(hidden).prop('type', 'hidden');
			         $(hidden).prop('name', 'content_id' + i);
			         $(hidden).prop('value', arr_content[i]);
			         			         

			         $('.planner_detail').append(div);
			         $('#content_div' + i).append(title);
			         $('#content_div' + i).append(img);
			         $('#content_div' + i).append(text);
			         $('#content_div' + i).append(hidden);
			         
			      },
			      error : function(data, textStatus) {
			         alert("잘못된 접근입니다.")
			      }
			   });
		}
				
	});
</script>

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
	font-family : "NanumSquareRoundEB";
	display : inline-block;
	margin-left : 30px;	
}

h2{
	font-family : "NanumSquareRoundEB";
}

*{
	font-family: "NanumSquareRoundR";
}


img.thumb_nail{
	width : 1110px;
	height : 400px;
}

div.title{
	width : 1000px;
	height : 200px;
	margin-left : 55px;
	background-color : white;
	opacity : 0.5;
	position : relative;
	top : -200px;
}

img.content_image{
	width : 500px;
	height : 300px;
}

div.content_text{
	display : inline-block;
}

</style>
</head>
<body>
	<div class="container">
	
		<c:forEach var="article" items="${result}">
			<img class="thumb_nail" >
			<div class="title">
				<h1>${article.title}</h1>
				<strong>등록일. ${article.register_date}</strong>
				<strong>작성자. ${article.member_id}</strong>
			</div>
		</c:forEach>
		
		<!-- javascript로 처리할 부분 -->
		<div class="planner_detail">
			
		</div>
	<input type="button" value="글쓰기" class="btn btn-outline-secondary btn-sm" onClick="location.href='${contextPath}/community/plan_write'">
	</div>
</body>
</html>