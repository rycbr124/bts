<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BTS회원가입</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"/>
<link rel="stylesheet" href="/bts/resources/css/b/p001/d001.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="/bts/resources/js/b/p001/d001.js"></script>
</head>
<body>
	<div>
		<h1 style="text-align:center;">Best Travel Seoul</h1><br><br>
		<h2 style="text-align:center">회원가입</h2>
	</div>
	<br><br>
	<div class="container">
		<form class="form-horizontal" role="form" method="post" action="${contextPath }/signup/signup2">
			<div class="form-group">
				<label for="provision" class="col-lg-2 control-label">회원가입약관</label>
				<div class="col-lg-10" id="provision">
					<textarea class="form-control" rows="8" style="resize: none">약관동의
                    </textarea>
					<div class="radio">
						<label> <input type="radio" id="provisionYn" name="provisionYn" value="Y" autofocus="autofocus" checked> 동의합니다.
						</label>
					</div>
					<div class="radio">
						<label> <input type="radio" id="provisionYn" name="provisionYn" value="N"> 동의하지 않습니다.
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="memberInfo" class="col-lg-2 control-label">개인정보취급방침</label>
				<div class="col-lg-10" id="memberInfo">
					<textarea class="form-control" rows="8" style="resize: none">개인정보의 항목 및 수집방법
                    </textarea>
					<div class="radio">
						<label> <input type="radio" id="memberInfoYn" name="memberInfoYn" value="Y" checked> 동의합니다.
						</label>
					</div>
					<div class="radio">
						<label> <input type="radio" id="memberInfoYn" name="memberInfoYn" value="N"> 동의하지 않습니다.
						</label>
					</div>
				</div>
			</div>
			<div class="form-group" id="divId">
				<label for="inputId" class="col-lg-2 control-label">아이디</label>
				<div class="col-lg-10">
					<input type="text" class="form-control onlyAlphabetAndNumber" id="member_id" name="member_id" data-rule-required="true" placeholder="30자이내의 알파벳, 언더스코어(_), 숫자만 입력 가능합니다." maxlength="30">
				</div>
			</div>
			<div class="form-group" id="divPassword">
				<label for="inputPassword" class="col-lg-2 control-label">패스워드</label>
				<div class="col-lg-10">
					<input type="password" class="form-control" id="password" name="password" data-rule-required="true" placeholder="패스워드" maxlength="30">
				</div>
			</div>
			<div class="form-group" id="divPasswordCheck">
				<label for="inputPasswordCheck" class="col-lg-2 control-label">패스워드 확인</label>
				<div class="col-lg-10">
					<input type="password" class="form-control" id="passwordCheck" data-rule-required="true" placeholder="패스워드 확인" maxlength="30">
				</div>
			</div>
			<div class="form-group" id="divName">
				<label for="inputName" class="col-lg-2 control-label">이름</label>
				<div class="col-lg-10">
					<input type="text" class="form-control onlyHangul" id="name" name="name" data-rule-required="true" placeholder="한글만 입력 가능합니다." maxlength="15">
				</div>
			</div>

			<div class="form-group" id="divNickname">
				<label for="inputNickname" class="col-lg-2 control-label">닉네임</label>
				<div class="col-lg-10">
					<input type="text" class="form-control" id="nick_name" name="nick_name" data-rule-required="true" placeholder="별명" maxlength="15">
				</div>
			</div>

			<div class="form-group" id="divEmail">
				<label for="inputEmail" class="col-lg-2 control-label">이메일</label>
				<div class="col-lg-10">
					<input type="email" class="form-control" id="email" name="email" data-rule-required="true" placeholder="이메일" maxlength="40">
				</div>
			</div>
			<div class="form-group" id="divPhoneNumber">
				<label for="inputPhoneNumber" class="col-lg-2 control-label">휴대폰 번호</label>
				<div class="col-lg-10">
					<input type="tel" class="form-control onlyNumber" id="tel_no" name="tel_no" data-rule-required="true" placeholder="-를 제외하고 숫자만 입력하세요." maxlength="11">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPhoneNumber" class="col-lg-2 control-label">성별</label>
				<div class="col-lg-10">
					<select class="form-control" id="gender" name="gender">
						<option value="M">남</option>
						<option value="F">여</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="inputBirthDay" class="col-lg-2 control-label">생년월일</label>
				<div class="col-lg-10">
					<input type="text" id="birth"  name="birth" placeholder="YYYY-MM-DD 로 입력하세요">
				</div>
			</div>
			<div class="form-group">
				<label for="inputEmailReceiveYn" class="col-lg-2 control-label">이메일 수신여부</label>
				<div class="col-lg-10">
					<label class="radio-inline"> <input type="radio" id="emailReceiveYn" name="emailReceiveYn" value="Y" checked> 동의합니다.
					</label> <label class="radio-inline"> <input type="radio" id="emailReceiveYn" name="emailReceiveYn" value="N"> 동의하지 않습니다.
					</label>
				</div>
			</div>
			<div class="form-group">
				<label for="inputPhoneNumber" class="col-lg-2 control-label">SMS 수신여부</label>
				<div class="col-lg-10">
					<label class="radio-inline"> <input type="radio" id="smsReceiveYn" name="smsReceiveYn" value="Y" checked> 동의합니다.
					</label> <label class="radio-inline"> <input type="radio" id="smsReceiveYn" name="smsReceiveYn" value="N"> 동의하지 않습니다.
					</label>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<button type="submit" class="btn btn-default" id="signupBtn">가입하기</button>
				</div>
			</div>
		</form>
	</div>
	<br><br><br>
</body>
</html>