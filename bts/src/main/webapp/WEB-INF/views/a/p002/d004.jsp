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

.justify-content-md-end input{
	margin-right:30px;
}

option.selected{
	background-color: rgba(143, 164, 225,0.3);
}

</style>
<script>
	$(document).ready(function(){
		$('#target-article').click(function(){
			var url="${contextPath}/admin/report/list/contents/target"
			url+="?report_se=${detailInfo.report_se}";
			url+="&contents_cd=${detailInfo.contents_cd}";
			window.open(url,"_blank");
		});
		
		$('input:radio[name=pnish_type]').change(function(){
			radioSelected($(this).val())
		});
		
		$('#pnish_desc').change(function(){
			if($('#pnish-type1').is(':checked')){			
				$('input[name=day_cnt]').val($('#pnish_desc option:selected').data('day'));
			}
		});
		
		$('#write-result').click(function(){
			var con_test = confirm("처리하시겠습니까?");
			console
			if(con_test){
				var param = $('#frmReport').serialize();
				$.ajax({
					type : "post", 
					async : false,
					url : "${contextPath}/admin/report/list/contents/save",
					data: param,
					dataType:'json',
					success : function (data,textStatus){
						alert("처리가 완료되었습니다.");
						location.reload();
						/*
						*/
						
					},
					error : function (data,textStatus){
						alert("저장중 문제가 발생했습니다.");
					}
				});
			}
		});
		
		function radioSelected(value) {
			var cnt = $('input[name=day_cnt]');
			cnt.val('');
			switch(value) {
			case "1": 
				cnt.attr('readonly',true);
				cnt.val($('option:selected').data('day'));
				break;
			case "2": 
				cnt.attr('readonly',false);
				break;
			case "3": 
				cnt.attr('readonly',true);
				break;			
			}
		}
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
		<div class="exp_product">신고내용</div>
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
		<c:choose>
			<c:when test="${detailInfo.report_at=='N'}">
				<div class="exp_product">처리하기</div>
				<form id="frmReport">
					<table class="table">
						<tbody>
							<tr>
								<th>정지사유</th>
								<td>
									<select id="pnish_desc" class="form-control" name="pnish_desc">
										<c:forEach var="pn" items="${pnish}">
											<c:choose>
											<c:when test="${pn.pnish_cd==detailInfo.pnish_cd}">
												<option class="selected" data-day="${pn.day_cnt}" value="${pn.name}" selected>${pn.name}</option>
											</c:when>
											<c:otherwise>
												<option data-day="${pn.day_cnt}" value="${pn.name}">${pn.name}</option>
											</c:otherwise>
											</c:choose>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<th>정지기간</th>
								<td>
									<div class="form-row align-items-center">
										<div class="col-auto my-1">
											<div class="custom-control custom-radio custom-control-inline">
												<input type="radio" id="pnish-type1" name="pnish_type" value="1" class="custom-control-input">
												<label class="custom-control-label" for="pnish-type1">사유에 맞춰 지정</label>
											</div>
											<div class="custom-control custom-radio custom-control-inline">
												<input type="radio" id="pnish-type2" name="pnish_type" value="2" class="custom-control-input">
												<label class="custom-control-label" for="pnish-type2">직접 입력</label>
											</div>
											<div class="custom-control custom-radio custom-control-inline">
												<input type="radio" id="pnish-type3" name="pnish_type" value="3" class="custom-control-input">
												<label class="custom-control-label" for="pnish-type3">영구 정지</label>
											</div>										
										</div>
										<div class="input-group col-md-5">
											<input type="number" min="1" name="day_cnt" class="form-control" readonly>								
											<div class="input-group-append">
												<span class="input-group-text">일</span>
											</div>
										</div>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					<input type="hidden" name="report_no" value="${detailInfo.report_no}">
					<input type="hidden" name="member_id" value="${detailInfo.target_id}">
					<div class="row justify-content-md-end">
						<input type="button" id="write-result" class="btn btn-outline-secondary" value="처리하기">				
					</div>
				</form>
			</c:when>
			<c:otherwise>
				<div class="exp_product">처리결과</div>
				<table class="table">
					<tbody>
						<tr>
							<th></th>
							<td></td>
						</tr>
					</tbody>
				</table>
			</c:otherwise>
		</c:choose>
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