<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${contextPath}/resources/css/admin/style.css"> 
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script src="${contextPath}/resources/ibsheet/ibsheetinfo.js"></script>
<script src="${contextPath}/resources/ibsheet/ibsheet.js"></script>
<script src="${contextPath}/resources/ibsheet/ibleaders.js"></script>
<style>
table{
	margin-top:20px;
}
</style>
<script>

	
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
 -->
	<div class="page_title">
		<span><a class="closeDepth" href="#">closeDepth</a></span> 
		<span class="title">불량회원관리 > <b>신고 내역 조회/변경</b></span>
	</div>
	
<div class="col-lg-12">
<table class="table">
  <thead>
    <tr>
      <th scope="col">신고번호</th>
      <th scope="col">제목</th>
      <th scope="col">신고자</th>
      <th scope="col">신고일자</th>
      <th scope="col">처리상태</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td>Mark</td>
      <td>Otto</td>
      <td>@mdo</td>
      <td>@mdo</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>Jacob</td>
      <td>Thornton</td>
      <td>@fat</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>Larry</td>
      <td>the Bird</td>
      <td>@twitter</td>
    </tr>
  </tbody>
</table>
</div>

</body>
</html>