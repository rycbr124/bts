<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<!-- Custom fonts for this template-->
<link href="${contextPath}/resources/css/admin/all.min.css" rel="stylesheet"
	type="text/css">


<!-- Custom styles for this template-->
<link href="${contextPath}/resources/css/admin/sb-admin-2.min.css" rel="stylesheet">
<!-- 부트스트랩 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<style type="text/css">/* Chart.js */
body{
overflow-y:hidden;
}
@
keyframes chartjs-render-animation {
	from {opacity: .99
}

to {
	opacity: 1
}
}
#side{
	float:left;
}
#side{
float:left;
}
#accordinonSidebar{
	margin-right : 0px;
}
</style>
</head>
<body>
	<ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="${contextPath}/main/main">
        <div class="sidebar-brand-icon rotate-n-15">
          <i class="fas fa-laugh-wink"></i>
        </div>
        <div class="sidebar-brand-text mx-3">BTS Admin</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
      <li class="nav-item active">
        <a class="nav-link" href="/bts/admin/main">
          <i class="fas fa-fw fa-tachometer-alt"></i>
          <span>관리자 메인</span></a>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        MEMBER
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="true" aria-controls="collapseTwo">
          <i class="fas fa-fw fa-cog"></i>
          <span>회원관리</span>
        </a>
        <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Member:</h6>
            <a class="collapse-item" href="${contextPath}/admin/mainMember">회원 목록 조회</a>
            <a class="collapse-item" href="${contextPath}/admin/updateMember">회원 목록 상세/수정</a>
          </div>
        </div>
      </li>

      <!-- Nav Item - Utilities Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities" aria-expanded="true" aria-controls="collapseUtilities">
          <i class="fas fa-fw fa-wrench"></i>
          <span>불량회원관리</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Report:</h6>
            <a class="collapse-item" href="${contextPath}/admin/report/pnish">제재 기준 조회/변경</a>
            <a class="collapse-item" href="${contextPath}/admin/report/history">제재 내역 조회/변경</a>
            <a class="collapse-item" href="${contextPath}/admin/report/list">신고내역조회</a>
          </div>
        </div>
      </li>

      <!-- Divider -->
      <hr class="sidebar-divider">

      <!-- Heading -->
      <div class="sidebar-heading">
        OTHER
      </div>

      <!-- Nav Item - Pages Collapse Menu -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
          <i class="fas fa-fw fa-folder"></i>
          <span>문의관리</span>
        </a>
        <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Contact:</h6>
            <a class="collapse-item" href="${contextPath}/admin/question">문의 목록 조회</a>
          </div>
        </div>
      </li>

      <!-- Nav Item - Charts -->
      <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseMember" aria-expanded="true" aria-controls="#collapseMember">
          <i class="fas fa-fw fa-chart-area"></i>
          <span>성향관리</span></a>
          <div id="collapseMember" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
          <div class="bg-white py-2 collapse-inner rounded">
            <h6 class="collapse-header">Inclination:</h6>
            <a class="collapse-item" href="${contextPath}/admin/updateIncln">성향 목록 조회/추가</a>
          </div>
        </div>
      </li>

      

      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

      <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

    </ul>
    
    <!-- Bootstrap core JavaScript-->
	<script src="${contextPath}/resources/js/admin/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/admin/bootstrap.bundle.min.js"></script>
	
	<!-- Core plugin JavaScript-->
	<script src="${contextPath}/resources/js/admin/jquery.easing.min.js"></script>
	
	<!-- Custom scripts for all pages-->
	<script src="${contextPath}/resources/js/admin/sb-admin-2.min.js"></script>
	
	<!-- Page level plugins -->
	<script src="${contextPath}/resources/js/admin/Chart.min.js"></script>
	
	<!-- Page level custom scripts -->
	<script src="${contextPath}/resources/js/admin/chart-area-demo.js"></script>
	<script src="${contextPath}/resources/js/admin/chart-pie-demo.js"></script>
</body>
</html>