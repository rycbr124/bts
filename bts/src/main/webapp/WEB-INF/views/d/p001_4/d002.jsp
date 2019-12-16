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
<title>커뮤니티 상세 글</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/comment/comment.css"> <!-- 커스텀 css -->
<script src="${contextPath}/resources/js/comment/comment.js"></script> <!-- 커스텀 js -->
<script src="${contextPath}/resources/js/report/report.js"></script> <!-- 커스텀 js -->


<script>
	$(document).ready(function (){
		init();
		
		function init(){
          var context="${contextPath}";
          var no="${plan_no}";
          var id="${sessionScope.memberInfo.member_id}";
          var url="${reqUrl}";
          setInit(context,no,id,url);
          var paging = ${initTotal};
          comPaging(paging);
	    }
		
		$(document).on('click','span.comment-report',function(){
			var reqUrl="${contextPath}/report/comment"
			var contents_cd=$(this).parent().parent().data('no');
			var target_id=$(this).parent().find('.comment-author').text();
			var popup = openReport(reqUrl,contents_cd,target_id);			
		});
		
		$('#contents-report').on('click',function(){
			var reqUrl="${contextPath}/report/article/plan"
			var contents_cd="${plan_no}";
			var target_id="${result[0].member_id}";
			var popup = openReport(reqUrl,contents_cd,target_id);
		});
		
		
		
		
		
		var arr_content = new Array();
		var arr_day = new Array();
		var arr_desc = new Array();
		var arr_no = new Array();
		<c:forEach var="planner" items="${detailPlanner}" varStatus="status">
			arr_content[${status.index}] = "${planner.content_id}";
			arr_day[${status.index}] = "${planner.day_no}";
			arr_desc[${status.index}] = "${planner.plan_desc}";
			arr_no[${status.index}] = "${planner.plan_no}";
		</c:forEach>
		console.log(arr_content);
		console.log(arr_day);
		
		var titleBox = document.createElement('div');
		$(titleBox).prop('class', 'titleBox');
		
		var titleImage = document.createElement('img');
		$(titleImage).prop('src', "${contextPath}/resources/image/community/computer.png");
		$(titleImage).prop('class', 'titleImage');
		
		var titleDesc = document.createElement('h2');
		$(titleDesc).prop('class', 'titleDesc');
		var descText = document.createTextNode('여행일정');
		titleDesc.appendChild(descText);
		
		$('.planner_detail').append(titleBox);
		$('.titleBox').append(titleImage);
		$('.titleBox').append(titleDesc);
		
		
		
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
			         
			         var day_div = document.createElement('div');
			         $(day_div).prop('class', 'day_div');
			         var day_text = document.createTextNode(arr_day[i]);
			         day_div.appendChild(day_text);
			         
			         var title = document.createElement('h3');
			         $(title).prop('class', 'font-weight-light');
			         var title_text = document.createTextNode(resultArray.title);
			         title.appendChild(title_text);
			         
			         var a = document.createElement('a');
			         $(a).prop('href', '${contextPath}/recommend/place_detail?contentid=' + arr_content[i] + '&contenttypeid=' + resultArray.contenttypeid);
			         a.appendChild(title);
			         		         
			         var img= document.createElement('img');
			         $(img).prop('class', 'content_image');
			         $(img).prop('src', resultArray.firstimage);
			         
			         var text = document.createElement('div');
			         $(text).prop('class', 'content_text');
			         var desc_text = document.createTextNode(arr_desc[i]);
			         var left = document.createTextNode('❝ ');
			         var right = document.createTextNode(' ❞');
			         
			         
			         
			         text.appendChild(left);
			         text.appendChild(desc_text);
			         text.appendChild(right);
			         
			         
			         
			         $('.thumb_nail').prop('src', resultArray.firstimage);
			   
			         var hidden = document.createElement('input');
			         $(hidden).prop('type', 'hidden');
			         $(hidden).prop('name', 'content_id' + i);
			         $(hidden).prop('value', arr_content[i]);
			         			         
			         $('.planner_detail').append(day_div);
			         $('.planner_detail').append(div);
			         $('#content_div' + i).append(a);
			         $('#content_div' + i).append(img);
			         $('#content_div' + i).append(text);
			         $('#content_div' + i).append(hidden);
			         
			      },
			      error : function(data, textStatus) {
			         alert("잘못된 접근입니다.")
			      }
			   });
		}
		
		var session_id = "${sessionScope.memberInfo.member_id}";
		console.log(session_id);
		<c:forEach var="article" items="${result}">
			var member_id = "${article.member_id}";
		</c:forEach>
		console.log(member_id);
		console.log("12121 : " + arr_no[0]);
		if(session_id == member_id){
			var m_button = document.createElement('input');
			$(m_button).prop('type', 'button');
			$(m_button).prop('value', '수정');
			$(m_button).prop('class', 'btn btn-default btn-sm');
			$(m_button).attr('onclick', 'location.href="${contextPath}/community/plan_update?plan_no=' + arr_no[0] + '"');
			
			var d_button = document.createElement('input');
			$(d_button).prop('type', 'button');
			$(d_button).prop('value', '삭제');
			$(d_button).prop('class', 'btn btn-default btn-sm');
			$(d_button).attr('onclick', 'location.href="${contextPath}/community/plan_delete?plan_no=' + arr_no[0] + '"');
			$('div.justify-content-md-end').append(m_button);
			$('div.justify-content-md-end').append(d_button);	
			
		}
		var list_button = document.createElement('input');
		$(list_button).prop('type', 'button');
		$(list_button).prop('value', '목록');
		$(list_button).prop('class', 'btn btn-default btn-sm');
		$(list_button).attr('onclick', 'location.href="${contextPath}/community/plan_list"');
		$('div.justify-content-md-end').append(list_button);	
		
	});

	
	
	
	
