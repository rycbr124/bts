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
	<p align="right">
		<input type="button" value="글쓰기" class="btn btn-default" onClick="location.href='${contextPath}/community/plan_write'">
	</p>


</div>
</body>
</html>