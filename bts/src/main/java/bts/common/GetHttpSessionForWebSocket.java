package bts.common;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

public class GetHttpSessionForWebSocket extends HttpSessionHandshakeInterceptor{
	
    @Override
    public boolean beforeHandshake(ServerHttpRequest request,ServerHttpResponse response, WebSocketHandler wsHandler,
         Map<String, Object> attributes) throws Exception {    
    
        // 위의 파라미터 중, attributes 에 값을 저장하면 웹소켓 핸들러 클래스의 WebSocketSession에 전달된다
        System.out.println("Before Handshake");
          
          
        ServletServerHttpRequest ssreq = (ServletServerHttpRequest) request;
        System.out.println("URI:"+request.getURI());
  
        HttpServletRequest req =  ssreq.getServletRequest();
        String accompany_at = req.getParameter("at");
        attributes.put("accompany_at", accompany_at);

        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

}
