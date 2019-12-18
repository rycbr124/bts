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
	   $('.member_info').prop('style','background-color:#e8f0fe; border-radius:0 9px 9px 0;');
		$('.member_info span').prop('style','color:rgb(25,103,210);');
   
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
      
   
      
        $('div.check input:radio').on('click',function(){
            var id=$(this).attr('id');
            var other=$(this).siblings('input:radio');

            $('label[for='+id+']').find('img').attr('src',$(this).data('col')); 
            $('label[for='+other.attr('id')+']').find('img').attr('src',other.data('bla'));
          })   
      
      
        $('[data-toggle="popover"]').popover();
           
        $('#myModal').on('shown.bs.modal', function () {
             $('#myInput').focus()
           })
           
           
           $('#secession').click(function(){
             //패스워드 입력 확인
             if($('#pw').val() == ''){
                var pwNull = document.createElement('p');
                var message = '비밀번호를 입력해주세요.'
                $(pwNull).text(message);
                $(pwNull).prop('id','pwMessage');
                var messageCheck = $('.withdrawal_check').children('#pwMessage');
                if(messageCheck.length >= 0){
                   $('#pwMessage').remove()
                   $('#pw_area').after(pwNull);
                }else{
                $('#pw_area').after(pwNull);
                }
                $('#pw').focus();
                return;
             }else if($('#passwdCheck').val() == ''){
                var pwCheckNull = document.createElement('p');
                var message = '비밀번호 확인을 입력해주세요.'
                $(pwCheckNull).text(message);
                $(pwCheckNull).prop('id','pwCheckMessage');
                var messageCheck = $('.withdrawal_check').children('#pwCheckMessage');
                   if(messageCheck.length >= 0){
                   $('#pwCheckMessage').remove();         
                   $('#pwCheck_area').after(pwCheckNull);
                }else{
                   $('#pwCheck_area').after(pwCheckNull);
                }
                $('#passwdCheck').focus();
                return;
             }
            
             
             //입력한 패스워드가 같인지 체크
             if($('#passwdCheck').val() != $('#password').val()){
                alert("패스워드가 일치하지 않습니다.");
                $('#passwdCheck').focus();
                return;
             }
             
             //패스워드 맞는지 확인
             $.ajax({
                url: "${contextPath}/my/passCheck",
                type: "POST",
                data: $('#delFrm').serializeArray(),
                success: function(data){
                   if(data=='false'){
                      alert("패스워드가 틀렸습니다.");
                      window.location.href = '${contextPath}/my/exitMain';
                   }else{
                      //탈퇴
                      var result = confirm('정말 탈퇴 하시겠습니까?');
                      if(result==true){
                         $('#delFrm').submit();
                         alert("회원탈퇴가 완료되었습니다.");
                      }else{
                         alert("회원탈퇴가 취소되었습니다.");
                         window.location.href = '${contextPath}/my/exitMain';
                      }
                   }   
                },
                error: function(){
                   alert("서버 에러.");
                }
             });
          });      
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
   function message_remove(){
      var pwMessage = $('.withdrawal_check').children('#pwMessage');
      var pwCheckMessage = $('.withdrawal_check').children('#pwCheckMessage');
      console.log(pwCheckMessage.length);
      if(pwMessage != 'undefined'){
         $('#pwMessage').remove();
      }
      if(pwCheckMessage != 'undefined'){
         $('#pwCheckMessage').remove();
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
	border-radius:50%;
	text-align:center;
	margin: 10px;
	font-size:12px; color:#fff;
	vertical-align:middle;
	line-height:90px;
}
#pwMessage , #pwCheckMessage{
   font-size:12px;
   color:gray;
   margin-left:100px;
}


div.col-md-6{
   float : right;
   margin-right : 50px;
   margin-bottom : 20px;
}
#side .sideTitle{
	width:250px;
	height:40px;
}
</style>

<body>
   <div id="content" class="container">
      <div class="package">
         <div class="row">
            <div class="col-md-9 sub-container">
               <form id="frm-profile" autocomplete="off" method="post">
                  <input type="hidden" name="email_id" id="email_id"> 
                  <input type="hidden" name="email_host" id="email_host">
                  <div class="profile">
                  <h3 class="space-5">여행자 정보 등록</h3>
                  <hr/>
               
                  <input type="hidden" name="email_id" id="email_id">
                  <input type="hidden" name="email_host" id="email_host"> 
                  <div class="mypage-picture">

                     <c:choose>
                        <c:when test="${not empty sessionScope.memberInfo.profile_image }">
                           <c:if test="${sessionScope.memberInfo.member_type =='kakao' }">
                              <img src="${sessionScope.memberInfo.profile_image }"id="profImg">
                           </c:if>
                           <c:if test="${sessionScope.memberInfo.member_type !='kakao' }">
                              <img src="${contextPath}${sessionScope.memberInfo.profile_image }"id="profImg">
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
                        class="btn btn-sm btn-default" value="사진 올리기" id="input_img" style="display:none;"/>
            
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
                           <c:if test="${sessionScope.memberInfo.gender =='남'}">
                              <option value="남" selected>남</option>
                              <option value="여">여</option>
                           </c:if>
                           <c:if test="${sessionScope.memberInfo.gender =='여'}">
                              <option value="남">남</option>
                              <option value="여" selected>여</option>
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
                        </c:forEach>
                     </div>
                  
                  
                  <div class="col-md-2" style="position:relative; width:100%; height:34px; margin-top:50px;">
                     <button type="button" id="btn-form-submit"class="btn btn-form-submit form-control">저장</button>
                  </div>
                  
               </form>
               <button type="button" class="btn btn-primary btn-lg" id="" data-toggle="modal" data-target="#myModal" style="position:absolute;font-size:14px; right:150px; bottom:0; border-radius:4px; line-height:15px;">회원 탈퇴</button>
               <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                      <div class="modal-content">
                           <div class="modal-header">
                             <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                             <h4 class="modal-title" id="myModalLabel">회원 탈퇴</h4>
                           </div>
                           <div class="modal-body">
                              <form name="delFrm" id="delFrm" method="post" action="${contextPath}/my/exitMem">
                              <input type="hidden" name="userId" value="${sessionScope.userId}">
                              <p>*탈퇴를 원하시면 패스워드를 확인해 주세요.</p>
                              <div class="withdrawal_check">
                                 <div id="pw_area">
                                    <p>비밀번호</p>
                                    <input type="password" name="password" id="pw" onKeydown ="message_remove()"/> 
                                 </div>
                                 <div id="pwCheck_area">
                                    <p>비밀번호 확인</p>
                                    <input type="password" name="passwdCheck" id="passwdCheck" onKeydown ="message_remove()"/>
                                 </div>
                              </div>   
                           </form>
                           </div>
                           <div class="modal-footer">
                             <button type="button" id="secession" class="btn btn-primary">탈퇴하기</button>
                             <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
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