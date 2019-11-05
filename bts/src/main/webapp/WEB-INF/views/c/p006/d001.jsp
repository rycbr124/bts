<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	isELIgnored="false"
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		openSocket();
		
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
		};
		
		function openSocket(){
			var con = new socketConn();
			var ws=con.getWs();
			
			ws.onopen=function(message){
				console.log(message);
				console.log(message.value);
			}
			ws.onclose=function(message){
				alert("end : "+message.value);
			}
			ws.onerror=function(message){
				console.log("error");
			}
			ws.onmessage=function(message){
				console.log(message);
				var message = document.createTextNode(message.data+"\n");
				messageTextArea.appendChild(message);
			}
			
			function sendMessage(){
				var textMessage = document.getElementById("textMessage");
				ws.send(textMessage.value);
				textMessage.value="";
			}
			
			$('input[type=button]').on('click',function(){
				sendMessage();	
			});
			
			window.addEventListener("unload",function(){
				ws.close();
			});
		}				
	});
</script>
</head>
<body>
	<input type="text" id="textMessage" >
	<input type="button" id="send" value="전송">
	<textarea id="messageTextArea" rows="10" cols="50"></textarea>
</body>
</html>