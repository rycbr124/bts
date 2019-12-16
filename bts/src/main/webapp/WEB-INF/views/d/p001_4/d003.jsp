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
<title>커뮤니티 글 작성</title>
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script>
/* 팝업 js */
	$(document).ready(function (){
		
	});


	$(document).on('click', '#btnSave', function(){
		var length = $('div.by_dayInfo > div').length;
		console.log(length);
		var frmSave = document.form;
		frmSave.action="${contextPath}/community/plan_save";
		frmSave.length.value=length;		
		frmSave.submit();
	});
	
	
	$(function load_plan(plan_no){
		var plan_no = ${plan_no};
		console.log(plan_no);
		
		var plan_day = new Array();
		var title = null;
		var p_no = null;
		var plan_date = null;
		var plan_desc = new Array();
		
		$.ajax({
			type : "post",
			async : false,
			url : "${contextPath}/community/load_plan",
			data : {
				plan_no:plan_no
			},
			dataType : 'json',
			success : function(data, textStatus){
				$('.planner_detail').empty();
				//div에 값 넣는 부분
				var content_arr = new Array();
				var day_arr = new Array();
				for(var i in data){
					console.log(data[i].content_id);
					console.log(data[i].plan_no);
					console.log(data[i].plan_desc);
					var dayNo = data[i].day_no;
					var contentid = data[i].content_id;
					plan_day[i] = data[i].day_no;
					content_arr.push(contentid);
					title = data[i].title;
					p_no = data[i].plan_no;
					day_arr.push(p_no);
					plan_date = data[i].range_date;
					plan_desc[i] = data[i].plan_desc;
					
					$('#title').prop('value', title);
					$('#p_no').prop('value', p_no);
					////////////////////////////////////////////////////					
				}
				console.log(plan_day);
				console.log(plan_desc);
				//day 중복제거
				var day_arr = new Array();
				
				$.each(plan_day, function(i, value){
					if(day_arr.indexOf(value) == -1) day_arr.push(value);
				});
				console.log(day_arr);
				console.log(day_arr.length);
				var arr_length = day_arr.length;
				//day 수 만큼 div생성
				for(var i=0; i < arr_length; i++){
					var div = document.createElement('div');
					$(div).prop('class', 'DAY' + (i+1));
					$('#form').append(div);
					$(div).prop('id','day_container');
					
					var day_info = document.createElement('div');
					$(day_info).prop('class', 'day_info');
					$(div).append(day_info);
					
					var day_text = document.createElement('div');
					$(day_text).prop('class', 'day_text');
					$(day_info).append(day_text);
					$(day_text).text('DAY' + (i+1));
					
					var by_dayInfo = document.createElement('div');
					$(by_dayInfo).prop('class', 'by_dayInfo');
					$(day_info).append(by_dayInfo);
					
				}
				
				
				
				for(var i in data){
					var serviceKey = 'dt2Nu%2Bu9tgj6Kwy1XIKjBFD8Ns8Etgi2jM6AuzJpQ1Hs%2Fy3WN2RSZU8PnK3MG15kw2UPyDjHSnaBkw7GTASqHA%3D%3D'
					var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=' + serviceKey + '&contentId=' + content_arr[i] + '&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y';
					
					$.ajax({
					      async : false,
					      url : reqUrl,
					      dataType : 'json',
					      success : function(data, textStatus) {
					         var resultArray = data.response.body.items.item;
					         var img = document.createElement('img');
								console.log(plan_day);
							 var day_name = document.getElementsByClassName(plan_day[i])[0];
							 console.log(day_name);
							 var infoContainer = $(day_name).children('.day_info').children('.by_dayInfo');
							 var detail_info = document.createElement('div');
							 $(detail_info).prop('class', 'content_div' + i);
							 $(detail_info).prop('id', 'content_div');
							 console.log(infoContainer);
							 $(infoContainer).append(detail_info);
							 
							 var img_container = document.createElement('div');
							 $(img_container).prop('class', 'img_container');
							 
							 var img = document.createElement('img');
							 $(img).prop('class', 'content_img');
							 $(img).prop('src', resultArray.firstimage);
							 
							 var title = document.createElement('div');
							 $(title).prop('class', 'title');
							 $(title).text(resultArray.title);
							 
							 $('.content_div' + i).append(img_container);
							 $(img_container).append(img);
							 $('.content_div' + i).append(title);
							 
							 var text = document.createElement('textarea');
							 $(text).prop('class', 'form-control');
					         $(text).prop('rows', '8');
					         $(text).prop('name', 'content' + i);
					         $(text).prop('id', 'content' + i);
					         $(text).prop('value', plan_desc[i]);
					         
					         
					         var hidden = document.createElement('input');
					         $(hidden).prop('type', 'hidden');
					         $(hidden).prop('name', 'content_id' + i);
					         $(hidden).prop('value', content_arr[i]);
					         
					         $('.content_div' + i).append(text);		
					         $('.content_div' + i).append(hidden);		
					      },
					      error : function(data, textStatus) {
					         alert("잘못된 접근입니다.")
					      }
					   });
				}
				
				var buttonDiv = document.createElement('div');
				$(buttonDiv).prop('class', 'row justify-content-md-end');
				$(form).append(buttonDiv);
			
				var button = document.createElement('button');
				$(button).prop('type', 'button');
				$(button).prop('class', 'btn btn-outline-secondary');
				$(button).prop('id', 'btnSave');
				$(button).text('저장');
				$(buttonDiv).append(button);
			},
			error : function(data, textStatus) {
		         alert("잘못된 접근입니다.")
		    }
			
		});
	
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
	position:relative;
	display:inline-block;
	transform:translate(100%,0);
	
}

img.content_img{
	position:relative;
	width : 300px;
	height : 200px;	
	min-width : 300px;
}

div.img_container{
	position:absolute;
	width : 300px;
	height : 200px;	
	border-right : 1px solid #BDBDBD;
}

div.day_text{
	border : 1px solid #203341;
	background : #203341;
	color : white;
	font-size : 25px;
	text-align : center;
	width : 100px;
	height : 50px;
	line-height : 50px; 
}

div.title{
	width : 300px;
	margin-right : 0;
	position : absolute;
	transform:translate(0,-100%);
}


#content_div{
	background : #F8F8FA;
	padding : 30px;
	border-bottom : 1px solid #BDBDBD;
}

#day_container{
	margin-top : 20px;
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
	width : 150px;
	height : 150px;
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

div.box{
	width : 400px;
	text-align : center;
}

button{
	margin-top : 10px;
}
</style>
</head>
<body>
	<div class="container">
		<form name="form" id="form" method="post" action="${contextPath}/community/plan_save">
		<h1>글쓰기</h1>
			<div class="mb-3">
				<label for="title">제목</label>
				<input type="text" class="form-control bg-light border-0" name="title" id="title" placeholder="제목을 입력해 주세요">
				<input type="hidden" name="length" value="length">
				<input type="hidden" name="p_no" id="p_no" value="">
			</div>
		</form>
	</div>
</body>
</html>