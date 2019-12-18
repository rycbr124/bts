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
<title>커뮤니티</title>
<link rel="stylesheet" href="${contextPath}/resources/css/boardHeader.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="${contextPath}/resources/js/community_plan/community.js"></script>
<script>
$(document).ready(function (){
var thumNail = new Map();
var arr_contentId = new Array();

<c:forEach var="thumnail" items="${listThumnail}" varStatus="status">
	var plan_no = "${thumnail.plan_no}";
	var content_id = "${thumnail.content_id}";
	thumNail.set(plan_no, content_id);
	arr_contentId.push(content_id);
</c:forEach>
console.log(thumNail);
console.log(arr_contentId);
console.log(arr_contentId.length);

<c:forEach var="article" items="${listArticle}" varStatus="status">
var plan_no = '${article.plan_no}';
console.log(plan_no);
var content_id = thumNail.get(plan_no);
console.log(content_id);


var serviceKey = 'dt2Nu%2Bu9tgj6Kwy1XIKjBFD8Ns8Etgi2jM6AuzJpQ1Hs%2Fy3WN2RSZU8PnK3MG15kw2UPyDjHSnaBkw7GTASqHA%3D%3D'
var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=' + serviceKey + '&contentId=' + content_id + '&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y';

$.ajax({
    async : false,
    url : reqUrl,
    dataType : 'json',
    success : function(data, textStatus) {
    	var resultArray = data.response.body.items.item;
    	console.log("1222111 : " + resultArray.title);
   
    	var figure = document.createElement('figure');
    	$(figure).prop('class', 'snip1321');
    	$(figure).prop('id', 'snip' + ${status.index});
    	
    	var img = document.createElement('img');
    	$(img).prop('src', resultArray.firstimage);
    	$(img).prop('alt', 'sq-sample26');
    	$(img).prop('id', 'image' + ${status.index});
    	$(img).prop('class', 'thumImage');
    	
    	var figcaption = document.createElement('figcaption');
    	$(figcaption).prop('class', 'fig' + ${status.index});
    	
    	var id = document.createElement('h4');
    	$(id).prop('class', 'id');
    	var id_text = document.createTextNode('${article.member_id}');
    	id.appendChild(id_text);
    	
    	var title = document.createElement('h2');
    	$(title).prop('class', 'title');
    	var title_text = document.createTextNode('${article.title}');
    	title.appendChild(title_text);
    	
    	
    	
    	var href = document.createElement('a');
    	$(href).prop('href', '${contextPath}/community/plan_contents?plan_no=${article.plan_no}');
    		    	
    	$('.content').append(figure);
        $('#snip' + ${status.index}).append(img);
        $('#snip' + ${status.index}).append(figcaption);
        $('.fig' + ${status.index}).append(id);
        $('.fig' + ${status.index}).append(title);
        $('#snip' + ${status.index}).append(href);		
    	
        var info_box = document.createElement('div');
        var info_box_title = document.createElement('p');
        var info_box_viewCnt = document.createElement('p');
        var info_box_member = document.createElement('p');
    	$(info_box).prop('class','info_box');
		$(info_box).append(info_box_title);
		$(info_box_title).prop('class','info_box_person');
		$(info_box_title).text('${article.person_se}')
		$(info_box).append(info_box_viewCnt);
		$(info_box_viewCnt).prop('class','info_box_viewCnt');
		$(info_box_viewCnt).text('${article.view_cnt}')
		$(info_box).append(info_box_member);
		$(info_box_member).prop('class','info_box_member');
		$(info_box_member).text('${article.member_id}')
		$(figure).append(info_box);
    },
    error : function(data, textStatus) {
        alert("잘못된 접근입니다.");
     }
});

</c:forEach>
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

*{
    font-family: "NanumSquareRoundR";
}
table{
	text-align : center;
	width:100%;
}

#body>th{
	font-family: "NanumSquareRoundEB";
	
}

td{
	font-family: "NanumSquareRoundR";
		
}

#body>h2{
	font-family: "NanumSquareRoundEB";
}
#body>p {
	font-family: "NanumSquareRoundR";	
}

.container>img{
	width : 1110px;
	height : 400px;
	margin-bottom : 50px;
}

img.thumImage{
	width : 100%;
	height : 100%;
}

div#paging{
	display: block; 
	text-align: center;
}

.content{
	margin : 0 auto;
	
}

