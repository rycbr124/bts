package bts.c.p006.controller.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import bts.b.p001.VO.B_P001VO;
import bts.c.p006.service.C_P006Service;
import bts.c.p006.vo.C_P006FormVO;
import bts.c.p006.vo.C_P006VO;

public class ChatWebSocketHandler extends TextWebSocketHandler {
	@Autowired
	Provider<C_P006VO> c_p006Provider;
	
	@Autowired
	Provider<B_P001VO> b_p001Provider;
	
	@Autowired
	Provider<C_P006FormVO> formProvider;
	
	@Autowired
	C_P006Service c_p006Service;

	private static final Map<WebSocketSession,B_P001VO> sessionList = new HashMap<>();
	private static final Map<WebSocketSession,B_P001VO> AccsessionList = new HashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		Map<String, Object> map = session.getAttributes();
		B_P001VO b_p001VO= (B_P001VO) map.get("memberInfo");
		String accompany_at = (String) map.get("accompany_at");
		if(accompany_at.equals("Y")) {
			AccsessionList.put(session, b_p001VO);									
		}else {
			sessionList.put(session, b_p001VO);						
		}
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		Map<String, Object> map = session.getAttributes();
		String accompany_at = (String) map.get("accompany_at");
		if(accompany_at.equals("Y")) {
			AccsessionList.remove(session);
		}else {
			sessionList.remove(session);			
		}
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		//message.getPayload(); 보낸 메시지
		//session.sendMessage(message);클라이언트로 메시지 전송
		//provider.get(); prototype으로 빈 가져오기
		Map<String, Object> map = session.getAttributes();
		String accompany_at = (String) map.get("accompany_at");
		Map<WebSocketSession,B_P001VO> memberList = new HashMap<>();
		if(accompany_at.equals("Y")) {
			memberList = AccsessionList;									
		}else {
			memberList = sessionList;									
		}
		B_P001VO b_p001VO = memberList.get(session);
		ObjectMapper mapper = new ObjectMapper();
		C_P006FormVO c_p006FormVO = mapper.readValue(message.getPayload(), C_P006FormVO.class);
		String header=c_p006FormVO.getHeader();
		
		if(header.equals("chat_list")){
			showChatList(c_p006FormVO,session,b_p001VO,mapper,accompany_at);
		}else if(header.equals("send_message")) {
			sendMessage(c_p006FormVO,session,b_p001VO,mapper,accompany_at,memberList);
		}else if(header.equals("search_member")) {
			searchMember(c_p006FormVO,session,b_p001VO,mapper);
		}

	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		exception.printStackTrace();
	}
	
	private void showChatList(C_P006FormVO c_p006FormVO,WebSocketSession session,B_P001VO b_p001VO,ObjectMapper mapper,String accompany_at) throws Exception {
		HashMap<String,Object> body = c_p006FormVO.getBody();
		String member_id= (String) body.get("member_id");
		C_P006VO c_p006VO = c_p006Provider.get();
		c_p006VO.setSender(b_p001VO.getMember_id());
		c_p006VO.setReceiver(member_id);
		c_p006VO.setAccompany_at(accompany_at);

		List<C_P006VO> msgResult = c_p006Service.selectMessageList(c_p006VO);
		body.clear();
		body.put("result",msgResult);		
		
		String result=mapper.writeValueAsString(c_p006FormVO);
		session.sendMessage(new TextMessage(result));
	}
	
	private void sendMessage(C_P006FormVO c_p006FormVO,WebSocketSession session,B_P001VO b_p001VO,ObjectMapper mapper,String accompany_at,Map<WebSocketSession,B_P001VO> memberList) throws Exception {
		HashMap<String,Object> body = c_p006FormVO.getBody();
		String receiver=(String) body.get("receiver");
		String message=(String) body.get("message");
		
		C_P006VO c_p006VO = c_p006Provider.get();
		c_p006VO.setSender(b_p001VO.getMember_id());
		c_p006VO.setReceiver(receiver);
		c_p006VO.setContents(message);
		c_p006VO.setAccompany_at(accompany_at);
		c_p006Service.insertMessage(c_p006VO);
		for(Entry<WebSocketSession, B_P001VO> entry : memberList.entrySet()) {
			WebSocketSession recSession = entry.getKey();
			B_P001VO recVO = entry.getValue();	

			if(recVO.getMember_id().equals(receiver)) {
				try {	
				c_p006VO.setMe_at("false");				
				body.clear();
				
				B_P001VO senderVO = b_p001Provider.get();
				senderVO.setProfile_image(b_p001VO.getProfile_image());
				senderVO.setMember_type(b_p001VO.getMember_type());
				senderVO.setNick_name(b_p001VO.getNick_name());

				body.put("result", c_p006VO);
				body.put("sender_info", senderVO);
				String result=mapper.writeValueAsString(c_p006FormVO);
				recSession.sendMessage(new TextMessage(result));
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		c_p006VO.setMe_at("true");
		body.clear();
		body.put("result", c_p006VO);
		String result = mapper.writeValueAsString(c_p006FormVO);
		if(session.isOpen()) {
			session.sendMessage(new TextMessage(result));
		}
	}
	
	private void searchMember(C_P006FormVO c_p006FormVO,WebSocketSession session,B_P001VO b_p001VO,ObjectMapper mapper) throws Exception {
		HashMap<String,Object> body = c_p006FormVO.getBody();
		String keyword= (String) body.get("keyword");
		
		Map<String,String> searchMap = new HashMap<>();
		searchMap.put("keyword", keyword);
		searchMap.put("member_id", b_p001VO.getMember_id());
		List<B_P001VO> searchResult = c_p006Service.selectSearchList(searchMap);
		body.clear();
		body.put("result",searchResult);
		String result=mapper.writeValueAsString(c_p006FormVO);
		session.sendMessage(new TextMessage(result));
	}
	
	
}
