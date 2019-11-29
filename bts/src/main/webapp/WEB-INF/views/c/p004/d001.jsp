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

th {
	font-family: "NanumSquareRoundEB";
}

td {
	font-family: "NanumSquareRoundR";
}

h2 {
	font-family: "NanumSquareRoundEB";
}

p {
	font-family: "NanumSquareRoundR";
}
#body{
	width:85%;
}
.question {
	position: relative;
	height: 500px;
	width: 1000px;
	margin: 0 auto;
}

#side li {
	font-size: 13px;
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
</style>
<script>
$(function(){
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
			<li class="nav-item"><a class="nav-link" href="/bts/question/questionMain">문의 내역</a></li>
			<li class="nav-item"><a class="nav-link active" href="/bts/question/question_write">문의하기</a></li>
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



</body>
</html>