<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="${contextPath}/resources/css/c/p006/d001.css"> <!-- 커스텀 css -->
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<script src="${contextPath}/resources/js/c/p006/d001.js"></script>
<script>
$(document).ready(function(){
	$('.chat_info').prop('style','background-color:#e8f0fe; border-radius:0 9px 9px 0;');
	$('.chat_info span').prop('style','color:rgb(25,103,210);');
	init();
	
	/*
	$("#chat-header>i").on("click",addMember);
	
	function addMember(){
		$('#user-add').fadeIn();
	}
	*/
	
	function init(){
		var url="ws://"+"${pageContext.request.serverName}"+":"+${pageContext.request.serverPort}+"${pageContext.request.contextPath}"+"/msg?at=${accompany_at}";		
		var con = new socketConn(url);
		var ws=con.getWs();
		
		ws.onopen=function(message){
			$('#set-acc').css('display','none');
			if("${target_id.member_id==null}"!="true"){
				var imgSrc="${target_id.profile_image}";
				if(imgSrc==null){
					imgSrc='${contextPath}/resources/image/no_img.jpg';
				}else{
					if("${target_id.member_type}"!='kakao' && "${target_id.member_type}"!='naver'){
						imgSrc='${contextPath}'+imgSrc;
					}
				}
				var divForm = new memberDivForm('discussion',imgSrc,"${target_id.nick_name}","${target_id.member_id}");
				var resultForm = prependMember(divForm);
				memberClick(resultForm,ws);
			}
			
			if("${accompany_at=='Y'}"=="true" && "${target_id.member_id==null}"!="true"){
				var textMessage='${sessionScope.memberInfo.member_id}'+'님이 매칭을 신청하였습니다.'
				sendText(ws,"send_message",sendForm(textMessage,"${target_id.member_id}"));
			}			
		}
		ws.onclose=function(message){
			alert("end");
		}
		ws.onerror=function(message){
			console.log("error");
		}
		ws.onmessage=function(message){
			var recData = JSON.parse(message.data);
			var recMessage = recData.body.result;
			
			if(recData.header=="chat_list"){
				for(var i in recMessage){
					messageAdd(recMessage[i].contents,new Date(recMessage[i].writing_date),recMessage[i].me_at);					
				}
			}else if(recData.header=="send_message"){
				updateChat(recData);
				var nowId=$('#chat-header .member-id').text();
				if(nowId==recMessage.sender || nowId==recMessage.receiver){
					//$('.clicked').find('span').removeClass('check-msg');
					messageAdd(recMessage.contents,new Date(recMessage.writing_date),recMessage.me_at);					
				}
			}else if(recData.header=='search_member'){
				popSearchMember(recMessage);
			}
			
		}
		
		//Add Event
		//유저 선택전 전송 막기
	    $("#chat-footer>input[type=button]").prop('disabled',true);
	    
		//유저목록 클릭이벤트
		$(document).on("click",".discussion",function(){
			memberClick(this,ws);
		});//end discussion event
		
		//유저 검색
		$("#people-search>input[type=text]").on("change",searchMember);
		
		//메시지 전송
	    $("#chat-footer>input[type=button]").on("click",function(){
	    	var textMessage = $('#chat-footer>textarea').val();
	    	var other = $('#chat-header .member-id').text();
	    	if(textMessage!==''){
	    		var contents=sendForm(textMessage,other);
		    	sendText(ws,"send_message",contents);
	    	}
	    });

	    $("#chat-footer>textarea").keyup(function(e) {
	    	e.preventDefault(); 
			var code = e.keyCode ? e.keyCode : e.which;
			if(code==13 && e.shiftKey!=true){
				$("#chat-footer>input[type=button]").trigger("click");
				return false;
			}
	    });
		
		//팝업 이벤트
		$("#set-acc").on("click",showAccPopup)
	    $("#add-user").on("click",showPopup);
		$(".pop-close").on("click",function(){
			closePopup(this);
		});	 	
		
		//팝업 검색
		$("#pop-search>input[type=text]").on("keydown",function(event){
			if(event.keyCode==13){
				popSearch(ws);
			}
		});		
		$("#pop-search>input[type=button]").on("click",function(){
			popSearch(ws);
		});	
		
		//팝업 검색결과 클릭
		$(document).on("click",".pop-member",function(){
			$('.pop-member').removeClass("selected");
			$(this).addClass("selected");
		});
		
		$("#pop-footer>input").on("click",function(){
			var selected = $('.selected').toArray()[0];
			var selectedSrc=$(selected).find('img').prop('src');
			var selectedNick=$(selected).find('.nick-name').text();
			var selectedid=$(selected).find('.member-id').text();
			
			var divForm = new memberDivForm('discussion',selectedSrc,selectedNick,selectedid);
			var resultForm = prependMember(divForm);
			memberClick(resultForm,ws);
			
			closePopup(this);
		});
		
	}//end init
	
	function memberClick(clickNode,ws){
		if(!$(clickNode).hasClass("clicked")){
			if("${accompany_at=='Y'}"=="true"){
				$('#set-acc').css('display','block');
			}
			
			//채팅 header에 유저 정보 표시
			$('#user-info').empty();
			var information = $(clickNode).children();
			for(var i=0; i<information.length; i++){
				$('#user-info').append(information[i].cloneNode(true));
			}
			
		    $("#chat-footer>input[type=button]").prop('disabled',false);//전송버튼 사용가능
			$('#chat-message').empty();//메시지 창 비우기
			$('.discussion').removeClass("clicked");//이전에 선택된 노드의 클래스 제거
			$(clickNode).addClass('clicked');//클래스 붙이기

			if($(clickNode).parent().is('#search-list')){//search의 discuss일경우 people-list에서도 변경
				var people=$('#people-list>.discussion .member-id').toArray();
				for(var i in people){						
					if($(people[i]).text()===$(clickNode).find('div.member-id').text()){
						$(people[i]).parent().parent().addClass('clicked');
						break;
					}				
				}//end for
			}//end inner if
			
			var contents = {
				member_id:$(clickNode).find('div.member-id').text()
			}
			sendText(ws,"chat_list",contents);
		}//end if
	}
	
	
	function memberDivForm(desc,imgSrc,nick,id){
		var result={
			"desc" : desc,
			"imgSrc" : imgSrc,
			"nick" : nick,
			"id" : id
		}
		return result;
	}
	
	function makeMemberDiv(/*memberDivForm object*/data){
		var container = document.createElement('div');
		var imgDiv=document.createElement('img');
		var infoDiv=document.createElement('div');
		var nickDiv=document.createElement('div');
		var idDiv=document.createElement('div');
		
		$(imgDiv).prop('src',data.imgSrc);
		$(container).addClass(data.desc);
		$(infoDiv).addClass('info');
		$(nickDiv).addClass('nick-name');
		$(idDiv).addClass('member-id');
		$(nickDiv).text(data.nick);
		$(idDiv).text(data.id);
		
		container.append(imgDiv);
		container.append(infoDiv);
		infoDiv.append(nickDiv);
		infoDiv.append(idDiv);
		return container;
	};
	
	function prependMember(/*memberDivForm*/ data){	
		var userList=$('#people-list>.discussion').toArray();
		
		for(var i in userList){
			if($(userList[i]).find('.member-id').text()==data.id){
				$(userList[i]).find('img').removeClass('receive');
				$('#people-list').prepend(userList[i]);
				return userList[i];
			}
		}
		
		var newDiscussion=makeMemberDiv(data);
		var check = document.createElement('span');
		$(newDiscussion).prepend(check);
		$('#people-list').prepend(newDiscussion);
		return newDiscussion;
	}
	
	function updateChat(recData){
		var res = recData.body.result;
		var divData;
		
		if(res.me_at=='true'){//보낸사람이 나면
			divData = new memberDivForm('discussion',null,null,res.receiver);
		}else{
			var imgSrc=recData.body.sender_info.profile_image;
			if(imgSrc==null){
				imgSrc='${contextPath}/resources/image/no_img.jpg';
			}else{
				if(recData.body.sender_info.member_type!='kakao' && recData.body.sender_info.member_type!='naver'){
					imgSrc='${contextPath}'+imgSrc;
				}
			}
			divData = new memberDivForm('discussion',imgSrc,recData.body.sender_info.nick_name,res.sender);
		}
		
		var updateMember = prependMember(divData);
		
		if(res.me_at!='true'){
			var cImg = $(updateMember).find('img').toArray()[0];
			var cloneImg = cImg.cloneNode(true);
			cImg.parentNode.replaceChild(cloneImg,cImg);
			$(cloneImg).addClass('receive');
		}
	}
	
	function popSearch(/*웹소켓 객체*/ws){
		$('#pop-footer>input').prop('disabled',false);
		var input=$('#pop-search>input[type=text]').toArray()[0];
		//입력값으로 select날려서 결과받아 뿌리기
		var contents={
			keyword : $(input).val()
		}
		sendText(ws,"search_member",contents);
	}

	function showAccPopup(){
		$('#acc-info').css('display','block');
		$('#pop-footer>input').prop('disabled',true);		
	}	
	
	function showPopup(){
		$('#pop-list').empty();
		$('#user-add').css('display','block');
		$('#pop-footer>input').prop('disabled',true);		
	}
	
	function closePopup(clickNode){
		$(clickNode).closest('.pop-con').css('display','none');
	}

	function popSearchMember(/*회원 정보 객체 배열*/members){
		$('#pop-list').empty();
		if(members.length==0){
			var noResult=document.createElement('div');
			$(noResult).addClass('no-result');
			$(noResult).text('검색된 유저가 없습니다.');
			$('#pop-list').append(noResult);
		}else{
			for(var k in members){
				var	mem = members[k];
				
				var imgSrc=mem.profile_image;
				if(imgSrc==null){
					imgSrc='${contextPath}/resources/image/no_img.jpg';
				}else{
					if(mem.member_type!='kakao' && mem.member_type!='naver'){
						imgSrc='${contextPath}'+imgSrc;
					}
				}

				var divData = new memberDivForm('pop-member',imgSrc,mem.nick_name,mem.member_id);
				var popContainer = makeMemberDiv(divData);
				$('#pop-list').append(popContainer);
			}
		}
	}
	
	function searchMember(){
		var input = $("#people-search>input[type=text]").val();
		if(input===''){
			$('#people-list').show();
			$('#search-list').hide();	
		}else{
			$('#people-list').hide();
			$('#search-list').show();
			$('#search-list').empty();
			var discussions = $('#people-list>div.discussion').toArray();
			for(var j=0; j<discussions.length; j++){
				var nick = $(discussions[j]).find('div.nick-name').toArray()[0];
				var member = $(discussions[j]).find('div.member-id').toArray()[0];
				if($(nick).text().indexOf(input)!==-1 || $(member).text().indexOf(input)!==-1 ){
					var discussClone=discussions[j].cloneNode(true);
					document.getElementById('search-list').append(discussClone);
				}
			}
			
		}
	}
	
	function messageAdd(inputText,date,meCheck){//메시지,날짜,본인
        var msgBox = document.createElement("div");
        var msgPre = document.createElement("pre"); 
        var msgDate = document.createElement("div");
        
        if(meCheck=="true"){
			$(msgBox).addClass("message-right");        	
        }else{
			$(msgBox).addClass("message-left");
        }
        
        $(msgDate).text(date.toLocaleString('ko-KR'));        
        $(msgPre).text(inputText);
        
        $(msgBox).append(msgPre);
        $(msgBox).append(msgDate);
        $("#chat-message").append(msgBox);
        
        $("#chat-footer>textarea").val('')
        
        //스크롤 밑으로 내리기
        var height=document.getElementById('chat-message').scrollHeight;
        $('#chat-message').scrollTop(height);
	}
	
 });
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>   
<body>
<div id="main-contents">
	<ul class="nav nav-tabs">
		<c:choose>
			<c:when test="${accompany_at=='Y'}">
				<li class="nav-item">
					<a class="nav-link" href="${contextPath}/my/message/main">일반채팅</a>
				</li>
				<li class="nav-item">
					<a class="nav-link active" href="${contextPath}/my/message/accompany">매칭채팅</a>
				</li>			
			</c:when>
			<c:otherwise>
				<li class="nav-item">
					<a class="nav-link active" href="${contextPath}/my/message/main">일반채팅</a>
				</li>
				<li class="nav-item">
					<a class="nav-link" href="${contextPath}/my/message/accompany">매칭채팅</a>
				</li>			
			</c:otherwise>
		</c:choose>
	</ul>
   <div class="msg-container">
      <div id="people">
         <div id="people-search">
            <input type="text" placeholder="검색">
         </div>
         <div id="people-list">
         	<c:forEach var="memberVO" items="${memberList}">
	         	<div class="discussion">
	         		<c:choose>
	         			<c:when test="${memberVO.profile_image ==null }">
	         				<img src="${contextPath}/resources/image/no_img.jpg">
	         			</c:when>
	         			<c:otherwise>
							<c:if test="${result.member_type=='naver' || result.member_type=='kakao'}">
								<img src="${memberVO.profile_image}">
							</c:if>
							<c:if test="${result.member_type!='naver' && result.member_type!='kakao'}">
								<img src="${contextPath}${memberVO.profile_image}">
							</c:if>    			
	         			</c:otherwise>
	         		</c:choose>
	         		<div class="info">
	         			<div class="nick-name">${memberVO.nick_name}</div>
	        			<div class="member-id">${memberVO.member_id}</div>
	         		</div>
	         	</div>
         	</c:forEach>
         </div>
		<div id="search-list"></div>
      </div>
      <div id="chat">
         <div id="chat-header">
			<div id="user-info"></div>
			<c:if test="${accompany_at!='Y'}">
				<i class="fas fa-user-plus fa-3x" id="add-user"></i>
			</c:if>
			<c:if test="${accompany_at=='Y'}">
				<i class="fas fa-info-circle fa-3x" id="set-acc"></i>
			</c:if>
         </div>
         <div id="chat-message">
			<div id="chat-main">
				쪽지를 확인할 대상을 선택해주세요
			</div>
         </div>
         <div id="chat-footer">
            <textarea></textarea>
            <input type="button" value="전송">
         </div>
      </div>
   </div>
</div>
   	<div id="user-add" class="pop-con">
		<div id="pop-up" class="pop-up">
			<div id="pop-header" class="pop-header">
				<div>새	쪽지</div>
				<a class="pop-close">&times;</a>			
			</div>
			<div id="pop-search" class="pop-search">
				<input type="text" placeholder="새로운 유저 검색"/>
				<input type="button" value="검색">
			</div>
			<div id="pop-list" class="pop-list"></div>
			<div id="pop-footer" class="pop-footer">
				<input type="button" value="선택">			
			</div>
		</div>
	</div>
   	<div id="acc-info" class="pop-con">
		<div class="pop-up">
			<div class="pop-header">
				<div>대상 매칭 확인</div>
				<a class="pop-close">&times;</a>			
			</div>
			<div id="acc-search" class="pop-search">

			</div>
			<div id="acc-list"></div>
			<div id="acc-footer" class="pop-footer">
				<input type="button" value="닫기">		
			</div>
		</div>
	</div>
</body>
</html>