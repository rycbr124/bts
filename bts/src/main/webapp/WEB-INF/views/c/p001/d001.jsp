<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 메인</title>
<link rel="stylesheet" href="${contextPath}/resources/css/mypage/d001.css">
<script src="${contextPath }/resources/js/c/p001/c_d001.js"></script>

  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>




<script>
	
	$(document).ready(function() {
		tagInit();
		
      
		
		if("${sessionScope.memberInfo.member_type=='kakao' || sessionScope.memberInfo.member_type=='naver'}"=="true"){
			$("#input_img").on("click", function(){
				alert('프로필 이미지 등록은 일반 회원만 가능합니다.');
				return false;
			});
		}
		
		$('#btn-form-submit').click(function() {
			var profile = $('#frm-profile')[0];
			profile.action = "/bts/my/update";
			profile.submit();
		});
		
		/*
		$('div.check input:radio').on('click',function(){
            var id=$(this).attr('id');
            console.log(id);
            var col=$(this).data('col');
            $('label[for='+id+']').find('img').attr('src',col);                   
         });
		*/
		
        $('div.check input:radio').on('click',function(){
            var id=$(this).attr('id');
            var other=$(this).siblings('input:radio');

            $('label[for='+id+']').find('img').attr('src',$(this).data('col')); 
            $('label[for='+other.attr('id')+']').find('img').attr('src',other.data('bla'));
          })   
		
		$(function () {      
          $('[data-toggle="popover"]').popover();
        })
		
		
		
	});
	
	function tagInit(){
		var input = ${selected};
		console.log("input : " + input);
		var radioList = $('input[type=radio]').toArray();
		for(var i in input){
			var cd = input[i].incln_cd;
			for(var j in radioList){
				var value = $(radioList[j]).prop('value')
				if(cd==value){
					$(radioList[j]).prop('checked',true);
					
					$(radioList[j]).parent().addClass('active');
					$('label[for='+cd+']').find('img').attr('src',$(radioList[j]).data('col')); 
					break;
				}
			}
		}
	}
	

	

</script>
<style>

@font-face {
	src: url("/bts/resources/fonts/Nanum/NanumSquareRoundEB.ttf");
    font-family: "NanumSquareRoundEB";
}
@font-face {
	src: url("/bts/resources/fonts/Nanum/NanumSquareRoundB.ttf");
    font-family: "NanumSquareRoundB";
}
@font-face {
    src: url("/bts/resources/fonts/Nanum/NanumSquareRoundR.ttf");
    font-family: "NanumSquareRoundR";
}
*{
	font-family: "NanumSquareRoundR";
}
h3.space-5{
	font-family: "NanumSquareRoundEB";
	background-color : #203341;
	color : white;
	height : 40px;
	margin : 0;
	line-height:40px;
	
}

.iclnImgB{
	width : 50px;
	height : 50px;
}

.active{
	background-color : rgba( 255, 255, 255, 0 );
}

[type=radio] { 
  position: absolute;
  opacity: 0;
  width: 0;
  height: 0;
}

div.profile{
	width : 500px;
	display : inline-block;
	float : left;
	background-color : #F8F8FA;
	text-align : center;
}

div.check{
	width : 500px;
	display : inline-block;
	float : right;
	background-color : #F8F8FA;
	text-align : center;
}

div.col-md-9{
	width : auto;
}

div.custom-control{
	text-align : center;
}

form{
	width : auto;
}

#circle{
	background-color:white;
	border:1px dashed #8C8C8C;
	width:90px; height:90px;
	border-radius:75px;
	text-align:center;
	margin: 10px;
	font-size:12px; color:#fff;
	vertical-align:middle;
	line-height:90px;
}

div.col-md-6{
	float : right;
	margin-right : 50px;
	margin-bottom : 20px;
}
</style>

