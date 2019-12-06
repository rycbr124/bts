<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>요청하신 페이지를 찾을 수 없습니다.</h1>
<a href="${contextPath}/main/main">메인으로 돌아가기</a>
</body>
</html>