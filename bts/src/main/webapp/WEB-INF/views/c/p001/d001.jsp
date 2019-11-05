<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 메인</title>
<link rel="stylesheet" href="${contextPath}/resources/css/mypage/d001.css">
<link rel="stylesheet" href="/bts/resources/library/bootstrap/css/bootstrap.min.css" />
</head>
<body>
<!--<div id = "container">
<img src ="http://www.artinsight.co.kr/data/tmp/1703/96103d28d38e5fb86a31b713cde7626c_sRZKrRWIDpl7sJnjq5rz2yR.jpg?s=1400x467">
</div>-->
<div id="content">
    <div class="wrapper-page">
</div>
        <div class="package">
            <div class="row">
					<div class="col-md-9 sub-container">
                    <h3 class="space-5">여행자 정보 등록</h3>
                    <form id="frm-profile" autocomplete="off"  method="post" action="${contextPath }/my/update">
                        <input type="hidden" name="email_id" id="email_id">
                        <input type="hidden" name="email_host" id="email_host">
                        <div class="mypage-picture row">
                            <img src="https://d2mgzmtdeipcjp.cloudfront.net/files/member/profile.png" class="user-picture">
                            <input type="file" data-toggle="modal" data-target="#modal-set-profile-img" class="btn btn-sm btn-default" value="사진 변경" />
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label class="title">이름</label>
                                <input type="text" value="${sessionScope.memberInfo.name}" id="name" name="name" placeholder="name" class="form-control" onlychar="true" onkeypress="specialCharNotPress();" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label class="title">닉네임</label>
                                <input type="text" id="nick_name" name="nick_name" placeholder="닉네임" class="form-control" engonly="true" required>
                            </div>
                            </div>
                               <div class="row">
                            <div class="col-md-6">
                                <label class="title">비밀번호</label>
                                <input type="text" id="password" name="password" placeholder="password" class="form-control" onlychar="true" onkeypress="specialCharNotPress();" required>
                            </div>
                        </div>
                             <div class="row">
                            <div class="col-md-6">
                                <label class="title">성별</label>
                                <!--<input type="text" id="gender" name="gender" value="" class="form-control" engonly="true" required="">-->
                                <select class="form-control" id="gender" name="gender">
									<option value="M">남</option>
									<option value="F">여</option>
								</select>
							</div>
                        </div>
                         <div class="row">
                            <div class="col-md-6">
                                <label class="title">생년월일</label>
                                <input type="text" id="birth" name="birth" placeholder="YYYY-MM-DD" class="form-control" engonly="true" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label class="title">이메일</label>
                                <input type="email" id="email" name="email" placeholder="email" class="form-control" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6">
                                <label class="title">휴대전화번호</label>
                                <input type="tel" id="tel_no" name="tel_no" placeholder="tel" class="form-control" required onkeypress="onlyNumOnKeyPress();" numberonly="true">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-2">
                                <button type="submit" class="btn btn-form-submit form-control" style="margin-bottom: 40px; background-color:#87ceeb;">저장</button>
                            </div>
                        </div>
                    </form>
                    <div class="row">
                        <div class="col-md-6" style="display:flex;">
                            <div style="margin:auto;">
                                <div data-toggle="modal" data-target="#modal-out-agreement" class="btn-out" style="cursor: pointer; color:lightgrey; text-decoration: underline;">회원 탈퇴</div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
        </div>
    </div>
</body>
</html>