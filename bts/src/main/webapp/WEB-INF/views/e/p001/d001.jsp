<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="result" value="${param.result }" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>

<style>
@font-face {
	src: url("/bts/resources/fonts/Nanum/NanumSquareRoundEB.ttf");
	font-family: "NanumSquareRoundEB";
}

@font-face {
	src: url("/bts/resources/fonts/Nanum/NanumSquareRoundR.ttf");
	font-family: "NanumSquareRoundR";
}

@import url(https://fonts.googleapis.com/css?family=Open+Sans);

.hit {
	animation-name: blink;
	animation-duration: 1.5s;
	animation-timing-function: ease;
	animation-iteration-count: infinite;
	/* 위 속성들을 한 줄로 표기하기 */
	/* -webkit-animation: blink 1.5s ease infinite; */
}

/* 애니메이션 지점 설정하기 */
/* 익스플로러 10 이상, 최신 모던 브라우저에서 지원 */
@
keyframes blink {
	from {color: white;
}

30%
{
color




:


 


yellow




;
}
to {
	color: red;
	font-weight: bold;
}
/* 0% {color:white;}
      30% {color: yellow;}
      100% {color:red; font-weight: bold;} */
}
* {
	font-family: "NanumSquareRoundR";
}

#subjectText {
	font-family: "NanumSquareRoundEB";
}

p {
	font-family: "NanumSquareRoundR";
}
.main-board{
	background:url('${contextPath}/resources/image/accompany/accMain2.jpg');
	background-size : 100% 100%;
}
.main-info h1{
	font-size: 2.2em;
}
</style>
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/e/p001/accompanyMain.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet" href="${contextPath}/resources/css/boardHeader.css" />
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>동행 찾기</title>
<!-- Bootstrap core CSS -->
</head>
<body>
	<div class="container" id="container">
		<div class="main-board">
			<div class='main-info'>
				<div class='main-back mx-auto'>
					<h1>동행찾기</h1>
					<span class="main-detail">BTS와 함께 동행자를 찾아보세요!</span>
				</div>
			</div>
		</div>
		<div id="headsubject"></div>
		<form id="boardForm" name="boardForm" method="post">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>이동수단</th>
						<th>작성자</th>
						<th>날짜</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${accList}" varStatus="status">
						<tr>
							<td><c:out value="${result.article_no}" /></td>
							<td><a href="${contextPath}/accompany/accView?article_no=${result.article_no}&member_id=${result.member_id}" id="acc_title"><c:out value="${result.acc_title}" /></a> <c:if test="${result.viewcnt >= 30}">
									<span class="hit">&nbsp;&nbsp;Hit!</span>
								</c:if></td>
							<td><c:if test="${result.traffic eq '버스' }">
									<img id="trafficImg" src="${contextPath}/resources/image/accompany/bus.png" alt="버스">
								</c:if> <c:if test="${result.traffic eq '택시' }">
									<img id="trafficImg" src="${contextPath}/resources/image/accompany/taxi.png" alt="택시">
								</c:if> <c:if test="${result.traffic eq '자차' }">
									<img id="trafficImg" src="${contextPath}/resources/image/accompany/car.png" alt="자차">
								</c:if> <c:if test="${result.traffic eq '지하철'}">
									<img id="trafficImg" src="${contextPath}/resources/image/accompany/subway.png" alt="지하철">
								</c:if> <c:if test="${result.traffic eq '도보'}">
									<img id="trafficImg" src="${contextPath}/resources/image/accompany/walk.png" alt="도보">
								</c:if></td>
							<td><c:out value="${result.nick_name }" /></td>
							<td><c:out value="${result.register_date }" /></td>
							<td><c:out value="${result.viewcnt }" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>
				<a href="${contextPath}/accompany/accWrite" class="btn btn-success" id="writeBtn">글작성</a>
			</div>
					<br> <br>
		<div id="paging" style="display: block; text-align: center;">
			<ul class="pagination" id="pagination">
				<!-- 이전버튼 -->
				<c:if test="${paging.startPage != 1}">
					<li class="page-item"><a href="${contextPath}/accompany/accMain?nowPage=${paging.startPage -1}&cntPerPage=${paging.cntPerPage}" class="paginate_button previous" id="prev">이전</a></li>
				</c:if>
				<!-- 페이지 번호 -->
				<c:forEach var="idx" begin="${paging.startPage}" end="${paging.endPage}">
					<c:choose>
						<c:when test="${idx == paging.nowPage }">
							<li class="page-item"><a class="page-link" href="#" id="pageNo">${idx}</a></li>
						</c:when>
						<c:when test="${idx != paging.nowPage }">
							<li class="page-item"><a class="page-link" href="${contextPath}/accompany/accMain?nowPage=${idx}&cntPerPage=${paging.cntPerPage}" id="pageNo">${idx}</a></li>
						</c:when>
					</c:choose>
				</c:forEach>
				<!-- 이후 -->
				<c:if test="${paging.endPage != paging.lastPage}">
					<li class="page-item"><a href="${contextPath}/accompany/accMain?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}" class="paginate_button next" id="next">다음 </a></li>
				</c:if>
			</ul>
		</div>
		<div class="wrap">
			<div class="form-group has-feedback" id="searchFrm">
				<select id=selectBox class="form-control">
					<option >제목</option>
					<option >작성자</option>
				</select> <input type="hidden" name="category" value=""> <label for="search" class="sr-only">Search</label> 
				<input type="text" class="form-control" name="searchResult" id="search" placeholder="검색">
				<button id="searchBtn" class="btn btn-sm btn-success" >검색</button>		
			</div>
		</div>
		</form>
		<script>
$(document).ready (function(){
$('#searchBtn').on('click',function (){
	var selectCategory = $('#selectBox option:selected').val();
	console.log(selectCategory);
	var searchFrm = document.boardForm;
	searchFrm.category.value = selectCategory;
	searchFrm.action = "${contextPath}/accompany/accMain";		
});
});
</script>
	</div>
</body>
</html>