h4.id{
	font-size : 16px;
}

h2.title{
	font-size : 22px;
}

@import url(https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css);
@import url(https://fonts.googleapis.com/css?family=Raleway:400,500,800);
figure.snip1321 {
  font-family: 'Raleway', Arial, sans-serif;
  position: relative;
  overflow: hidden;
  height : auto;
  width: 350px;
  margin: 10px; 
  color: #000000;
  text-align: center;
  -webkit-perspective: 50em;
  perspective: 50em;
  display : inline-block;
}
figure.snip1321 * {
  -webkit-box-sizing: padding-box;
  box-sizing: padding-box;
  -webkit-transition: all 0.2s ease-out;
  transition: all 0.2s ease-out;
}
figure.snip1321 img {
	width:100%;
	height:auto;
	min-height:210px;
	max-height:210px;
  	vertical-align: top;
}
figure.snip1321 figcaption {
  top: 30%;
  left: 20px;
  right: 20px;
  position: absolute;
  opacity: 0;
  z-index: 1;
}
figure.snip1321 h2,
figure.snip1321 h4 {
  margin: 0;
}
figure.snip1321 h2 {
  font-weight: 600;
}
figure.snip1321 h4 {
  font-weight: 400;
  text-transform: uppercase;
}
figure.snip1321 i {
  font-size: 32px;
}
figure.snip1321:after {
  background-color: rgba(255,255,255,0.5);
  position: absolute;
  content: "";
  display: block;
  top: 20px;
  left: 20px;
  right: 20px;
  bottom: 40px;
  -webkit-transition: all 0.4s ease-in-out;
  transition: all 0.4s ease-in-out;
  -webkit-transform: rotateX(-90deg);
  transform: rotateX(-90deg);
  -webkit-transform-origin: 50% 50%;
  -ms-transform-origin: 50% 50%;
  transform-origin: 50% 50%;
  opacity: 0;
  height:170px;
}
figure.snip1321 a {
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  position: absolute;
  z-index: 1;
}
figure.snip1321:hover figcaption,
figure.snip1321:hover figcaption {
  	-webkit-transform: translateY(-50%);
  	transform: translateY(-50%);
  	opacity: 1;
  	-webkit-transition-delay: 0.2s;
  	transition-delay: 0.2s;
}
figure.snip1321:hover:after,
figure.snip1321:hover:after {
	height:170px;
  	-webkit-transform: rotateX(0);
  	transform: rotateX(0);
  	opacity: 0.9;
}
.info_box{
	border:1px solid rgb(200,200,200);
	border-top:0;
	height:100px;
	padding:15px;
	color:rgb(111,111,111);
	font-size:15px;
}
.info_box .info_box_person{
	position:absolute;
	width:100px;
	height:auto;
	text-align:left;
} 
.info_box .info_box_viewCnt{
	width:100px;
	height:14px;
	float:right;
	top:0;
	display:inline-block;
	text-align:right;
}
.info_box .info_box_member{
	position:absolute;
	width:auto;
	height:20px;
	bottom:0;
	text-align:left;
}
.info_box .info_box_viewCnt:before{
	background:url('/bts/resources/image/icon/view.png') no-repeat 0px 0px;
	content:"";
	display:inline-block;
	width:14px;
	height:14px;
	background-size:14px;
	margin-right:5px;
}
.info_box .info_box_member:before{
	background:url('/bts/resources/image/icon/person.png') no-repeat 0px 0px;
	content:"";
	display:inline-block;
	width:15px;
	height:15px;
	background-size:15px;
	margin-right:5px;
}

.search-form .form-group {
  float: right !important;
  transition: all 0.35s, border-radius 0s;
  width: 32px;
  height: 32px;
  background-color: #fff;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
  border-radius: 25px;
  border: 1px solid #ccc;
}
.search-form .form-group input.form-control {
  padding-right: 20px;
  border: 0 none;
  background: transparent;
  box-shadow: none;
  display:block;
}
.search-form .form-group input.form-control::-webkit-input-placeholder {
  display: none;
}
.search-form .form-group input.form-control:-moz-placeholder {
  /* Firefox 18- */
  display: none;
}
.search-form .form-group input.form-control::-moz-placeholder {
  /* Firefox 19+ */
  display: none;
}
.search-form .form-group input.form-control:-ms-input-placeholder {
  display: none;
}
.search-form .form-group:hover,
.search-form .form-group.hover {
  width: 100%;
  border-radius: 4px 25px 25px 4px;
}
.search-form .form-group span.form-control-feedback {
  position: absolute;
  top: -1px;
  right: -2px;
  z-index: 2;
  display: block;
  width: 34px;
  height: 34px;
  line-height: 34px;
  text-align: center;
  color: #3596e0;
  left: initial;
  font-size: 14px;
}

#search{
	width : 20%;
	display : inline-block;
}

