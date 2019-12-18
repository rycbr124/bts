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
<script>
$(document).ready(function(){
	$('.accompany_list').prop('style','background-color:#e8f0fe; border-radius:0 9px 9px 0;');
	$('.accompany_list span').prop('style','color:rgb(25,103,210);');
})
</script>
</head>
<body>
	<br>
	<br>
	<div class="container" id="container">

		<div id="headsubject">
			<h1 id="subjectText">내 동행 리스트</h1>
		<ul class="tabs">
			<li id="tabContent" class="tab-link " data-tab="accToMe"><a href="${contextPath}/my/accompany/accList">나에게 신청한글</a></li>
			<li id="tabContent" class="tab-link current" data-tab="accToYou">내가 신청한글</li>
		</ul>
		<div id="accToYou" class="tab-content current">
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
						<c:forEach var="result2" items="${myList }" varStatus="status">
							<tr>
								<td><c:out value="${result2.article_no }" /></td>
								<td><a id="acc_title" href="${contextPath}/accompany/accView?article_no=${result2.article_no}&member_id=${result2.member_id}"><c:out value="${result2.acc_title }" /></a></td>
								<td><c:out value="${result2.member_id }" /></td>
								<td><c:out value="${result2.register_date }" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</form>

			<br> <br>
			<div id="paging" style="display: block; text-align: center;">
				<ul class="pagination" id="pagination">
					<!-- 이전버튼 -->
					<c:if test="${paging2.startPage2 != 1}">
						<li class="page-item"><a href="${contextPath}/my/accompany/accList2?nowPage2=${paging2.startPage2 -1}&cntPerPage2=${paging2.cntPerPage2}" class="paginate_button previous" id="prev">이전</a></li>
					</c:if>
					<!-- 페이지 번호 -->
					<c:forEach var="idx" begin="${paging2.startPage2}" end="${paging2.endPage2}">
						<c:choose>
							<c:when test="${idx == paging2.nowPage2 }">
								<li class="page-item"><a class="page-link" href="#" id="pageNo">${idx}</a></li>
							</c:when>
							<c:when test="${idx != paging2.nowPage2 }">
								<li class="page-item"><a class="page-link" href="${contextPath}/my/accompany/accList2?nowPage2=${idx}&cntPerPage2=${paging2.cntPerPage2}" id="pageNo">${idx}</a></li>
							</c:when>
						</c:choose>
					</c:forEach>
					<!-- 이후 -->
					<c:if test="${paging2.endPage2 != paging2.lastPage2}">
						<li class="page-item"><a href="${contextPath}/my/accompany/accList2?nowPage2=${paging2.endPage2+1}&cntPerPage2=${paging2.cntPerPage2}" class="paginate_button next" id="next">다음 </a></li>
					</c:if>
				</ul>
			</div>
		</div>
		</div>

		
	</div>
</body>
</html>