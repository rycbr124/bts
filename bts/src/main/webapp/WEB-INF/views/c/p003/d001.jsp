<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="${contextPath}/resources/css/mypage/myReservation.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<title>내 예약 목록</title>

<script>
$(document).ready(function(){
	$('.reserved_info').prop('style','background-color:#e8f0fe; border-radius:0 9px 9px 0;');
	$('.reserved_info span').prop('style','color:rgb(25,103,210);');
})
function cancle(resve_no){
	$.ajax({
	     type : "POST",
	     url : "${contextPath}/my/reserv/reservCancle",
	     data : {   
	        "resve_no" : resve_no
	     },
	     success : function() {
	        alert('예약이 취소되었습니다.');
	        location.reload();
	     },
	     error : function() {
	        alert('예약이 실패하였습니다.');
	     },
	     complete : function(){
	    	location.reload(); 
	     }
	  });
}

</script>

</head>
<body>
	<div class="container">
		<br>
		<br>
		<br>
		<h1 id="headsubject">내 예약 목록</h1>
		<br>
		<form id="reservForm" name="reservForm" method="post">
			<table class="table table-striperd table-hover">
				<thead>
					<tr>
						<th>예약번호</th>
						<th></th>
						<th>숙소이름</th>
						<th>객실이름</th>
						<th>예약일</th>
						<th>결제금액</th>
						<th>입실날짜</th>
						<th>퇴실날짜</th>
						<th>예약상태</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="result" items="${reservList }" varStatus="status">
						<tr>
							<td id="contents"><c:out value="${result.resve_no }" /></td>
							<td><img id="reservImg"
								src="${contextPath}${result.lodging_image }" /></td>
							<td id="contents"><a id="resName" href="#"><c:out
										value="${result.name }" /></a></td>
							<td id="contents"><c:out value="${result.room_type }" /></td>
							<td id="contents"><c:out value="${result.resve_date }" /></td>
							<td id="contents"><c:out value="${result.amount }" /></td>
							<td id="contents"><c:out value="${result.in_date }" /></td>
							<td id="contents"><c:out value="${result.out_date }" /></td>
							<c:choose>
								<c:when test="${result.cancle_at eq 'N'}">
									<td id="contents"><c:out value="예약완료" /></td>
								</c:when>
								<c:otherwise>
									<td id="contents"><c:out value="예약취소" /></td>
								</c:otherwise>
							</c:choose>
							<td><div id="btndiv"><button id="cancleBtn" onclick="cancle('${result.resve_no }')" class="btn btn-success" >예약취소</button></div></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		<br> <br>
		<div id="paging" style="display: block; text-align: center;">
			<ul class="pagination" id="pagination">
				<!-- 이전버튼 -->
				<c:if test="${paging.startPage != 1}">
					<li class="page-item"><a
						href="${contextPath}/my/reserv/reservList?nowPage=${paging.startPage -1}&cntPerPage=${paging.cntPerPage}"
						class="paginate_button previous" id="prev">이전</a></li>
				</c:if>
				<!-- 페이지 번호 -->
				<c:forEach var="idx" begin="${paging.startPage}"
					end="${paging.endPage}">
					<c:choose>
						<c:when test="${idx == paging.nowPage }">
							<li class="page-item"><a class="page-link" href="#"
								id="pageNo">${idx}</a></li>
						</c:when>
						<c:when test="${idx != paging.nowPage }">
							<li class="page-item"><a class="page-link"
								href="${contextPath}/my/reserv/reservList?nowPage=${idx}&cntPerPage=${paging.cntPerPage}"
								id="pageNo">${idx}</a></li>
						</c:when>
					</c:choose>
				</c:forEach>
				<!-- 이후 -->
				<c:if test="${paging.endPage != paging.lastPage}">
					<li class="page-item"><a
						href="${contextPath}/my/reserv/reservList?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}"
						class="paginate_button next" id="next">다음 </a></li>
				</c:if>
			</ul>
		</div>

	</div>
</body>
</html>