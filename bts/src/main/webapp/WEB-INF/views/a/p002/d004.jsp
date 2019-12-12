<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${contextPath}/resources/css/admin/style.css"> 
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style>
body *{
	box-sizing: border-box;
}
#detail{
	margin-top:10px;
}
.table th{
	border-right:1px dashed #bbbbbf;
	background-color:#ebebed;
}

td.nop{
	padding-top : 5px;
	padding-bottom : 5px;
}

.modal-backdrop {
   background-color: rgba(0,0,0,0.5);
}

div.modal-body img{
	max-width:80%;
	max-height : 500px;
}
</style>
<script>
	$(document).ready(function(){
		$('#target-article').click(function(){
			var url="${contextPath}/admin/report/list/contents/target"
			url+="?report_se=${detailInfo.report_se}";
			url+="?contents_cd=${detailInfo.contents_cd}";
			window.open(url,"_blank");
		});
	});
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="page_title">
		<span><a class="closeDepth" href="#">closeDepth</a></span> 
		<span class="title">불량회원관리 > <b>신고 목록 조회/변경</b></span>
	</div>
	<div id="detail" class="col-md-12">
		<table class="table">
			<tbody>
				<tr>
					<th>신고일</th>
					<td>${detailInfo.report_date}</td>
					<th>신고자</th>
					<td>${detailInfo.reporter_id}</td>
					<th>처리상태</th>
					<td>
						<c:if test="${detailInfo.report_at=='N'}">처리중</c:if>
						<c:if test="${detailInfo.report_at=='Y'}">처리완료</c:if>
					</td>
				</tr>
				<tr>
					<th>신고사유</th>
					<td>${detailInfo.pnish_name}</td>
					<th>신고대상</th>
					<td>${detailInfo.target_id}</td>
					<th>신고 확인</th>
					<td class="nop">
						<input type="button" id="target-article" class="btn btn-primary btn-sm" value="신고 게시글 바로가기" />
						<input type="button" data-toggle="modal" data-target="#reportModal" class="btn btn-light btn-sm" value="내용확인" />
					</td>				
				</tr>
				<tr>
					<th>제목</th>
					<td colspan="6">${detailInfo.title}</td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="6">${detailInfo.contents}</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="modal fade" id="reportModal" role="dialog">
		<div class="modal-dialog modal-xl modal-dialog-scrollable" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">신고 대상 내용</h5>
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					${detailInfo.target_contents}
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn btn-outline-secondary" data-dismiss="modal">닫기</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>