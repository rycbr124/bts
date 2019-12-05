<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap/bootstrap.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/mypage/myAccList.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>마이페이지 동행목록</title>
</head>
<body>
	<br>
	<br>
	<div class="container" id="container">
		<div id="headsubject">
			<h1 id="subjectText">내글 동행 신청자 리스트</h1>
			<form id="boardForm" name="boardForm" method="post">
				<table class="table table-striped table-hover">
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>신청자아이디</th>
							<th>날짜</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="result" items="${accList }" varStatus="status">
							<tr>
								<td><c:out value="${result.article_no }" /></td>
								<td><a id="acc_title" href="${contextPath}/accompany/accView?article_no=${result.article_no}&member_id=${result.member_id}"><c:out value="${result.acc_title }" /></a></td>
								<td><c:out value="${result.member_id }" /></td>
								<td><c:out value="${result.register_date }" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>
			<br> <br>
			<div id="paging" style="display: block; text-align: center;">
				<ul class="pagination" id="pagination">
					<!-- 이전버튼 -->
					<c:if test="${paging.startPage != 1}">
						<li class="page-item"><a href="${contextPath}/my/accompany/accList?nowPage=${paging.startPage -1}&cntPerPage=${paging.cntPerPage}" class="paginate_button previous" id="prev">이전</a></li>
					</c:if>
					<!-- 페이지 번호 -->
					<c:forEach var="idx" begin="${paging.startPage}" end="${paging.endPage}">
						<c:choose>
							<c:when test="${idx == paging.nowPage }">
								<li class="page-item"><a class="page-link" href="#" id="pageNo">${idx}</a></li>
							</c:when>
							<c:when test="${idx != paging.nowPage }">
								<li class="page-item"><a class="page-link" href="${contextPath}/my/accompany/accList?nowPage=${idx}&cntPerPage=${paging.cntPerPage}" id="pageNo">${idx}</a></li>
							</c:when>
						</c:choose>
					</c:forEach>
					<!-- 이후 -->
					<c:if test="${paging.endPage != paging.lastPage}">
						<li class="page-item"><a href="${contextPath}/my/accompany/accList?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}" class="paginate_button next" id="next">다음 </a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</body>
</html>