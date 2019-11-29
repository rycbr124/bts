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
.question_detail{
	position:relative;
	width:1000px;
	height:auto;
	border:1px solid lightgray;
	margin:0 auto;
	margin-top: 50px;
}
.question_detail .question_date{
	position:relative;
	width:50%;
	height:50px;
	border-bottom:1px solid lightgray;
	border-right:1px solid lightgray;
	margin:0;
}
.question_detail .question_date .date_title{
position:absolute;
	width:100px;
	height:50px;
	line-height:50px;
	font-size:15px;
	text-align:center;
	background-color:#01003A;
	color:#fff;
}
.question_detail .question_date .date_info{
	position:absolute;
	width:399px;
	height:50px;
	right:0;
	text-align:center;
	line-height:50px;
}
.question_detail .contact_status{
	position:absolute;
	margin-left:50%;
	width:50%;
	height:50px;
	top:0;
	border-bottom:1px solid lightgray;
}
.question_detail .contact_status .status_title{
	position:absolute;
	width:100px;
	height:50px;
	line-height:50px;
	font-size:15px;
	text-align:center;
	background-color:#01003A;
	color:#fff;
}
.question_detail .contact_status .status_info{
	position:absolute;
	width:399px;
	height:50px;
	right:0;
	text-align:center;
	line-height:50px;
}
.question_detail .question_title{
	postion:relative;
	width:100%;
	height:50px;
	border-bottom:1px solid lightgray;
}
.question_detail .question_title .title{
	position:absolute;
	width:100px;
	height:50px;
	line-height:50px;
	font-size:15px;
	text-align:center;
	background-color:#01003A;
	color:#fff;
	border-top:1px solid #fff;
	border-bottom:1px solid #fff;
}
.question_detail .question_title .title_info{
	position:absolute;
	width:899px;
	height:50px;
	right:0;
	text-align:center;
	line-height:50px;
}
.question_detail .question_contents{
	position:relative;
	width:100%;
	height:300px;
	border-bottom:1px solid lightgray;
}
.question_detail .question_contents .contents_title{
	position:absolute;
	width:100px;
	height:auto;
	line-height:300px;
	font-size:15px;
	text-align:center;
	background-color:#01003A;
	color:#fff;
	border-top:1px solid #fff;
	border-bottom:1px solid #fff;
}
.question_detail .question_contents .contents_info{
	position:relative;
	width:899px;
	height:auto;
	margin-left:100px;
	padding-left:20px;
	padding-top:20px;
}
.question_detail .answer{
	position:relative;
	width:100%;
	height:200px;
	border-top:1px solid lightgray;
}
.question_detail .answer .answer_title{
	position:absolute;
	width:100px;
	height:auto;
	line-height:200px;
	font-size:15px;
	text-align:center;
	background-color:#01003A;
	color:#fff;
	border-top:1px solid #fff;
	border-bottom:1px solid #fff;
}
.question_detail .answer .answer_info{
	position:relative;
	width:899px;
	height:auto;
	margin-left:100px;
	padding-left:20px;
	padding-top:20px;
}
#footer{
	position:relative;
	float:bottom;
	margin-top:100px;
}
</style>
</head>
<body>
	<div class="question_detail">
		<div class="question_date">
			<div class="date_title">등록일</div>
			<c:forEach var="detail" items="${question_info}">
			<div class="date_info">${detail.contact_date}</div>
			</c:forEach>
		</div>
		<div class="contact_status">
			<div class="status_title">처리 상태</div>
			<c:forEach var="detail" items="${question_info}">
			<div class="status_info">${detail.answer_at}</div>
			</c:forEach>
		</div>
		<div class="question_title">
			<div class="title">제 목 </div>
			<c:forEach var="detail" items="${question_info}">
			<div class="title_info">${detail.title}</div>
			</c:forEach>
		</div>
		<div class="question_contents">
			<div class="contents_title">내용</div>
			<c:forEach var="detail" items="${question_info}">
			<div class="contents_info">${detail.contents}</div>
			</c:forEach>
		</div>
		<c:forEach var="detail" items="${question_info}">
		<c:set var="answer_at" value="${detail.answer_at}"/>
		<c:if test="${answer_at == '처리완료'}">
		<c:set var="answer_detail" value="${answerDetail}"/>
			<div class="answer">
				<div class="answer_title">답변</div>
				<div class="answer_info">${answer_detail}</div>
			</div>
	</c:if>
	</c:forEach>
		
	</div>
</body>
</html>