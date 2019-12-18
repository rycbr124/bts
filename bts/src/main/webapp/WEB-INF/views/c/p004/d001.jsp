<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style>
@font-face {
	src: url("/bts/resources/fonts/Nanum/NanumSquareRoundEB.ttf");
	font-family: "NanumSquareRoundEB";
}

@font-face {
	src: url("/bts/resources/fonts/Nanum/NanumSquareRoundR.ttf");
	font-family: "NanumSquareRoundR";
}

table {
	text-align: center;
	width: 100%;
}

#body th,td,h2,p {
	font-family: "NanumSquareRoundEB";
}
#body{
	width:85%;
}
.question {
	position: relative;
	height: auto;
	width: 1000px;
	min-height:500px;
	margin-top:80px;
	margin-left:120px;
}

.nav-link{
	font-size:13px;
	font-weight:bold;
}
.question_table th{
font-size: 13px;
background-color: lightgray;
height:40px;
border-top:2px solid gray;
}
#question_body td{
font-size:13px;
border-right:1px solid lightgray;
border-bottom:1px solid lightgray;
padding:10px;
}
#footer{
	margin-top:50px;
}

</style>
<script>
$(function(){
	$('.question_info').prop('style','background-color:#e8f0fe; border-radius:0 9px 9px 0;');
	$('.question_info span').prop('style','color:rgb(25,103,210);');
	$('#title a').on('click', function(){
		var contact_tag = $(this).parent().prev();
		var contact_no = $(contact_tag).text();
		$('#title a').attr('href','/bts/question/question_detail?contact_no='+contact_no);
	});
});
</script>
</head>
<body>
	<div class="question">
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link active" href="/bts/question/questionMain">문의 내역</a></li>
			<li class="nav-item"><a class="nav-link" href="/bts/question/question_write">문의하기</a></li>
		</ul>
		
	<table class="question_table">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>날짜</th>
				<th style="font-size:10px;">답변 여부</th>
			</tr>
		</thead>
		<c:forEach var="question" items="${questionList}">
			<tbody>
			<tr id="question_body">
				<td style="width:50px;">${question.contact_no}</td>
				<td id="title"><a href="#">${question.title}</a></td>
				<td style="width:100px;">${question.member_id}</td>
				<td style="width:100px;">${question.contact_date}</td>
				<td style="width:50px;">${question.answer_at}</td>
			</tr>
			</tbody>
		</c:forEach>
	</table>	
	</div>
		<div id="paging">
			<ul class="pagination justify-content-center" id="pagination">
				<!-- 이전버튼 -->
				<c:if test="${paging.startPage != 1}">
					<li class="page-item"><a href="${contextPath}/question/questionMain?nowPage=${paging.startPage -1}&cntPerPage=${paging.cntPerPage}" class="paginate_button previous" id="prev">이전</a></li>
				</c:if>
				<!-- 페이지 번호 -->
				<c:forEach var="idx" begin="${paging.startPage}" end="${paging.endPage}">
					<c:choose>
						<c:when test="${idx == paging.nowPage }">
							<li class="page-item"><a class="page-link" href="#" id="pageNo">${idx}</a></li>
						</c:when>
						<c:when test="${idx != paging.nowPage }">
							<li class="page-item"><a class="page-link" href="${contextPath}/question/questionMain?nowPage=${idx}&cntPerPage=${paging.cntPerPage}" id="pageNo">${idx}</a></li>
						</c:when>
					</c:choose>
				</c:forEach>
				<!-- 이후 -->
				<c:if test="${paging.endPage != paging.lastPage}">
					<li class="page-item"><a href="${contextPath}/question/questionMain?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}" class="paginate_button next" id="next">다음 </a></li>
				</c:if>
			</ul>
		</div>


</body>
</html>