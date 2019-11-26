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
<title>커뮤니티 글 수정</title>

<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script>
$(document).ready(function (){
	var plan_no = ${plan_no};
	console.log(plan_no);
	
	var arr_content = new Array();
	var arr_day = new Array();
	var arr_desc = new Array();
	var arr_no = new Array();
	var plan_title = null;
	
	<c:forEach var="planner" items="${detailPlanner}" varStatus="status">
		arr_content[${status.index}] = "${planner.content_id}";
		arr_day[${status.index}] = "${planner.day_no}";
		arr_desc[${status.index}] = "${planner.plan_desc}";
		arr_no[${status.index}] = "${planner.plan_no}";
		plan_title = "${planner.title}";
	</c:forEach>
	
	$('#title').prop('value', plan_title);
	$('#p_no').prop('value', plan_no);
	
	for(var i in arr_content){
		var serviceKey = '%2B50SHKR5TLKYKGJB1vUT27tbTUYeocbkQFjQVTN8m%2FtACpIoNMLXI3Q9xkQt%2BkdRQOdUkotl2i0ioIb2nwaC8w%3D%3D'
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

		         var title = document.createElement('h5');
		         $(title).prop('class', 'font-weight-light');
		         var title_text = document.createTextNode(resultArray.title);
		         title.appendChild(title_text);
		         
		         var img= document.createElement('img');
		         $(img).prop('class', 'content_image');
		         $(img).prop('src', resultArray.firstimage);
		         
		         var text = document.createElement('textarea');
		         $(text).prop('class', 'form-control');
		         $(text).prop('rows', '10');
		         $(text).prop('name', 'content' + i);
		         $(text).prop('id', 'content' + i);
		         $(text).prop('value', arr_desc[i]);
		         
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

$(document).on('click', '#btnSave', function(){
	var length = $('.content_div').length;
	console.log(length);
	var frmSave = document.form;
	frmSave.action="${contextPath}/community/plan_modify";
	frmSave.length.value=length;		
	frmSave.submit();
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
	padding-top : 30px;
	background-color : #F6F6F6;
}

strong{
	display : block;
}

table.table_content{
	align : center;
	width : 100%;
	margin-top : 10px;
}

img.file{
	width : 350px;
	height : 350px;
	display: block; 
	margin: 0px auto;
	
}

a.btn-example{
	display : block;
	text-align : center;
	color : black;
}

button.btn btn-sm btn-primary{
	float : right;
}
</style>




</head>
<body>
	<div class="container">
		<form name="form" id="form" method="post" action="${contextPath}/community/plan_save">
		<h1>글쓰기</h1>
			<div class="mb-3">
				<label for="title">제목</label>
				<input type="text" class="form-control" name="title" id="title" placeholder="제목을 입력해 주세요">
			</div>
			<div class="mb-3">
				<label for="content">내용</label>
				<div class="planner_detail">
				
				</div>	
			</div>
			
			<input type="hidden" name="length" value="">
			<input type="hidden" name="p_no" id="p_no" value="">
		</form>
		<button type="button" class="btn btn-sm btn-primary" id="btnSave">저장</button>
	</div>
</body>
</html>