#selectBox{
	width : 100px;
	display : inline-block;
}

.main-board{
	background:url('${contextPath}/resources/image/community/community_main.jpg');
}

.main-info h1{
	font-size: 2.2em;
}
</style>
</head>
<body>
<form name="form" id="form" method="post">
	<div class="container">
		<div class="main-board">
			<div class='main-info'>
				<div class='main-back mx-auto'>
					<h1>커뮤니티</h1>
					<span class="main-detail">BTS와 함께 나만의 여행계획을 공유하세요!</span>
				</div>
			</div>
		</div>
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link active"
				href="${contextPath}/community/plan_list">계획</a></li>
			<li class="nav-item"><a class="nav-link"
				href="${contextPath}/community/review/list">후기</a></li>
		</ul>
		<div class="content">
		
		</div>
		<div class="row justify-content-md-end">
			<select id=selectBox class="form-control">
				<option value="제목">제목</option>
				<option value="작성자">작성자</option>
			</select> 
			<input type="hidden" name="category" value="">
			<label for="search" class="sr-only">Search</label>
	   		<input type="text" class="form-control" name="searchResult" id="search" placeholder="search">
	   		<button class="btn btn-sm btn-success" onclick="searchPlan()">Search</button>
     		<span class="glyphicon glyphicon-search form-control-feedback"></span>
        </div>

		<div id="paging">
			<ul class="pagination justify-content-center" id="pagination">
				<!-- 이전버튼 -->
				<c:if test="${paging.startPage != 1}">
					<li class="page-item"><a href="${contextPath}/community/plan_list?nowPage=${paging.startPage -1}&cntPerPage=${paging.cntPerPage}" class="paginate_button previous" id="prev">이전</a></li>
				</c:if>
				<!-- 페이지 번호 -->
				<c:forEach var="idx" begin="${paging.startPage}" end="${paging.endPage}">
					<c:choose>
						<c:when test="${idx == paging.nowPage }">
							<li class="page-item"><a class="page-link" href="#" id="pageNo">${idx}</a></li>
						</c:when>
						<c:when test="${idx != paging.nowPage }">
							<li class="page-item"><a class="page-link" href="${contextPath}/community/plan_list?nowPage=${idx}&cntPerPage=${paging.cntPerPage}" id="pageNo">${idx}</a></li>
						</c:when>
					</c:choose>
				</c:forEach>
				<!-- 이후 -->
				<c:if test="${paging.endPage != paging.lastPage}">
					<li class="page-item"><a href="${contextPath}/community/plan_list?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}" class="paginate_button next" id="next">다음 </a></li>
				</c:if>
			</ul>
		</div>
		<div class="row justify-content-md-end">
			<input type="button" data-toggle="modal" data-target="#myModal" class="btn btn-outline-secondary" onclick="log_check('${sessionScope.memberInfo.member_id}')" value="글쓰기">
		</div>
		
		  <!-- Modal -->
  		<div class="modal fade" id="myModal" role="dialog">
    		<div class="modal-dialog">
    
     	 		<!-- Modal content-->
     		 	<div class="modal-content">
       			 	<div class="modal-header">
         				 <h4 class="modal-title">글쓰기</h4>
         			 	<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span></button>
        			</div>
        			<div class="modal-body">
          				<!-- 여기가 내용들어갈 부분 -->
          				<select class="form-control" id="myPlanList">
          					<option value="">플랜 선택하기</option>
          					<c:forEach var="myPlan" items="${myPlan}">
          						<option value="${myPlan.plan_no}">${myPlan.title}</option>
          					</c:forEach>
          				</select>
          				
        			</div>
        			<div class="modal-footer">
         				 <button type="button" class="btn btn-default" onclick="javascript:go_write()">선택완료</button>
        			</div>
      			</div>
      
    		</div>
  		</div>
  			
	</div>
</form>
</body>
</html>