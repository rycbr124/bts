<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}" />	
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${contextPath}/resources/css/c/p006/d001.css"> <!-- 커스텀 css -->
<script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
<script>
$(document).ready(function(){
	init();
	
	function init(){
		var con = new socketConn();
		var ws=con.getWs();
		
		ws.onopen=function(message){
			console.log(message);
		}
		ws.onclose=function(message){
			alert("end");
		}
		ws.onerror=function(message){
			console.log("error");
		}
		ws.onmessage=function(message){
			var recData = JSON.parse(message.data);
			console.log("메시지")
			console.log(recData);
			if(recData instanceof Array){
				for(var i in recData){
					console.log("배열임")
					console.log(recData[i])
					messageAdd(recData[i].contents,new Date(recData[i].writing_date),recData[i].me_at);				
				}
			}else{
				console.log("배열이아님")
				messageAdd(recData.contents,new Date(recData.writing_date),recData.me_at);				
			}
		}
		
		function parseMessage(jsonMsg){
			
		}
		
		function socketConn(){
			var ws;
			openSocket();
			
			function openSocket(){
				if(ws!==undefined && ws.readyState!==WebSocket.CLOSED){
					console.log("WebSocket is already opened.");
				}else{
					var url="ws://"+"${pageContext.request.serverName}"+":"+${pageContext.request.serverPort}+"${pageContext.request.contextPath}"+"/msg";		
					ws = new WebSocket(url);
				}
			};
			
			this.getWs=function(){
				return ws;
			};	
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
		
	    function messageAdd2(){
	        var msgBox = document.createElement("div");
	        var msgPre = document.createElement("pre");          
	        $(msgBox).addClass("message-left");
	        $(msgBox).append(msgPre);
	        
	        var inputText = $("#chat-footer>textarea").val();
	        $(msgPre).text(inputText);
	        
	        $("#chat-message").append(msgBox);
	        $("#chat-footer>textarea").val('')
	        
	        //스크롤 밑으로 내리기
	        var height=document.getElementById('chat-message').scrollHeight;
	        $('#chat-message').scrollTop(height);
	    }
	    
	    function sendText(){
	    	var textMessage = $('#chat-footer>textarea').val();
	    	ws.send(textMessage);
	    	//messageAdd($("#chat-footer>textarea").val(),new Date(),"true");
	    }
	    
	    $("#chat-footer>input[type=button]").on("click",sendText);
	}
	
 });
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>   
<body>
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
	         				<img src="${contextPath}/resources/image/train.jpg">
	         			</c:when>
	         			<c:otherwise>
			         		<img src="${memberVO.profile_image}">	         			
	         			</c:otherwise>
	         		</c:choose>
	         		<div class="info">
	         			<div class="nick-name">${memberVO.nick_name}</div>
	        			<div class="member-id">${memberVO.member_id}</div>
	         		</div>
	         	</div>
         	</c:forEach>
         </div>
      </div>
      <div id="chat">
         <div id="chat-header">
			<div id="user-info">
				<img class="user-image" src="${contextPath}/resources/image/train.jpg">
            	<div class="info">
            		<div class="nick-name">test1</div>
            		<div class="member-id">테스트</div>            		
            	</div>			
			</div>
         </div>
         <div id="chat-message">
         <!-- 
            <div class="message-left">
               <pre>1</pre>
               <div>2019년 12월 11일, 오후 11:40</div>
            </div>
            <div class="message-right">
               <pre>2</pre>
               <div>2019년 12월 11일, 오후 11:40</div>
            </div>
          -->
         </div>
         <div id="chat-footer">
            <textarea></textarea>
            <input type="button" value="전송">
         </div>
      </div>
   </div>
</body>
</html>