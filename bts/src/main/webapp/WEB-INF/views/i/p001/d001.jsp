<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${contextPath}/resources/css/planner/planner.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/plan/planner.js"></script>
<title>Best Travel Seoul[플래너]</title>
<script>
$(document).ready(function(){
	var planList = ${list};
	plan_list(planList);
});
function plan_list(planList){
	var list = planList.plan_no;
	var length = planList.plan_no.length;
	for(var i=0; i<length; i++){
		var div = document.createElement('div');
		var image = document.createElement('img');
		var a = document.createElement('a');
		var tit = document.createElement('h2');
		var rangedt = document.createElement('p');
		var registerDt = document.createElement('p');
		var member = document.createElement('p');
		var personType = document.createElement('p');
		$('.my_plan').append(div);
		$(div).prop('class','plan_info'+i);
		$(div).prop('id','plan_info');
		$('.plan_info'+i).append(a);
		$(a).append(image);
		$('.plan_info'+i).append(tit);
		$('.plan_info'+i).append(rangedt);
		$(rangedt).prop('class','rangedt');
		$('.plan_info'+i).append(registerDt);
		$(registerDt).prop('class','registerDt');
		$('.plan_info'+i).append(member);
		$(member).prop('class','memberId');
		$('.plan_info'+i).append(personType);
		$(personType).prop('class','personType');
		
	}
	
		

	var resultArray;
	for(var key in list){
		var plan = list[key];
		var title = plan['TITLE'];
		var rangeDate = plan['RANGE_DATE'];
		var contentid = plan['CONTENT_ID'];
		var registerDate = plan['REGISTER_DATE'];
		var memberId = plan['MEMBER_ID'];
		var plan_no =plan['PLAN_NO'];
		var person_se = plan['PERSON_SE'];
		
		var serviceKey = 'lUN5B8XHOdyoYlgxfJqeeTMdZZWYbuV9qc80jLPpilJ%2BYukKsP1%2FvR6W2AJ9UxbCgbUlkVqiN5O3%2FWiHMOyvcw%3D%3D'
		var reqUrl = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey='
					+ serviceKey + '&contentId='+ contentid +'&defaultYN=Y&firstImageYN=Y&overviewYN=Y&mapinfoYN=Y&MobileOS=ETC&MobileApp=AppTest';
		$.ajax({
			async:false,
			url: reqUrl,
			dataType : 'json',
			success : function(data, textStatus){
				 resultArray = data.response.body.items.item;
				$('.plan_info' + key + '>a img').attr('src',resultArray.firstimage);
				$('.plan_info' + key + '>a').attr('href','/bts/plan_detail/plan_detail?plan_no='+plan_no);
				$('.plan_info' + key + '> h2').text(title);
				$('.plan_info' + key + '> p').text(rangeDate);
				$('.plan_info' + key + '> .registerDt').text(registerDate);
				$('.plan_info' + key + '> .memberId').text(memberId);
				$('.plan_info' + key + '> .personType').text(person_se);
				
			},
			error : function(data, testStatus){
				alert("잘못된 접근입니다.");
			}
		});
		
		var plan_size = $('.my_plan').children().size();
		var del = document.createElement('a');
		var img = document.createElement('img');
		for(var i=0; i<plan_size; i++){
			$('.plan_info'+i).mouseenter(function(){
				$(this).append(del);
				$(del).append(img);
				$(del).prop('class','delete_plan');
				$(del).attr('href','/bts/planner/planner?plan_no='+plan_no);
				$(img).attr('src','/bts/resources/image/icon/remove.png');
				$(img).prop('class','delete_icon');
			})
			.mouseleave(function(){
				$(del).remove();
			});
	}
	
	}
	
}

</script>
</head>
<body>
	<div class="body" id="body">
		<div class="top_content" id="top_content">
			<img src="${contextPath}/resources/image/planner/plan.jpg">
			<div class="plan_desc" id="plan_desc">
				<h1>PLANNER</h1>
				<span style="color:gray; font-size:20px;">상상속 여행을 현실로 실행하세요!</span>
				<p>여행 루트와 여행비용,시간을 계산해 <br>
				나만의 여행을 계획하세요.</p>
				<button class="plan_btn" id="plan_btn" onclick="location='${contextPath}/plan/plan'">플랜 작성</button>
			</div>
		</div> <!-- top_content -->
		
		<div class="plan_myPlan"id="plan_myPlan">
			<h1>다른 일정 보기</h1>
			<div class="my_plan">
			</div>
		</div>
	</div>
</body>
</html>