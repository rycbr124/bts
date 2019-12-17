<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>

<!-- 부트스트랩 -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

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

<style>

*{
	box-sizing: border-box;
}


body{


}

#wrapper{
	display:flex;
}

#contents{
    overflow-x: hidden;
    overflow-y:	hidden;
    width:100%;
}

</style>
</head>
<body>
<div id ="wrapper">
	<div id="side">
			<tiles:insertAttribute name="side" />
	</div>

	<div id="contents" class="d-flex flex-column">
		<tiles:insertAttribute name="header" />
		<tiles:insertAttribute name="body" />
	</div>
</div>
</body>
</html>