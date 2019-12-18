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
<link rel="stylesheet" href="${contextPath}/resources/css/mypage/myBoardList.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap/bootstrap.css" />
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<title>내가 쓴 글목록 조회</title>
<script>
	$('.post_list').prop('style','background-color:#e8f0fe; border-radius:0 9px 9px 0;');
	$('.post_list span').prop('style','color:rgb(25,103,210);');
</script>
</head>
<body>

	<div class="container" id="container">
		<div id="headsubject">
		<div class="headTitle">
			<img src="${contextPath}/resources/image/mypage/document.png" class="document">
			<h1 id="subjectText">내가 쓴 글 목록</h1>
		</div>
			<ul class="tabs">
				<li class="tab-link" data-tab="acctab"><a style="color:#666666;" href="${contextPath}/my/myBoardList">후기</a></li>
				<li class="tab-link current" data-tab="reviewtab">동행</li>
				<li class="tab-link" data-tab="plantab"><a style="color:#666666;" href="${contextPath}/my/myBoardList3">플랜</a></li>
			</ul>

			<div id="reviewtab" class="tab-content current">
				<form id="reviewboardForm" name="reviewboardForm" method="post">
					<table class="table table-striped table-hover">
						<thead>
							<tr>
								<th>번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>날짜</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="accresult" items="${accList }" varStatus="status">
								<tr>
									<td><c:out value="${accresult.article_no}" /></td>
									<td><a id="acc_title" href="${contextPath}/accompany/accView?article_no=${accresult.article_no}&member_id=${accresult.member_id}"><c:out value="${accresult.acc_title }"></c:out></a></td>
									<td><c:out value="${accresult.member_id }" /></td>
									<td><c:out value="${accresult.register_date }" /></td>
								</tr>
								
								<input type="hidden" value="${accresult.article_no}" name="article_no"/>							
							</c:forEach>
						</tbody>
					</table>
				</form>
				<div id="paging" style="display: block; text-align: center;">
					<ul class="pagination" id="pagination">
						<!-- 이전버튼 -->
						<c:if test="${paging2.startPage2 != 1}">
							<li class="page-item"><a href="${contextPath}/my/myBoardList2?nowPage2=${paging2.startPage2 -1}&cntPerPage2=${paging2.cntPerPage2}" class="paginate_button previous" id="prev">이전</a></li>
						</c:if>
						<!-- 페이지 번호 -->
						<c:forEach var="idx" begin="${paging2.startPage2}" end="${paging2.endPage2}">
							<c:choose>
								<c:when test="${idx == paging2.nowPage2 }">
									<li class="page-item"><a class="page-link" href="#" id="pageNo">${idx}</a></li>
								</c:when>
								<c:when test="${idx != paging2.nowPage2 }">
									<li class="page-item"><a class="page-link" href="${contextPath}/my/myBoardList2?nowPage2=${idx}&cntPerPage2=${paging2.cntPerPage2}" id="pageNo">${idx}</a></li>
								</c:when>
							</c:choose>
						</c:forEach>
						<!-- 이후 -->
						<c:if test="${paging2.endPage2 != paging2.lastPage2}">
							<li class="page-item"><a href="${contextPath}/my/myBoardList2?nowPage2=${paging2.endPage2+1}&cntPerPage2=${paging2.cntPerPage2}" class="paginate_button next" id="next">다음 </a></li>
						</c:if>
					</ul>
				</div>
			</div>
			<script>
				$(document).ready(function() {
										
					$('#review_title').click(function(){
						var articleNo = this.dataset.article;
						document.reviewresult.value=articleNo;
						document.action="${contextPath}/community/review/contents";
						document.method="post";
						document.submit();
					})
				})
			</script>
		</div>
	</div>

</body>
</html>