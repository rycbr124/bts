<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored= "false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
#report_title{
	position:relative;
	font-size:25px;
	width:100%;
	left:35%;
}
#report_container{
	position:relative;
	margin:0 auto;
	width:600px;
	height:auto;
	border:1px solid lightgray;
}

input[type=text]{
position:relative;
display:inline-block;
left:80px;
}
.report_type dd{
position:relative;
display:inline-block;
left:80px;
}
textarea{
position:relative;
display:inline-block;
left:80px;
}
#btn_report{
	position:relative;
	width:100%;
	left:53%;
}
dt{
position:absolute;
width:100px;
height:auto;
background-color:rgb(127,127,127);
color:#fff;
text-align:center;
}
</style>
<title>Insert title here</title>
</head>
<body>
 <div id="report_title">
 신고하기
 </div>

 <form name="report_form" method="post"  action="">
 <div id="report_container">
  <div class="title">
   <dl>
    <dt>제&nbsp;&nbsp;&nbsp;목</dt>
    <dd>
          <input type="text" name="subject" size="35" maxlength="100"  class="boxTF" />
    </dd>
   </dl>
  </div>

  <div class="member">
   <dl>
    <dt>작성자</dt>
    <dd>
          <input type="text" name="name" size="35" maxlength="20" class="boxTF" />
    </dd>
   </dl>
  </div>

  <div class="report_type">
   <dl>
    <dt>신고사유</dt>
    <dd>
    	<label><input type="checkbox" value=""/>욕설</label>
    	<label><input type="checkbox" value=""/>불법 광고</label>
    	<label><input type="checkbox" value=""/>음란물</label>
    	<label><input type="checkbox" value=""/>도배성 게시글</label>
    </dd>
   </dl>
  </div>

  <div id="report_content">
   <dl>
    <dt>내&nbsp;&nbsp;&nbsp;용</dt>
    <dd>
          <textarea name="content" cols="63" rows="12" class="boxTA"style="resize: none;"></textarea>
    </dd>
   </dl>
  </div>
  </div>
   <div id="btn_report">
        <input type="button" value=" 등록하기 " class="btn2" onclick=""/>
        <input type="reset" value=" 다시입력 " class="btn2" onclick=""/>
        <input type="button" value=" 작성취소 " class="btn2" onclick=""/>
 </div>
</form>
</body>
</html>