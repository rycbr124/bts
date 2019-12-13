<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="result" value="${param.result }" />
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<script>
	var imgArray = new Array();
	imgArray[0] = "${contextPath}/resources/image/accompany/duksu.jpg";
	imgArray[1] = "${contextPath}/resources/image/accompany/olympic.jpg";
	imgArray[2] = "${contextPath}/resources/image/accompany/sakura.jpg";
	imgArray[3] = "${contextPath}/resources/image/accompany/seoul.jpg";

	function showImage() {
		var imgNum = Math.round(Math.random() * 3);
		var objImg = document.getElementById("accImage");
		objImg.src = imgArray[imgNum];

	}
	function accDel(){
		alert('정말 삭제하시겠습니까?');
		location.href = "${contextPath}/accompany3/accDel?article_no=${accView.article_no}";
	}
	
	
</script>
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextPath}/resources/css/e/p001/d002.css" />
<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap/bootstrap.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/report/report.js"></script> <!-- 커스텀 js -->
<script src="${contextPath}/resources/js/c/p006/d001.js"></script><!-- 소켓 js -->
<title>동행 게시글 조회</title>
</head>
<body onload="showImage()">
	<div class="container" id="container">
		<div id="imgContainer">
			<img id="accImage">
			<div id="imgText">
				<h1 id="h4text">&nbsp;&nbsp;${accView.acc_title }</h1>
				<span id="h4text">글번호 : ${accView.article_no }</span>
				<br>
				<h4 id="h4text">작성자 : ${accView.nick_name}</h4>
				<h4 id="h4text">나이 : ${accView.age }</h4>
				<h4 id="h4text">교통수단 : ${accView.traffic}</h4>
				<h4 id="h4text">인원수 : ${accView.whlrs_no}</h4>
				<h4 id="h4text">
					태그 :
					<c:forEach var="tagList" items="${tagList}">
						<span style="color: white;">#${tagList.tag_name }&nbsp;&nbsp;</span>
					</c:forEach>
				</h4>
			</div>
		</div>
		<div id="headsubject">
			<h1 id="subjectText">동행 게시글</h1>
			<input type="hidden" value="${accView.article_no}" name="article_no">			
		</div>
		<div id="contents">
			<br>
			<h5>작성자의 성향을 보려면 아래 버튼을 클릭하세요.</h5>
			<button class="btn btn-outline-secondary" id="inclnBtn">회원 성향 보기</button>
			<div class="dropdown" id="dropdown" style="display: none;">
				<c:forEach var="inclnView" items="${inclnView}">
					<h5>${inclnView.group_desc}</h5>
					<label class="btn btn-info btn-lg" id="incln" style="background-color: #666666; border-color: #666666">${inclnView.name}</label>
					<br>
					<br>
				</c:forEach>
			</div>
			<script>
				$('#inclnBtn').click(function() {
					$('#dropdown').toggle('slow');
				});
			</script>
			<br> <br>
			<h3>게시글 내용 :</h3>
			${accView.content} <br> <br> 
			<a class="btn btn-success" id="contentReq" href="#" style="background-color: #666666; border-color: #666666">매칭신청하기</a> &nbsp;&nbsp;&nbsp; 
			<a class="btn btn-success" href="${contextPath}/accompany/accMain" style="background-color: #666666; border-color: #666666">목록으로 돌아가기</a>&nbsp;&nbsp;&nbsp; 
			<a class="btn btn-success" id="contents-report" href="#" style="background-color: #666666; border-color: #666666" data-toggle="modal">게시글 신고하기</a>&nbsp;&nbsp;&nbsp;
			<c:choose>
			<c:when test="${sessionScope.memberInfo.member_id eq member_id}">		 		
			<a class="btn btn-success" href="${contextPath }/accompany3/accUpdateForm?article_no=${accView.article_no}" style="background-color: #666666; border-color: #666666" >게시글 수정하기</a>&nbsp;&nbsp;&nbsp;
			<a class="btn btn-success" onclick="accDel();" id="accDelBtn" href="#" style="background-color: #666666; border-color: #666666" >게시글 삭제하기</a>
			</c:when>
			</c:choose>
		</div>
	</div>
	<script>
	$(document).ready(function(){
		$('#contents-report').on('click',function(){
			var reqUrl = "${contextPath}/report/article/accompany";
			var contents_cd = ${accView.article_no};
			var target_id = "${accView.member_id}";
			var popup = openReport(reqUrl, contents_cd, target_id);
		});
		$('#contentReq').on('click',function(){
			var target_id = "${accView.member_id}";
			var article_no = "${accView.article_no}";
			var alldata = {target_id:target_id, article_no:article_no};
			$.ajax({
				url : "${contextPath}/accompany3/accReq",
				type : "GET",
				datatype : "text",
				async : false,
				data : alldata,
				success : function(){
					alert("신청이 완료되었습니다.");
					
					var form = document.createElement('form');
					var hidden = document.createElement('input');
					$(hidden).attr('type','hidden');
					$(hidden).attr('name','target_id');			
					form.append(hidden);
					
					form.target_id.value=target_id;
					form.action="${contextPath}/my/message/accompany";
					form.method="post";
					$('body').append(form);
					form.submit();				
				},
				error: function(){
					alert("다시 시도해주세요.");
				}
				
			})
		})
	})
	
	</script>
	<div class="modal fade" id="popUpAccReq">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- header -->
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h3 class="modal-title">동행 신청 하기</h3>
				</div>
				<!-- body -->
				<form role="form" method="post" action="#">
						<div class="form-group" id="delInput">
							<label class="member_id"> <span>ID</span> <input type="text" class="form-control" id="member_id" name="member_id" placeholder="ID" /></label> <br> <label class="password"> <span>Password</span> 
							<input type="password" class="form-control" id="password" name="password" placeholder="Password" /></label> <br> 
							<br><a href="#" class="btn btn-success"  id="modbutton">수정</a>&nbsp;&nbsp;
							<a href="#" class="btn btn-success" id="delbutton">삭제</a>
						</div>
					</form>			
			</div>
		</div>
	</div>
	<br>
	<br>
</body>
</html>