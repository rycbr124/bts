<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<link rel="stylesheet"
	href="${contextPath}/resources/css/reserv/reservMain.css" />
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<title>숙박예약</title>
<script>
	$(document).ready(function() {
		$('ul.tabs li').click(function() {
			var tab_id = $(this).attr('data-tab');
			$('ul.tabs li').removeClass('current');
			$('.tab-content').removeClass('current');

			$(this).addClass('current');
			$("#" + tab_id).addClass('current');
		})

		$('#review_title').click(function() {
			var articleNo = this.dataset.article;
			document.reviewresult.value = articleNo;
			document.action = "${contextPath}/community/review/contents";
			document.method = "post";
			document.submit();
		})
	})
</script>
</head>
<body>
	<div id="reservcontainer">
		<img src="${contextPath}/resources/image/reserv/hotelReserv.jpg"
			id="reservMainImg"><br>
		<div id="headsubject">
			<h1 id="headsubjectText">숙박예약하기</h1>
		</div>

		<ul class="tabs">
			<li class="tab-link current" data-tab="hotel">호텔</li>
			<li class="tab-link" data-tab="guest">게스트하우스</li>
			<li class="tab-link" data-tab="motel">모텔</li>
		</ul>

		<div id="hotel" class="tab-content current">
			<form id="hotelsForm" name="hotelsForm">
				<c:set var="i" value="0" />
				<c:set var="j" value="3" />
				<table class="table table-striped table-hover">
					<tbody>
						<c:forEach var="hotelResult" items="${hotelList }"
							varStatus="status">
							<c:if test="${status.count%4 eq 0 }"><br/></c:if>
							
							<div class="card" style="width: 18rem;">
								<img src="${contextPath}${hotelResult.lodging_image}"
									class="card-img-top" alt="...">
								<div class="card-body">
									<h5 class="card-title">${hotelResult.name}</h5>
									<p class="card-text">${hotelResult.address}</p>
									<a href="${contextPath}/resve/hotelView?lodging_id=${hotelResult.lodging_id}" class="btn btn-primary">예약하기</a>
								</div>
							</div>
							
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>

		<div id="guest" class="tab-content">
			<form id="guestForm" name="guestForm">
				<table class="table table-striped table-hover">
					<tbody>
						<c:forEach var="guestResult" items="${guestList }"
							varStatus="status">
							<c:if test="${status.count%4 eq 0 }"><br/></c:if>
							<div class="card" style="width: 18rem;">
								<img src="${contextPath}${guestResult.lodging_image}"
									class="card-img-top" alt="...">
								<div class="card-body">
									<h5 class="card-title">${guestResult.name}</h5>
									<p class="card-text">${guestResult.address}</p>
									<a href="${contextPath}/resve/hotelView?lodging_id=${guestResult.lodging_id}" class="btn btn-primary">예약하기</a>
									<input type="hidden" value=${guestResult.lodging_id }>
								</div>
							</div>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>

		<div id="motel" class="tab-content">
			<form id="motelsForm" name="motelsForm">
				<table class="table table-striped table-hover">
					<tbody>
						<c:forEach var="motelResult" items="${motelList }"
							varStatus="status">
							<c:if test="${status.count%4 eq 0 }"><br/></c:if>
							<div class="card" style="width: 18rem;">
								<img src="${contextPath}${motelResult.lodging_image}"
									class="card-img-top" alt="...">
								<div class="card-body">
									<h5 class="card-title">${motelResult.name}</h5>
									<p class="card-text">${motelResult.address}</p>
									<a href="${contextPath}/resve/hotelView?lodging_id=${motelResult.lodging_id}" class="btn btn-primary">예약하기</a>
									<input type="hidden" value=${motelResult.lodging_id }>
								</div>
							</div>
						</c:forEach>
					</tbody>
				</table>
			</form>
		</div>

	</div>
</body>
</html>