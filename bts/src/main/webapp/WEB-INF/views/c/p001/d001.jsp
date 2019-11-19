<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 메인</title>
<link rel="stylesheet"
	href="${contextPath}/resources/css/mypage/d001.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="/bts/resources/library/bootstrap/css/bootstrap.min.css" />
<script src="${contextPath }/resources/js/c/p001/c_d001.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script>
	$(document).ready(function() {

		$('#btn-form-submit').click(function() {
			var profile = $('#frm-profile')[0];
			profile.action = "${contextPath }/my/update";
			profile.submit();
			/*
			console.log(profile);
			console.log(profile.profile_image);
			 */
		});
		/*
		$('.btn-primary').click(function(){
			$('.btn-primary').removeClass('active');
			$(this).addClass('active');
			
		})
		*/
	})
</script>
<body>
	<!--<div id = "container">
<img src ="http://www.artinsight.co.kr/data/tmp/1703/96103d28d38e5fb86a31b713cde7626c_sRZKrRWIDpl7sJnjq5rz2yR.jpg?s=1400x467">
</div>-->
	<div id="content">
		<div class="wrapper-page"></div>
		<div class="package">
			<div class="row">
				<div class="col-md-9 sub-container">
					<h3 class="space-5">여행자 정보 등록</h3>
					<hr />
					<form id="frm-profile" autocomplete="off" method="post">
						<input type="hidden" name="email_id" id="email_id"> <input
							type="hidden" name="email_host" id="email_host">
						<div class="mypage-picture" action="${contextPath}/my/image">

							<c:choose>
								<c:when
									test="${not empty sessionScope.memberInfo.profile_image }">
									<c:if test="${sessionScope.memberInfo.member_type =='kakao' }">
										<img src="${sessionScope.memberInfo.profile_image }"
											id="profImg">
									</c:if>
									<c:if test="${sessionScope.memberInfo.member_type !='kakao' }">
										<img
											src="${contextPath}/${sessionScope.memberInfo.profile_image }"
											id="profImg">
									</c:if>
								</c:when>

								<c:otherwise>
									<img
										src="https://d2mgzmtdeipcjp.cloudfront.net/files/member/profile.png"
										class="user-picture" id="profImg">
								</c:otherwise>
							</c:choose>

							<input type="file" data-toggle="modal" name="profile_image"
								data-target="#modal-set-profile-img"
								class="btn btn-sm btn-default" value="사진 올리기" id="input_img" />
						</div>
						<div class="row">
							<div class="col-md-6">
								<label class="title">이름</label> <input type="text"
									value="${sessionScope.memberInfo.name}" id="name" name="name"
									placeholder="name" class="form-control"
									onkeypress="specialCharNotPress();" required>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label class="title">닉네임</label> <input type="text"
									value="${sessionScope.memberInfo.nick_name}" id="nick_name"
									name="nick_name" placeholder="닉네임" class="form-control"
									required>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label class="title">비밀번호</label> <input type="password"
									value="${sessionScope.memberInfo.password}" id="password"
									name="password" placeholder="password" class="form-control"
									onkeypress="specialCharNotPress();" required>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label class="title">성별</label>
								<!--<input type="text" id="gender" name="gender" value="" class="form-control" engonly="true" required="">-->
								<select class="form-control" id="gender" name="gender">
									<c:if test="${sessionScope.memberInfo.gender =='M'}">
										<option value="M" selected>남</option>
										<option value="F">여</option>
									</c:if>
									<c:if test="${sessionScope.memberInfo.gender =='F'}">
										<option value="M">남</option>
										<option value="F" selected>여</option>
									</c:if>
								</select>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label class="title">생년월일</label> <input type="text"
									value="${sessionScope.memberInfo.birth}" id="birth"
									name="birth" placeholder="YYYY-MM-DD" class="form-control"
									required>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label class="title">이메일</label> <input type="email"
									value="${sessionScope.memberInfo.email}" id="email"
									name="email" placeholder="email" class="form-control" required>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6">
								<label class="title">휴대전화번호</label> <input type="tel"
									value="${sessionScope.memberInfo.tel_no}" id="tel_no"
									name="tel_no" placeholder="tel" class="form-control" required
									onkeypress="onlyNumOnKeyPress();">
							</div>
						</div>
						<div class="check">
							<div >
							<label class="title">여행 성향 체크</label>
								<br><hr>
								<c:forEach var="data" items="${incln}">
									<font size="3.5em" color="green">${data.group_desc}</font><br>
									<div class="btn-group btn-group-toggle" data-toggle="buttons">
										<label class="btn btn-info btn-lg" >
											<input type="radio" name="${data.group_name}" value="탕진">여행은 탕진! 내가 만수르~!
										</label>
										<label class="btn btn-info btn-lg">
											<input type="radio" name="${data.group_name}" value="알뜰">정해진 돈만 쓰면서 아낀다 알뜰살뜰!
										</label>
									</div>
									<hr>
								</c:forEach>
								<font size="3.5em" color="green">경비</font><br>
								<div class="btn-group btn-group-toggle" data-toggle="buttons">
									<label class="btn btn-info btn-lg" >
										<input type="radio" name="security" value="탕진">여행은 탕진! 내가 만수르~!
									</label>
									<label class="btn btn-info btn-lg">
										<input type="radio" name="security" value="알뜰">정해진 돈만 쓰면서 아낀다 알뜰살뜰!
									</label>
								</div>
								<hr>
								<font size="3.5em" color="green">계획</font><br>
								<div class="btn-group btn-group-toggle" data-toggle="buttons">
									<label class="btn btn-info btn-lg">
										<input type="radio" name="plan" value="즉흥">발길 닿는대로 운명에 길찾기!
									</label>
									
									<label class="btn btn-info btn-lg">
										<input type="radio" name="plan" value="계획">계획한대로 정해서 다니기 내가 바로 이 구역에 가이드
									</label>
								</div>
								<hr>
								<font size="3.5em" color="green">음식</font><br>
								<div class="btn-group btn-group-toggle" data-toggle="buttons">
									<label class="btn btn-info btn-lg">
										<input type="radio" name="food" value="최대">유명 맛집 탐방!유명한 음식 최대한 많이 먹기
									</label>
									<label class="btn btn-info btn-lg">
										<input type="radio" name="food" value="최소">최소한으로 대표적인,익숙한 음식만 먹기
									</label>
								</div>
								<hr>
								<font size="3.5em" color="green">사람</font><br>
								<div class="btn-group btn-group-toggle" data-toggle="buttons">
									<label class="btn btn-info btn-lg">
										<input type="radio" name="friend" value="단체">여행은 많이가면 갈수록 좋지! 최대인원
									</label>
									<label class="btn btn-info btn-lg">
										<input type="radio" name="friend" value="소수">소수정예!! 소수 인원으로 추억쌓기 
									</label>
								</div>
								<hr>
								<font size="3.5em" color="green">안전</font><br>
								<div class="btn-group btn-group-toggle" data-toggle="buttons">
									<label class="btn btn-info btn-lg">
										<input type="radio" name="safety" value="안전">안전제일! 정해진 장소, 대표적인 장소 여행
									</label>
									<label class="btn btn-info btn-lg">
										<input type="radio" name="safety" value="모험">내가 바로 콜롬버스! 모르는 여행지 탐험!
									</label>
								</div>
								<hr>
								<font size="3.5em" color="green">풍경</font><br>
								<div class="btn-group btn-group-toggle" data-toggle="buttons">
									<label class="btn btn-info btn-lg">
										<input type="radio" name="scenery" value="도시">도시의 화려함,야경 그 지역에 색다른 도시 풍경
									</label>
									<label class="btn btn-info btn-lg">
										<input type="radio" name="scenery" value="자연">여행은 힐링이지 도심을 벗어나 자연속으로...
									</label>
								</div>
								<hr>
								<font size="3.5em" color="green">시간</font><br>
								<div class="btn-group btn-group-toggle" data-toggle="buttons">
									<label class="btn btn-info btn-lg">
										<input type="radio" name="time" value="낮">밤에는 다음날을 위해 휴식이지!!
									</label>
									<label class="btn btn-info btn-lg">
										<input type="radio" name="time" value="밤">여행은 철야지 자는시간도 아끼면서 놀자!!
									</label>
								</div>
								<hr>
								<font size="3.5em" color="green">스타일</font><br>
								<div class="btn-group btn-group-toggle" data-toggle="buttons">
									<label class="btn btn-info btn-lg">
										<input type="radio" name="theme" value="액티비티">여행은 많이보고 느끼고 경험해야한다!
									</label>
									<label class="btn btn-info btn-lg">
										<input type="radio" name="theme" value="힐링">여행은 곧 휴식 여유로움을 즐겨야한다!
									</label>
								</div>
								<hr>
								<!-- 
								<div class="btn-group" data-toggle="buttons" id="check">
										<label class="btn btn-primary"> 
										<input type="radio" name="options" id="option1" autocomplete="off"> Radio1 
										</label> 
										<label class="btn btn-primary"> 
										<input type="radio" name="options" id="option2" autocomplete="off"> Radio2
										</label>
								</div>
								 -->
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								<button type="button" id="btn-form-submit"
									class="btn btn-form-submit form-control"
									style="margin-bottom: 40px; background: #ec008c; /* fallback for old browsers */ background: -webkit-linear-gradient(to right, #fc6767, #ec008c); /* Chrome 10-25, Safari 5.1-6 */ background: linear-gradient(to right, #fc6767, #ec008c);">저장</button>
							</div>
						</div>
					</form>
					<div class="row">
						<div class="col-md-6" style="display: flex;">
							<div style="margin: auto;">
								<div data-toggle="modal" data-target="#modal-out-agreement"
									class="btn-out"
									style="cursor: pointer; color: lightgrey; text-decoration: underline;">
									<a href="${contextPath }/my/exitMain">회원탈퇴</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>