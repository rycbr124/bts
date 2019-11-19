<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextPath}/resources/css/plan_detail/plan_detail.css">
<title>Insert title here</title>
<script>
$(document).ready(function(){
	var plan_root = ${root};
	planRoot(plan_root);
});
function planRoot(plan_root){
	var plan_detail = plan_root.plan_root;
	var resultArray;
	var array = new Array;
	for(var key in plan_detail){
		var detail = plan_detail[key];
		var dayNo = detail['DAY_NO'];
		
		
		var serviceKey = 'lUN5B8XHOdyoYlgxfJqeeTMdZZWYbuV9qc80jLPpilJ%2BYukKsP1%2FvR6W2AJ9UxbCgbUlkVqiN5O3%2FWiHMOyvcw%3D%3D'
		var contentid = detail['CONTENT_ID'];
		var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey='
				   + serviceKey + '&contentId='+ contentid +'&defaultYN=Y&firstImageYN=Y&overviewYN=Y&mapinfoYN=Y&MobileOS=ETC&MobileApp=AppTest';
		$.ajax({
			async:false,
			url: reqUrl,
			dataType: 'json',
			success: function(data, textStatus){
				resultArray = data.response.body.items.item;
				array.push(resultArray.firstimage);
				$('.body > .thumbnail_image').attr('src', array[0]);
				
				
			}
		});	
	}

}
</script>
</head>
<body>
	<div class="body">
		<img class="thumbnail_image" style="position:relative; display:inline-block;">
	</div>
</body>
</html>