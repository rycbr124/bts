<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>관리자 메인</title>

<!-- Custom fonts for this template-->
<link href="${contextPath}/resources/css/admin/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="${contextPath}/resources/css/admin/sb-admin-2.min.css"
	rel="stylesheet">
<!-- 부트스트랩 -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style type="text/css">/* Chart.js */
@
keyframes chartjs-render-animation {
	from {opacity: .99
}

to {
	opacity: 1
}

}
.chartjs-render-monitor {
	animation: chartjs-render-animation 1ms
}

.chartjs-size-monitor, .chartjs-size-monitor-expand,
	.chartjs-size-monitor-shrink {
	position: absolute;
	direction: ltr;
	left: 0;
	top: 0;
	right: 0;
	bottom: 0;
	overflow: hidden;
	pointer-events: none;
	visibility: hidden;
	z-index: -1
}

.chartjs-size-monitor-expand>div {
	position: absolute;
	width: 1000000px;
	height: 1000000px;
	left: 0;
	top: 0
}

.chartjs-size-monitor-shrink>div {
	position: absolute;
	width: 200%;
	height: 200%;
	left: 0;
	top: 0
}

#page {
	display: inline-block;
}

#content {
	margin-top: 50px;
}

.nav-tabs>li {
	font-size: 13px;
	font-weight: bold;
}