</script>

<style>
@font-face {
	src: url("/bts/resources/fonts/Nanum/NanumSquareRoundEB.ttf");
    font-family: "NanumSquareRoundEB";
}
@font-face {
	src: url("/bts/resources/fonts/Nanum/NanumSquareRoundB.ttf");
    font-family: "NanumSquareRoundB";
}
@font-face {
    src: url("/bts/resources/fonts/Nanum/NanumSquareRoundR.ttf");
    font-family: "NanumSquareRoundR";
}
h1{
	font-family: "NanumSquareRoundB";
	
	margin-left : 30px;	
}
h2{
	font-family : "NanumSquareRoundEB";
}
h3.font-weight-light{
	color : black;
}
.plan_header{
	max-height:400px;
}
strong{
	display : block;
	margin-left : 30px;
}
a:hover{
	color : black;
}
*{
	font-family: "NanumSquareRoundR";
}
img.thumb_nail{
	width : 1110px;
	height : 400px;
}
div.title{
	width : 1110px;
	height : 200px;
	position : relative;
	top : -200px;	
	background-color:rgba(130,130,130,0);
    background-image: linear-gradient(rgba(100%,100%,100%,0), rgba(100,100,100));
    color : white;
    bottom : 0px;s
	
}
img.content_image{
	width : 300px;
	height : 150px;
}
div.content_text{
	display : inline-block;
	float : right;
	text-align : center;
}
div.planner_detail{
	padding : 80px;	
	background-color : #F8F8FA;
}
div.justify-content-md-end{
	background-color : #F8F8FA;
	padding-bottom : 10px;
}
div.mx-auto{
	background-color : #F8F8FA;
}
#contents-info{
	background-color : #F8F8FA;
}
div.comment{
	background-color : #F8F8FA;
	margin : 0px;
}
div.content_div{
	border : solid 0.8px #D5D5D5;
	padding : 20px;
	margin-bottom : 20px;
	background-color : white;
}
div.day_div{
	width : 80px;
	background-color : #003399;
	color : white;
	text-align : center;
}
div.quoatation{
	display : inline-block;
	font-size : 50px;
}
div.titleBox{
	margin-bottom : 10px;
}
img.titleImage{
	width : 50px;
	height : 50px;
	margin-right : 10px;
}
h2.titleDesc{
	display : inline-block;
}

#contents-info{
	margin-bottom : 0px;
}
</style>
</head>
<body>
	<div class="container">
		<div class ="plan_header">
		<c:forEach var="article" items="${result}">
			<img class="thumb_nail" >
			<div class="title">
				<h1>${article.title}</h1>
				<strong>등록일. ${article.register_date}</strong>
				<strong>작성자. ${article.member_id}</strong>
				<strong>여행날짜. ${article.range_date}</strong>
				<strong>여행타입. ${article.person_se}</strong>
			</div>
		</c:forEach>
		</div>
		<!-- javascript로 처리할 부분 -->
		<div class="planner_detail">
		
		</div>
		<div class="row justify-content-md-end">
		
		</div>
		
		
		<div id='contents-info'>
			<span id="comment-count">
				<i class="far fa-comment-dots fa-2x"></i>
				<span>${paging.totalCount}</span>
			</span>
			<span id="view-count"></span>
			<span id="contents-report">게시글 신고</span>
		</div>
		<div class="comment">
		<div id="comment-form" class="mx-auto">
			<form name="frmCom" class="row justify-content-md-end"  action="${contextPath}/community/comment/write" method="post">
				<textarea name="input-comment"></textarea>
				<input type="submit" class="btn btn-outline-secondary btn-sm" value="작성하기">
				<input type="hidden" name="article_no" value="${plan_no}">
			</form>
		</div>
		
		<div id="comments">
		</div>
		</div>
		<div id="comment-paging">
			<ul id="paging-list" class="pagination justify-content-center pagination-sm">
			</ul>
		</div>

	</div>
</body>
</html>