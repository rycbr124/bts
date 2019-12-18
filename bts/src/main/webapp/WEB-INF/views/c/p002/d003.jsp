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
				<li class="tab-link" data-tab="reviewtab" ><a style="color:#666666;" href="${contextPath}/my/myBoardList2">동행</a></li>
				<li class="tab-link current" data-tab="plantab">플랜</li>
			</ul>

			<div id="plantab" class="tab-content current">
				<form id="planboardForm" name="planboardForm" >
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
							<c:forEach var="planresult" items="${planList }" varStatus="status">
								<tr>
									<td><c:out value="${planresult.plan_no }" /></td>
									<td><a id="acc_title" href="${contextPath}/community/plan_contents?plan_no=${planresult.plan_no}"><c:out value="${planresult.title }"></c:out></a></td>
									<td><c:out value="${planresult.member_id }" /></td>
									<td><c:out value="${planresult.register_date }" /></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</form>
				<div id="paging" style="display: block; text-align: center;">
					<ul class="pagination" id="pagination">
						<!-- 이전버튼 -->
						<c:if test="${paging3.startPage3 != 1}">
							<li class="page-item"><a href="${contextPath}/my/myBoardList3?nowPage3=${paging3.startPage3 -1}&cntPerPage3=${paging3.cntPerPage3}" class="paginate_button previous" id="prev">이전</a></li>
						</c:if>
						<!-- 페이지 번호 -->
						<c:forEach var="idx" begin="${paging3.startPage3}" end="${paging3.endPage3}">
							<c:choose>
								<c:when test="${idx == paging3.nowPage3 }">
									<li class="page-item"><a class="page-link" href="#" id="pageNo">${idx}</a></li>
								</c:when>
								<c:when test="${idx != paging3.nowPage3 }">
									<li class="page-item"><a class="page-link" href="${contextPath}/my/myBoardList3?nowPage3=${idx}&cntPerPage3=${paging3.cntPerPage3}" id="pageNo">${idx}</a></li>
								</c:when>
							</c:choose>
						</c:forEach>
						<!-- 이후 -->
						<c:if test="${paging3.endPage3 != paging3.lastPage3}">
							<li class="page-item"><a href="${contextPath}/my/myBoardList3?nowPage3=${paging3.endPage3 +1}&cntPerPage3=${paging3.cntPerPage3}" class="paginate_button next" id="next">다음 </a></li>
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