.result_table th {
	font-size: 13px;
	background-color: lightgray;
	height: 20px;
	border-top: 2px solid gray;
}
</style>
</head>
<body>
	<!-- Main Content -->
	<div id="content">

		<!-- Begin Page Content -->
		<div class="container-fluid">

			<!-- Page Heading -->
			<div
				class="d-sm-flex align-items-center justify-content-between mb-4">
				<h1 class="h3 mb-0 text-gray-800">Best Travel Seoul</h1>
				<a href="#"
					class="d-none d-sm-inline-block btn btn-sm btn-primary shadow-sm"><i
					class="fas fa-download fa-sm text-white-50"></i> Generate Report</a>
			</div>

			<!-- Content Row -->
			<div class="row">

				<!-- Earnings (Monthly) Card Example -->
				<div class="col-xl-3 col-md-6 mb-4">
					<div class="card border-left-primary shadow h-100 py-2">
						<div class="card-body">
							<div class="row no-gutters align-items-center">
								<div class="col mr-2">
									<div
										class="text-xs font-weight-bold text-primary text-uppercase mb-1">현재
										총 회원수</div>
									<div class="h5 mb-0 font-weight-bold text-gray-800">${countMember}</div>
								</div>
								<div class="col-auto">
									<i class="fas fa-calendar fa-2x text-gray-300"></i>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Earnings (Monthly) Card Example -->
				<div class="col-xl-3 col-md-6 mb-4">
					<div class="card border-left-success shadow h-100 py-2">
						<div class="card-body">
							<div class="row no-gutters align-items-center">
								<div class="col mr-2">
									<div
										class="text-xs font-weight-bold text-success text-uppercase mb-1 style: j">현재
										총 문의수</div>
									<div class="h5 mb-0 font-weight-bold text-gray-800">${countContact}</div>
								</div>
								<div class="col-auto">
									<i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Pending Requests Card Example -->
				<div class="col-xl-3 col-md-6 mb-4">
					<div class="card border-left-warning shadow h-100 py-2">
						<div class="card-body">
							<div class="row no-gutters align-items-center">
								<div class="col mr-2">
									<div
										class="text-xs font-weight-bold text-warning text-uppercase mb-1">현재
										총 신고수</div>
									<div class="h5 mb-0 font-weight-bold text-gray-800">${countReport}</div>
								</div>
								<div class="col-auto">
									<i class="fas fa-comments fa-2x text-gray-300"></i>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- Earnings (Monthly) Card Example -->
				<div class="col-xl-3 col-md-6 mb-4">
					<div class="card border-left-info shadow h-100 py-2">
						<div class="card-body">
							<div class="row no-gutters align-items-center">
								<div class="col mr-2">
									<div
										class="text-xs font-weight-bold text-info text-uppercase mb-1">현재
										업무 처리율</div>
									<div class="row no-gutters align-items-center">
										<div class="col-auto">
											<div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">${countTotal}%</div>
										</div>
										<div class="col">
											<div class="progress progress-sm mr-2">
												<div class="progress-bar bg-info" role="progressbar"
													style="width: ${countTotal}%" aria-valuenow="50"
													aria-valuemin="0" aria-valuemax="100"></div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-auto">
									<i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>



			<!-- Content Row -->
			<div class="row">

				<!-- Content Column -->
				<div class="col-lg-6 mb-4">

					<!-- Project Card Example -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">게시글 현황</h6>
						</div>
						<div class="card-body">
							<h4 class="small font-weight-bold">
								커뮤니티_계획 <span class="float-right">${countWrite.plan}%</span>
							</h4>
							<div class="progress mb-4">
								<div class="progress-bar bg-danger" role="progressbar"
									style="width: ${countWrite.plan}%" aria-valuenow="20"
									aria-valuemin="0" aria-valuemax="100"></div>
							</div>
							<h4 class="small font-weight-bold">
								커뮤니티_후기<span class="float-right">${countWrite.review}%</span>
							</h4>
							<div class="progress mb-4">
								<div class="progress-bar bg-warning" role="progressbar"
									style="width: ${countWrite.review}%" aria-valuenow="40"
									aria-valuemin="0" aria-valuemax="100"></div>
							</div>
							<h4 class="small font-weight-bold">
								동행<span class="float-right">${countWrite.accompany}%</span>
							</h4>
							<div class="progress mb-4">
								<div class="progress-bar" role="progressbar"
									style="width: ${countWrite.accompany}%" aria-valuenow="60"
									aria-valuemin="0" aria-valuemax="100"></div>
							</div>
						</div>
					</div>
				</div>

				<div class="col-lg-6 mb-4">

					<!-- Illustrations -->
					<div class="card shadow mb-4">
						<div class="card-header py-3">
							<h6 class="m-0 font-weight-bold text-primary">최신 등록글</h6>
						</div>
						<div class="card-body">
							<div class="container">
								<ul class="nav nav-tabs" role="tablist">
									<li class="nav-item"><a class="nav-link active"
										data-toggle="tab" href="#planner">커뮤니티_계획</a></li>
									<li class="nav-item"><a class="nav-link" data-toggle="tab"
										href="#menu1">커뮤니티_후기</a></li>
									<li class="nav-item"><a class="nav-link" data-toggle="tab"
										href="#menu2">동행</a></li>
								</ul>
								<div class="tab-content">
									<div id="planner" class="container tab-pane active">
										<table class="table">
											<thead>
												<tr>
													<th>제목</th>
													<th>작성자</th>
												</tr>
											</thead>
											<c:forEach var="planner" items="${planner}">
											<tbody>
												<tr>
													<td><a href="${contextPath}/community/plan_contents?plan_no=${planner.plan_no}">${planner.title}</a></td>
													<td>${planner.member_id}</td>
												</tr>
											</tbody>
											</c:forEach>
										</table>
									</div>
									<div id="menu1" class="container tab-pane fade">
											<table class="table">
											<thead>
												<tr>
													<th>제목</th>
													<th>작성자</th>
												</tr>
											</thead>
											<c:forEach var="article" items="${article}">
											<tbody>
												<tr>
													<td><a href="#">${article.title}</a></td>
													<td>${article.member_id}</td>
												</tr>
											</tbody>
											</c:forEach>
										</table>
									</div>
									<div id="menu2" class="container tab-pane fade">
											<table class="table">
											<thead>
												<tr>
													<th>제목</th>
													<th>작성자</th>
												</tr>
											</thead>
											<c:forEach var="accompany" items="${accompany}">
											<tbody>
												<tr>
													<td><a href="${contextPath}/accompany/accView?article_no=${accompany.article_no}&member_id=${accompany.member_id}">${accompany.acc_title}</a></td>
													<td>${accompany.member_id}</td>
												</tr>
											</tbody>
											</c:forEach>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>


				</div>
			</div>

		</div>
		<!-- /.container-fluid -->

	</div>
	<!-- End of Main Content -->

	<!-- Footer -->
	<footer class="sticky-footer bg-white">
		<div class="container my-auto">
			<div class="copyright text-center my-auto">
				<span>Copyright © Your Website 2019</span>
			</div>
		</div>
	</footer>
	<!-- End of Footer -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Page level plugins -->
	<script src="${contextPath}/resources/js/admin/Chart.min.js"></script>

	<!-- Page level custom scripts -->
	<script src="${contextPath}/resources/js/admin/chart-area-demo.js"></script>
	<script src="${contextPath}/resources/js/admin/chart-pie-demo.js"></script>




</body>
</html>