<body>
	<div id="content" class="container">
		<div class="package">
			<div class="row">
				<div class="col-md-9 sub-container">
					<form id="frm-profile" autocomplete="off" method="post">
					<div class="profile">
						<h3 class="space-5">여행자 정보 등록</h3>
						<hr />
					
						<input type="hidden" name="email_id" id="email_id"> <input
							type="hidden" name="email_host" id="email_host"> 
						<div class="mypage-picture">

							<c:choose>
								<c:when
									test="${not empty sessionScope.memberInfo.profile_image }">
									<c:if test="${sessionScope.memberInfo.member_type =='kakao' }">
										<img src="${sessionScope.memberInfo.profile_image }"
											id="profImg">
									</c:if>
									<c:if test="${sessionScope.memberInfo.member_type !='kakao' }">
										<img
											src="${contextPath}${sessionScope.memberInfo.profile_image }"
											id="profImg">
										<input type="hidden" name="profileImage" value=""/>
									</c:if>
								</c:when>

								<c:otherwise>
									<img
										src="${contextPath}/resources/image/mypage/profile_img.png"
										class="user-picture" id="profImg">
								</c:otherwise>
							</c:choose>

							<input type="file" data-toggle="modal" name="profile_image"
								data-target="#modal-set-profile-img"
								class="btn btn-sm btn-default" value="사진 올리기" id="input_img" />
						</div>
						<div class="row">
						<label class="title">이름</label>
							<div class="col-md-6">
								 <input type="text"
									value="${sessionScope.memberInfo.name}" id="name" name="name"
									placeholder="name" class="form-control"
									onkeypress="specialCharNotPress();" required>
							</div>
						</div>
						<div class="row">
						<label class="title">닉네임</label>
							<div class="col-md-6">
								 <input type="text"
									value="${sessionScope.memberInfo.nick_name}" id="nick_name"
									name="nick_name" placeholder="닉네임" class="form-control"
									required>
							</div>
						</div>
						<div class="row">
						<label class="title">비밀번호</label>
							<div class="col-md-6">
								 <input type="password"
									value="${sessionScope.memberInfo.password}" id="password"
									name="password" placeholder="password" class="form-control"
									onkeypress="specialCharNotPress();" required>
							</div>
						</div>
						<div class="row">
						<label class="title">성별</label>
							<div class="col-md-6">
								
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
							<label class="title">생년월일</label>
							<div class="col-md-6">
								 <input type="text"
									value="${sessionScope.memberInfo.birth}" id="birth"
									name="birth" placeholder="YYYY-MM-DD" class="form-control"
									required>
							</div>
						</div>
						<div class="row">
						<label class="title">이메일</label> 
							<div class="col-md-6">
								<input type="email"
									value="${sessionScope.memberInfo.email}" id="email"
									name="email" placeholder="email" class="form-control" required>
							</div>
						</div>
						<div class="row">
						<label class="title">휴대전화번호</label>
							<div class="col-md-6">
								 <input type="tel"
									value="${sessionScope.memberInfo.tel_no}" id="tel_no"
									name="tel_no" placeholder="tel" class="form-control" required
									onkeypress="onlyNumOnKeyPress();">
							</div>
						</div>
						</div>
						<div class="check">
							
							<h3 class="space-5">여행자 성향 등록</h3>
								<hr>
								<c:forEach var="data" items="${incln}">
									<div class="custom-control custom-radio"> 
										<font size="3.5em" color="green">${data.key}</font><br>
										<c:forEach var="data2" items="${data.value}" varStatus="status">
											
											<input type="radio" id="${data2.incln_cd}" name="${data2.group_name}" value="${data2.incln_cd}" data-col="${data2.icon_col}" data-bla="${data2.icon_bla}">
											<label class="custom-control-label" for="${data2.incln_cd}">
												<div id="circle">
													<img src="${data2.icon_bla}" class="iclnImgB" title="${data2.group_desc}" data-container="body" data-toggle="popover" data-placement="bottom" data-content="${data2.name}"  data-trigger="hover">
												</div>		
																		
											</label>
										</c:forEach>
									</div>
									<hr>
								</c:forEach>
							</div>
						
						
						<div class="col-md-2">
							<button type="button" id="btn-form-submit"
								class="btn btn-form-submit form-control"
								style="margin-bottom: 40px; background: #ec008c; /* fallback for old browsers */ background: -webkit-linear-gradient(to right, #fc6767, #ec008c); /* Chrome 10-25, Safari 5.1-6 */ background: linear-gradient(to right, #fc6767, #ec008c);">저장</button>
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