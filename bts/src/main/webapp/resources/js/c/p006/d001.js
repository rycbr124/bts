function socketConn(url){
	var ws;
	openSocket();
	
	function openSocket(){
		if(ws!==undefined && ws.readyState!==WebSocket.CLOSED){
			console.log("WebSocket is already opened.");
		}else{
			ws = new WebSocket(url);
		}
	};
	
	this.getWs=function(){
		return ws;
	};	
}

function sendText(/*웹소켓 객체*/ws,/*String*/head,/*Object*/contents){
	var result={
		header : head,
		body : contents
	}
	result=JSON.stringify(result);
	ws.send(result);
}

function sendForm(/*메시지 String*/message,/*메시지 전송 대상 id*/receiver){
	var contents={
		message : message,
		receiver : receiver
	}
	return contents
}