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
	function selChange() {
		var sel = 6;
		location.href = "${contextPath}/accompany/paging?nowPage=${paging.nowPage}&cntPerPage="
				+ sel;
	}
</script>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>동행 찾기</title>
<!-- Bootstrap core CSS -->

<!-- Custom styles for this template -->
<link href="${contextPath }/resources/css/e/p001/d001.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1 id="mainsubject">동행 찾기</h1>
		<br>
		<div class="row">

			<!-- /.col-lg-3 -->

			<div class="col-lg-9">
				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" style="width: 800px; height: 400px">
					<!-- Indicators -->
					<ol class="carousel-indicators">
						<li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
						<li data-target="#carousel-example-generic" data-slide-to="1"></li>
						<li data-target="#carousel-example-generic" data-slide-to="2"></li>
					</ol>

					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox">
						<div class="item active">
							<img class="slide" src="${contextPath }/resources/image/accompany/gyungbok.jpg" alt="..." id="crsImg1">
							<div class="carousel-caption">경복궁 야간개장!!</div>
						</div>
						<div class="item">
							<img class="slide" src="${contextPath }/resources/image/accompany/hanRiver.jpg" alt="..." id="crsImg2">
							<div class="carousel-caption">한강 투어!!</div>
						</div>
						<div class="item">
							<img class="slide" src="${contextPath }/resources/image/accompany/duksu.jpg" alt="..." id="crsImg3">
							<div class="carousel-caption">덕수궁 둘러보기</div>
						</div>
					</div>


					<!-- Controls -->
					<a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev"> <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span class="sr-only">Previous</span>
					</a> <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next"> <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span> <span class="sr-only">Next</span>
					</a>
				</div>
				<br> <br> <br> <br>
				<div class="row" align="center">

					<div class="col-lg-4 col-md-6 mb-4" id="content">
						<div class="card h-100">
							<a href="${contextPath }/member/accompany2.do"><img class="card-img-top" src="${contextPath }/resources/image/accompany/gyungbok.jpg" alt="" id="content-img1"></a>
							<div class="card-body">
								<h4 class="card-title">
									<a href="${contextPath }/member/accompany2.do" id="accTitle">경복궁 야간개장!!</a>
								</h4>
								<h5>#고궁투어 #경복궁 #야경</h5>
								<p class="card-text">야간 경복궁 같이 보고 저녁 먹으실분 찾아요~!</p>
							</div>
							<div class="card-footer">
								<input id="toggle-heart" type="checkbox" /> <label for="toggle-heart">❤</label>
							</div>
						</div>
					</div>

					<div class="col-lg-4 col-md-6 mb-4" id="content">
						<div class="card h-100">
							<a href="#"><img class="card-img-top" src="${contextPath }/resources/image/accompany/samchung.jpg" alt="" id="content-img2"></a>
							<div class="card-body">
								<h4 class="card-title">
									<a href="#" id="accTitle">서울 맛집 투어</a>
								</h4>
								<h5>#맛집 #사진 #인스타그램</h5>
								<p class="card-text">서울 여기저기 같이 맛집 투어하고 사진 찍으실분 찾아요!</p>
							</div>
							<div class="card-footer">
								<input id="toggle-heart2" type="checkbox" /> <label for="toggle-heart2">❤</label>
							</div>
						</div>
					</div>

					<div class="col-lg-4 col-md-6 mb-4" id="content">
						<div class="card h-100">
							<a href="#"><img class="card-img-top" src="${contextPath }/resources/image/accompany/coex.jpg" alt="" id="content-img3"></a>
							<div class="card-body">
								<h4 class="card-title">
									<a href="#" id="accTitle">코엑스에서 하루 보내기</a>
								</h4>
								<h5>#쇼핑 #코엑스 #아쿠아리움</h5>
								<p class="card-text">코엑스에서 같이 쇼핑하고 아쿠아리움구경해요!</p>
							</div>
							<div class="card-footer">
								<input id="toggle-heart3" type="checkbox" /> <label for="toggle-heart3">❤</label>
							</div>
						</div>
					</div>

					<div class="col-lg-4 col-md-6 mb-4" id="content">
						<div class="card h-100">
							<a href="#"><img class="card-img-top" src="${contextPath }/resources/image/accompany/olympic.jpg" alt="" id="content-img4"></a>
							<div class="card-body">
								<h4 class="card-title">
									<a href="#" id="accTitle">올림픽 공원 가기!</a>
								</h4>
								<h5>#나홀로나무 #경치 #힐링</h5>
								<p class="card-text">올림픽공원 나홀로나무 보러 같이가요!</p>
							</div>
							<div class="card-footer">
								<input id="toggle-heart4" type="checkbox" /> <label for="toggle-heart4">❤</label>
							</div>
						</div>
					</div>

					<div class="col-lg-4 col-md-6 mb-4" id="content">
						<div class="card h-100">
							<a href="#"><img class="card-img-top" src="${contextPath }/resources/image/accompany/hanRiver.jpg" alt="" id="content-img5"></a>
							<div class="card-body">
								<h4 class="card-title">
									<a href="#" id="accTitle">한강 투어!!</a>
								</h4>
								<h5>#여의도 #한강공원 #분위기</h5>
								<p class="card-text">여의도 공원에서 같이 밤도깨비 야시장 갈사람 찾아요!!</p>
							</div>
							<div class="card-footer">
								<input id="toggle-heart5" type="checkbox" /> <label for="toggle-heart5">❤</label>
							</div>
						</div>
					</div>

					<div class="col-lg-4 col-md-6 mb-4" id="content">
						<div class="card h-100">
							<a href="#"><img class="card-img-top" src="${contextPath }/resources/image/accompany/duksu.jpg" alt="" id="content-img6"></a>
							<div class="card-body">
								<h4 class="card-title">
									<a href="#" id="accTitle">덕수궁 둘러보기</a>
								</h4>
								<h5>#고궁투어 #덕수궁 #한복</h5>
								<p class="card-text">덕수궁 같이보고 근처 맛집도 같이 가요!</p>
							</div>
							<div class="card-footer">
								<input id="toggle-heart6" type="checkbox" /> <label for="toggle-heart6">❤</label>
							</div>
						</div>
					</div>

				</div>
				<!-- /.row -->

			</div>
			<!-- /.col-lg-9 -->

		</div>
		<!-- /.row -->
		<!--  <div id="pagination">
			<ul class="pagination">
				<li class="page-item"><a class="page-link" href="#">Prev</a></li>
				<li class="page-item"><a class="page-link" href="#">1</a></li>
				<li class="page-item"><a class="page-link" href="#">2</a></li>
				<li class="page-item"><a class="page-link" href="#">3</a></li>
				<li class="page-item"><a class="page-link" href="#">4</a></li>
				<li class="page-item"><a class="page-link" href="#">5</a></li>
				<li class="page-item"><a class="page-link" href="#">Next</a></li>
				<a href="${contextPath }/member/accWrite.do">
					<button type="submit" class="btn btn-default">동행 글 작성</button>
				</a>
			</ul>
		</div>-->
		<!-- paging -->
		<div id="pagination">
			<c:if test="${paging.startPage !=1 }">
				<ul class="pagination">
					<li class="page-item"><a href="${contextPath }/accompany/paging?nowPage=${paging.startPage-1}&cntPerPage=${paging.cntPerPage}">&lt;</a></li>
				</ul>
			</c:if>
		</div>
		<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
			<c:choose>
				<c:when test="${p== paging.nowPage}">
					<b>${p}</b>
				</c:when>
				<c:when test="${p != paging.nowPage }">
					<ul class="pagination">
						<li class="page-item"><a href="${contextPath}/accompany/paging?nowPage=${p}&cntPerPage=${paging.cntPerPage}">${p}</a></li>
					</ul>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
			<ul class="pagination">
				<li class="page-item"><a href="${contextPath}/accompany/paging?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}">&gt;</a></li>
			</ul>
		</c:if>
	</div>
	<!-- /.container -->
	<script src="${contextPath }/resources/library/jquery/jquery.min.js"></script>
	<script src="${contextPath }/resources/library/bootstrap/js/bootstrap.js"></script>
	<script src="${contextPath }/resources/library/bootstrap/js/bootstrap.bundle.min.js"></script>
</body>
</html>