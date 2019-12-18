<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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

#body{
	width:85%;
}
.question {
	position: relative;
	height: auto;
	width: 1000px;
	margin-top:80px;
	margin-left:120px;
}

#side li {
	font-size: 13px;
}
.nav-link{
	font-size:13px;
	font-weight:bold;
}
.question .question_title{
	position:relative;
	display:block;
	font-size:20px;
	line-height: 50px;
	width:100%;
	heigth:50px;
	border-bottom:1px solid black;
	padding-left:30px;
}
.write_area{
	position:relative;
	width:100%;
	heigth:auto;
	margin:0 auto;
	font-size:15px;
	left:50%;
	transform:translate(-25%,0);
	margin-top:20px;
}
.write_area label{
	position:absolute;
	display:block;
	width:100px;;
}
input[type=text]{
position:relative;
left:100px;
}
input[type=radio]{
position:relative;
}
.write_area span{
position:relative;
left:100px;
	
}
textarea{
position:relative;
display:inline-block;
left:100px;
resize: none;
}
input[type=button]{
	position:relative;
	width:80px;
	height:30px;
	font-size:13px;
	left:50%;
	
}
</style>
<script>	
function submit(){
	var frm_question = document.question;
	var type_val = document.getElementsByName('question_type');
	var title = $('#title').val();
	var content = $('#content').val();
	for(var i=0; i<type_val.length; i++){
		if(type_val[i].checked == true){
			var type = type_val[i].value;
		}
	}
	frm_question.action ='/bts/question/add_question';
	frm_question.method = 'get';
	frm_question.title.value = title;
	frm_question.question_type.value = type;
	frm_question.content.value = content;
	frm_question.submit();
}
</script>
</head>
<body>
<div class="question">
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link" href="/bts/question/questionMain">문의 내역</a></li>
			<li class="nav-item"><a class="nav-link active" href="/bts/question/question_write">문의하기</a></li>
		</ul>
		<strong class="question_title">문의하기</strong>
		<form name="question" method="get">
		<div class="write_area">
			<label>작&nbsp;&nbsp;성&nbsp;&nbsp;자 &nbsp;&nbsp;&nbsp;&nbsp;</label>
			<input type="text" id="member_id" value='${member_id}' style="width:373px;" disabled/><br><br>
			<label>제&nbsp;&nbsp;&nbsp;&nbsp;목&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<input type="text" id="title" name="title" value="" style="width:373px;"/><br><br>
			<label>문의 유형&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<span><input type="radio" name="question_type" value="1"/>계정/탈퇴</span>&nbsp;&nbsp;
				<span><input type="radio" name="question_type" value="2"/>서비스 문의</span>&nbsp;&nbsp;
				<span><input type="radio" name="question_type" value="3"/>광고 및 제휴 문의</span>&nbsp;&nbsp;
				<span><input type="radio" name="question_type" value="4"/>기타</span>
			<br><br>
			<label>문의 내용&nbsp;&nbsp;&nbsp;&nbsp;</label>
			<textarea cols="50" rows="10" id="content" name="content" placeholder="내용을 구체적으로 작성해 주시면 더 빠르고 정확한 내용을 답변 드릴수 있습니다."></textarea>
		</div>
		</form>
		<input type="button" onclick="submit()" value="작성 완료"/>
</div>	
</body>
</html>