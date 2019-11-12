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
<script>
	$(document).ready(function (){
		var arr_content = new Array();
		var arr_day = new Array();
		<c:forEach var="planner" items="${detailPlanner}" varStatus="status">
			arr_content[${status.index}] = "${planner.content_id}";
			arr_day[${status.index}] = "${planner.day_no}";
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
			         
			         
			         var text = document.createElement('textarea');
			         $(text).prop('class', 'form-control');
			         $(text).prop('rows', '10');
			         $(text).prop('name', 'content');
			         $(text).prop('id', 'content');
			         $(text).prop('placeholder', arr_day[i] + '의 내용을 입력해 주세요');
			         
			         var hr = document.createElement('hr');
			         
			         
			         
			         $('.planner_detail').append(div);
			         $('#content_div' + i).append(title);
			         $('#content_div' + i).append(img);
			         $('#content_div' + i).append(text);
			         $('#content_div' + i).append(hr);
			         

			      },
			      error : function(data, textStatus) {
			         alert("잘못된 접근입니다.")
			      }
			   });
		}
		
		
		
	});


	$(document).on('click', '#btnSave', function(){
		$("#form").submit();
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

h3{
	font-family: "NanumSquareRoundEB";
}

*{
	font-family: "NanumSquareRoundR";
}

textarea.form-control{
	width : 450px;
	float : right;
	display : inline-block;
}

img.content_image{
	width : 400px;
	height : 250px;	
}

div.content_div{
	padding-right : 70px;
	padding-left : 70px;
	background-color : #F6F6F6;
}

</style>

</head>
<body>
	<div class="container">
		<form name="form" id="form" method="post" action="${contextPath}/community/plan_list">
			<div class="mb-3">
				<label for="title">제목</label>
				<input type="text" class="form-control" name="title" id="title" placeholder="제목을 입력해 주세요">
			</div>
			<div class="mb-3">
				<label for="content">내용</label>
				<div class="planner_detail">
				
				</div>
				
			</div>
			<div class="mb-3">
				<label for="tag">TAG</label>
				<input type="text" class="form-control" name="tag" id="tag" placeholder="태그를 입력해 주세요">
			</div>
		</form>
	<div>
		<button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
	</div>
	</div>

</body>
</html>