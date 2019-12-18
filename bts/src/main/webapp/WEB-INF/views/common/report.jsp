<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored= "false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<style>
@font-face{
	src:url("/bts/resources/fonts/Nanum/NanumSquareRoundR.ttf");
	font-family:"nanum";	
}

body {
	background: #eee !important;
	font-family:nanum;	
}

#input-box{
	margin-top:10vw;
	margin-bottom:10vw;
	background-color:white;
	padding-top:20px;
	padding-bottom:20px;
}

.custom-radio{
	padding-top:5px;
}

</style>
<script>
	$(document).ready(function(){
		$('#endWrite').click(function(){
			var params = $("#frmReport").serialize();
			$.ajax({
				type : "post", 
				async : false,
				url : "${contextPath}/report/write",
			    data: params,
			    dataType:'json',
				success : function (data,textStatus){
					if(data){
						alert('신고되었습니다.');
					}else{
						alert('문제가 발생했습니다. 잠시후 다시 시도해주세요.');							
					}
					window.close();
				},//end success
				error : function (data,textStatus){
					alert("에러가 발생했습니다.");
				}
			}); //end ajax
		});
		
		$('#cancelWrite').click(function(){
			window.close();
		});
	});
</script>
<title>신고</title>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div id="input-box" class="col-md-10 col-md-offset-2 well text-center">
				<div id="title" class="col-md-12 form-legend text-center">
					<h2>신고하기</h2>
				</div>
				<div class="col-md-12 form-column">
					<form id="frmReport" method="post">
						<div class="form-group row">
							<label class="col-md-2 col-form-label">제목</label>
							<div class="col-md-10">
								<input type="text" name="title" class="form-control" placeholder="제목을 입력하세요">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-2 col-form-label">신고대상</label>
							<div class="col-md-10">
								<input type="text" name="target_id" readonly class="form-control-plaintext" value="${reportForm.target_id}">
							</div>
						</div>
						<div class="form-group row">
							<label class="col-md-2 col-form-label">신고사유</label>
							<div class="col-md-10">
								<c:forEach var="pn" items="${pnish}">
									<div class="custom-control custom-radio custom-control-inline">
									  <input type="radio" id="${pn.pnish_cd}" name="pnish_cd" value="${pn.pnish_cd}" class="custom-control-input">
									  <label class="custom-control-label" for="${pn.pnish_cd}">${pn.name}</label>
									</div>
								</c:forEach>
							</div>
						</div>	
						<div class="form-group row">
							<label class="col-md-2 col-form-label">내용</label>
							<div class="col-md-10">
								<textarea name="contents" class="form-control"></textarea>
							</div>
						</div>
						<input type="button" id="endWrite" class="btn btn-outline-success" value="등록하기">
						<input type="reset" class="btn btn-outline-secondary" value="다시입력">
						<input type="button" id="cancelWrite" class="btn btn-outline-danger" value="취소">				  
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>