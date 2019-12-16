<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
       isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<style></style>
<meta charset="UTF-8">
<link rel="stylesheet"  href="${contextPath}/resources/css/plan/plan.css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/plan/plan.js"></script>
<script type="text/javascript" src="${contextPath}/resources/js/plan/plan_modify.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6b2f7da39af5c9b3e7839e09fedbc28a"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
<title>Best Travel Seoul[플랜 작성]</title>
<script>
$(document).ready(function(){
 var planner_info = ${plannerInfo};
 var detail_info = ${detailInfo};
 var tagList = ${tag_list};
 plan_modify(planner_info,detail_info, tagList);
});
</script>

</head>
<body>
<form name="plan" modelAttribute="planWrite" onsubmit="return false">
<div class="map_controller" id="map_controller">
 <div class="select_date" id="select_date">
   <div class="plan_tab" id="plan_tab">
      <a href="${contextPath}/main/main"><img src="${contextPath}/resources/image/logo/흰색/logo_white_all.png" class="logo" alt="홈화면이동"></a>
      <div class="select_date" id="select_date">
          <div class="title_write">
         	<input type="text" class="title" id="title_val" name="title"  placeholder="제목을 입력해주세요"/>
         	
         	<div class="tag_write">
         	<input type="text" name="tag" class="tag" placeholder="태그를 입력해주세요." onkeydown="enter_check();"/>
         	<div class="tag_value" ></div>
         	<input type="hidden" name="tag_value" value=""/>
         	</div>
         	<select class="personnel" style="height:30px; margin-top:10px; margin-bottom:10px;">
         		<option value=''>타입선택</option>
         		<option value='나홀로 여행'>나홀로 여행</option>
         		<option value='친구와 함께'>친구와 함께</option>
         		<option value='커플/신혼'>커플/신혼 여행</option>
         		<option value='가족 여행'>가족 여행</option>
         	</select>
         	<input type="hidden"name="personnel" value=""/>
         </div>
         <div class="calendar" id="calendar" style="margin-top:0;">
            <img src="${contextPath}/resources/image/icon/calendar.png">
            <input type="text" name="daterange"class="daterange" size="20" placeholder="날짜를 입력해주세요."/>
         </div><!-- calendar -->
      </div><!-- select_date -->
   </div><!-- plan_tab -->
   
   <div class="content_container" id="content_container"></div><!-- content_container -->
   
   
   <div class="plan_list_container" id="plan_list_container">
   <input type="hidden" name="detail_information" value=""/>
      <div class="plan_list_header" id="plan_list_header">
         <h1 class="date_value">
         </h1>
         <p class="day_value">
         </p>
      </div>
   </div>
   
   </div>
</div><!-- map_controller -->






<div class="map_area" id="map_area">
 <div class="tourist" id="tourist">
   		<div class="tourist_header">서울
   		<button onclick="save_plan()" class="save_plan" style="position:absolute;right:0;width:60px;height:45px;">저장</button> 
   		<input type="hidden" name="plan_no" value=""/>
   		</div>
   		<div class="tourist_tab">
   			<div class="select_icon">
   				<a href="javascript:searchContentType(12)" id="touristDestination"><img src="${contextPath}/resources/image/icon/map.png"/></a>
   				<a href="javascript:searchContentType(39)" id="restaurant"><img src="${contextPath}/resources/image/icon/fork.png"/></a>
   				<a href="javascript:searchContentType(38)" id="shopping"><img src="${contextPath}/resources/image/icon/shopping-bag.png"/></a>
   				<a href="javascript:searchContentType(32)" id="hotel"><img src="${contextPath}/resources/image/icon/hotel.png"/></a>
   				<a href="javascript:searchContentType(14)" id="museum"><img src="${contextPath}/resources/image/icon/canvas.png"/></a>
   				<a href="javascript:selectWishList()" id="wishList"><img src="${contextPath}/resources/image/icon/paperclip.png"/></a>
   			</div>
   			<select class="select_place" id="sigungu">
   				<option value=''>지역선택</option>
   				<option value='1'>강남구</option>
   				<option value='15'>서초구</option>
   				<option value='24'>중구</option>
   				<option value='2'>강동구</option>
   				<option value='3'>강북구</option>
   				<option value='4'>강서구</option>
   				<option value='5'>관악구</option>
   				<option value='6'>광진구</option>
   				<option value='7'>구로구</option>
   				<option value='8'>금청구</option>
   				<option value='9'>노원구</option>
   				<option value='10'>도봉구</option>
   				<option value='11'>동대문구</option>
   				<option value='12'>동작구</option>
   				<option value='13'>마포구</option>
   				<option value='14'>서대문구</option>
   				<option value='16'>성동구</option>
   				<option value='17'>성북구</option>
   				<option value='18'>송파구</option>
   				<option value='19'>양천구</option>
   				<option value='20'>영등포구</option>
   				<option value='21'>용산구</option>
   				<option value='22'>은평구</option>
   				<option value='23'>종로구</option>
   				<option value='25'>중랑구</option>
   			</select>
   		
   		</div>
   		<div class="detail_list_container"></div>
 
   </div>
		<div id="map" style="width:100%;height:100vh; float:right;"></div>
</div>
</form>
</body>
</html>