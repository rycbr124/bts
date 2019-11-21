<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style>
#side{
	width: 224px;
	margin-right : 0;
	position : absolute;
}
#body{
	position : relative;
}
</style>
</head>
<body>
<div id = "container">
	<div id="side">
			<tiles:insertAttribute name="side" />
	</div>

	<div id="body">
			<tiles:insertAttribute name="body" />
	</div>
</div>
</body>
</html>