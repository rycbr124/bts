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
<script>
	function selChange() {
		var sel = document.getElementById('cntPerPage').value;
		location.href = "${contextPath}/accompany/accMain?nowPage=${paging.nowPage}&cntPerPage="
				+ sel;
	}
</script>
<style>
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
    @keyframes blink {
      from {color: white;}
      30% {color: yellow;}
      to {color: red; font-weight: bold;}
      /* 0% {color:white;}
      30% {color: yellow;}
      100% {color:red; font-weight: bold;} */
    }
</style>
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/e/p001/accompanyMain.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>동행 찾기</title>
<!-- Bootstrap core CSS -->
</head>
<body>
	<div class="container" id="container">
		<img src="${contextPath }/resources/image/accompanyMain.jpg" id="accMainImage"> <br> <br>
		<div id="headsubject">
			<h1 id="subjectText">동행 찾기</h1>
		</div>
		<form id="boardForm" name="boardForm" method="post">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>아이디</th>
						<th>날짜</th>
						<th>조회수</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${accList}" varStatus="status">
						<tr>
							<td><c:out value="${result.article_no}" /></td>
							<td><a href="${contextPath}/accompany/accView?article_no=${result.article_no}" id="acc_title"><c:out value="${result.acc_title}" /></a> 
							<c:if test="${result.viewcnt >= 30}">
									<span class="hit">&nbsp;&nbsp;Hit!</span>
								</c:if></td>
							<td><c:out value="${result.member_id }" /></td>
							<td><c:out value="${result.register_date }" /></td>
							<td><c:out value="${result.viewcnt }" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div>
				<a href="${contextPath}/accompany/accWrite" class="btn btn-success" id="writeBtn">글작성</a>
			</div>
		</form>
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
	</div>
</body>
</html>