<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<style>
	@font-face{
		src:url("/bts/resources/fonts/Nanum/NANUMBARUNGOTHICBOLD.TTF");
		font-family:"nanumB";	
	}

	@font-face{
		src:url("/bts/resources/fonts/Binggrae-Bold.ttf");
		font-family:"nanum";	
	}

#title *{
	text-align:center;
}

#title>div{
	border-bottom:2px solid #9c9c9c;
}

#review-header{
	width: 100%;
    height: 400px;
}

#thumbnail-image{
	position:relative;
	display:inline-block;
    width: 100%;
    height: 400px;
    left: 50%;
    transform: translate(-50%,0);
    object-fit : cover;
}

#header-info{
	position: relative;
    width: 100%;
    height: 200px;
    background-color: rgba(130,130,130,0);
    background-image: linear-gradient(rgba(100%,100%,100%,0), rgba(100,100,100));
    color: #fff;
    bottom: 200px;
    padding: 20px;
}

#header-info *{

}

#title{
    width: auto;
    margin: 0;
    font-family:nanumB;
    font-size:3em
}

#profile-image{
    display: inline-block;
	width:32px;
	height:32px;
	border-radius:50%;
	object-fit : cover;
	margin-right:10px;
}

#member-id{
	display: inline-block;
    font-size: 20px;
    margin: auto 0;
}

#register-date{
	margin:0;
	width: 200px;
}

#review-modify{
    display: block;
    width: 100px;
    height: 40px;
    font-size: 15px;
    margin-top:10px;
    cursor: pointer;
}

#review-detail{
	background-color:#F8F8FA;
	padding : 50px;
	/*
	padding:50px 50px 10px 50px;
	*/
}

#contents img{
	max-width:80%;
	max-height : 500px;
}

#contents{
	overflow:hidden;
	height:auto;
}

#tags{
	margin : 10px 0;
	padding : 15px 10px;
	border-top:1px solid #cfcfcf;
}

/*tag*/
.tag {
  background: #e3e3e3;
  border-radius: 3px 0 0 3px;
  color: #999;
  display: inline-block;
  height: 26px;
  line-height: 26px;
  padding: 0 20px 0 23px;
  position: relative;
  margin: 0;
  margin-right: 5px;
  text-decoration: none;
  -webkit-transition: color 0.2s;
}

.tag::before {
  background: #fff;
  border-radius: 10px;
  box-shadow: inset 0 1px rgba(0, 0, 0, 0.25);
  content: '';
  height: 6px;
  left: 10px;
  position: absolute;
  width: 6px;
  top: 10px;
}

.tag::after {
  background: #F8F8FA;
  border-bottom: 13px solid transparent;
  border-left: 10px solid #e3e3e3;
  border-top: 13px solid transparent;
  content: '';
  position: absolute;
  right: 0;
  top: 0;
}

.tag:hover {
  background-color: #50bcdf;
  color: white;
}

.tag:hover::after {
   border-left-color: #50bcdf; 
}

#contents-info{
	display:flex;
	justify-content : space-between;
	margin-bottom : 10px;
}

.fa-comment-dots{
	color:rgba(170,170,170);
	margin-right:5px;
}

#comments{
	margin-top:10px;
	background-color:#f7f7f7;	
}

#comment-form{
	width:50%;
	margin: auto 0;
}

#comment-form textarea{
 	background-color: #fcfcfc;
    border: none;
    border-radius: 4px;
    box-shadow: 0 1px 1px rgba(0, 0, 0, .30);
    color: #555f77;
    font-size: 14px;
    padding: 5px 10px;
    outline: none;
    width: 100%;
    resize:none;
    margin-bottom:10px;
}

img.comment-image{
	border: 2px solid #fff;
    border-radius: 50%;
    box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
    height: 80px;
    left: 0;
    overflow: hidden;
    width: 80px;
}

</style>
<script>
	$(document).ready(function(){
		var date = '${result.register_date}';
		date = date.substr(0,date.lastIndexOf('.'));
		$('#register-date').text(date);
	})
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
${result.article_no}
<br>
${result.title}
<br>
${result.contents}
<br>
${result.register_date}
<br>
${result.member_id}
 -->
	<div class="container">
		<div id="review-header">
			<c:choose>
				<c:when test="${result.thumbnail_img==null}">
					<img id="thumbnail-image" src="${contextPath}/resources/image/no_img.jpg">
				</c:when>
				<c:otherwise>
					<img id="thumbnail-image" src="${contextPath}${result.thumbnail_img}" alt="">
				</c:otherwise>
			</c:choose>
			<div id="header-info">
				<h1 id="title">${result.title}</h1>
				<img id="profile-image" src="${contextPath}/resources/image/no_img.jpg">
				<h3 id="member-id">${result.member_id}</h3>
				<p id="register-date"></p>
				<input type="button" id="review-modify" class="btn btn-outline-light" value="수정">
			</div>
		</div>
		<!-- 
		 -->
		<div id="review-detail">
			<div id="contents">${result.contents}</div>
			<div id="tags">
				<c:forEach var="tag_name" items="${result.tag_list}">
					<span class='tag'>${tag_name}</span>
				</c:forEach>
			</div>
			<div id='contents-info'>
				<span id="comment-count"><i class="far fa-comment-dots fa-2x"></i>3</span><span id="view-count"></span>
			</div>
			
			<div id="comment-form" class="mx-auto">
				<form name="frmCom" class="row justify-content-md-end">
					<textarea name="input-comment"></textarea>
					<input type="submit" class="btn btn-outline-secondary btn-sm" value="작성하기">
					<input type="hidden" value="${result.article_no}">
				</form>
			</div>
			<div id="comments">
				<img class="comment-image" src="${contextPath}${result.thumbnail_img}">
				<div></div>			
			</div>
		</div>
	</div>
</body>
</html>