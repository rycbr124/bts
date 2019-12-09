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


var serviceKey = '%2B50SHKR5TLKYKGJB1vUT27tbTUYeocbkQFjQVTN8m%2FtACpIoNMLXI3Q9xkQt%2BkdRQOdUkotl2i0ioIb2nwaC8w%3D%3D'
	var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=' + serviceKey + '&contentId=' + arr_contentId[${status.index}] + '&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y';
	
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
	    
	    },
	    error : function(data, textStatus) {
	        alert("잘못된 접근입니다.")
	     }
	});




</c:forEach>
/*
var length = arr_contentId.length;

for(var i in arr_contentId){
	
	var serviceKey = '%2B50SHKR5TLKYKGJB1vUT27tbTUYeocbkQFjQVTN8m%2FtACpIoNMLXI3Q9xkQt%2BkdRQOdUkotl2i0ioIb2nwaC8w%3D%3D'
	var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey=' + serviceKey + '&contentId=' + arr_contentId[i] + '&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y';
	
	$.ajax({
	    async : false,
	    url : reqUrl,
	    dataType : 'json',
	    success : function(data, textStatus) {
	    	var resultArray = data.response.body.items.item;
	    	console.log("1222111 : " + resultArray.title);
	   
	    	var figure = document.createElement('figure');
	    	$(figure).prop('class', 'snip1321');
	    	$(figure).prop('id', 'snip' + i);
	    	
	    	var img = document.createElement('img');
	    	$(img).prop('src', resultArray.firstimage);
	    	$(img).prop('alt', 'sq-sample26');
	    	$(img).prop('id', 'image' + i);
	    	
	    	var figcaption = document.createElement('figcaption');
	    	$(figcaption).prop('class', 'fig' + i);
	    	
	    	var addr = document.createElement('h4');
	    	var addr_text = document.createTextNode(resultArray.addr1);
	    	addr.appendChild(addr_text);
	    	
	    	var title = document.createElement('h2');
	    	var title_text = document.createTextNode(resultArray.title);
	    	title.appendChild(title_text);
	    	
	    	var href = document.createElement('a');
	    	$(href).prop('href', '${contextPath}/recommend/course_detail?contentid=' + resultArray.contentid + "&contenttypeid=" + resultArray.contenttypeid);
	    		    	
	    	$('.content').append(figure);
	        $('#snip' + i).append(img);
	        $('#snip' + i).append(figcaption);
	        $('.fig' + i).append(addr);
	        $('.fig' + i).append(title);
	        $('#snip' + i).append(href);		
	    
	    },
	    error : function(data, textStatus) {
	        alert("잘못된 접근입니다.")
	     }
	});

}
*/
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

th{
	font-family: "NanumSquareRoundEB";
	
}

td{
	font-family: "NanumSquareRoundR";
		
}

h2{
	font-family: "NanumSquareRoundEB";
}
p {
	font-family: "NanumSquareRoundR";	
}

img{
	width : 1110px;
	height : 400px;
	margin-bottom : 50px;
}

li.nav-item{
	width : 250px;
}

div#paging{
	display: block; 
	text-align: center;
}

#content{
	height : 800px;
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
  width: 310px;
  max-height : 210px;
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
	max-height:210px;
  vertical-align: top;
}
figure.snip1321 figcaption {
  top: 50%;
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
  background-color: #ffffff;
  position: absolute;
  content: "";
  display: block;
  top: 20px;
  left: 20px;
  right: 20px;
  bottom: 20px;
  -webkit-transition: all 0.4s ease-in-out;
  transition: all 0.4s ease-in-out;
  -webkit-transform: rotateX(-90deg);
  transform: rotateX(-90deg);
  -webkit-transform-origin: 50% 50%;
  -ms-transform-origin: 50% 50%;
  transform-origin: 50% 50%;
  opacity: 0;
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
figure.snip1321.hover figcaption {
  -webkit-transform: translateY(-50%);
  transform: translateY(-50%);
  opacity: 1;
  -webkit-transition-delay: 0.2s;
  transition-delay: 0.2s;
}
figure.snip1321:hover:after,
figure.snip1321.hover:after {
  -webkit-transform: rotateX(0);
  transform: rotateX(0);
  opacity: 0.9;
}

</style>


</head>
<body>

<div class="container">
	<h2>커뮤니티</h2>
  <p>BTS와 함께 나만의 여행계획을 공유하세요!</p>
  <img src="${contextPath}/resources/image/community/community_main.jpg">
  <ul class="nav nav-tabs">
    <li class="nav-item">
      <a class="nav-link active" href="${contextPath}/community/plan_list">계획</a>
    </li>
    <li class="nav-item">
      <a class="nav-link" href="${contextPath}/community/review/list">후기</a>
    </li>
    
  </ul>


	
	<table class="table table-striped">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
		
		<c:forEach var="article" items="${listArticle}">
		<tr>
			<td>${article.rownum}</td>
			<td><a href='${contextPath}/community/plan_contents?plan_no=${article.plan_no}'>${article.title}</a></td>
			<td>${article.member_id}</td>
			<td>${article.register_date}</td>
			<td>${article.view_cnt}</td>
		</tr>

		</c:forEach>
	</table>
	
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
		<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal" onclick="log_check('${sessionScope.memberInfo.member_id}')">글쓰기</button>
		
		
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
  		
			
  		<div class="content">
		
		</div>
  		
</div>
</body>
</html>