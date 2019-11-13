package bts.c.p006.controller.component;

import java.sql.Date;
import java.util.Calendar;
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
	Provider<C_P006FormVO> formProvider;
	
	@Autowired
	C_P006Service c_p006Service;
	
	private static final Map<WebSocketSession,B_P001VO> sessionList = new HashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		Map<String, Object> map = session.getAttributes();
		B_P001VO b_p001VO= (B_P001VO) map.get("memberInfo");
		try {
			ObjectMapper mapper = new ObjectMapper();
			C_P006VO c_p006VO = c_p006Provider.get();
			c_p006VO.setSender(b_p001VO.getMember_id());
			c_p006VO.setReceiver("test1");
			List<C_P006VO> msgResult = c_p006Service.selectMessageList(c_p006VO);
			String result=mapper.writeValueAsString(msgResult);
			System.out.println(result);
			session.sendMessage(new TextMessage(result));
		}catch(Exception e) {
			e.printStackTrace();
		}
		sessionList.put(session, b_p001VO);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception{
		sessionList.remove(session);
	}
	
	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception{
		B_P001VO b_p001VO = sessionList.get(session);
		sendTo(session,message.getPayload(),b_p001VO);//보내주는 메소드
		//message.getPayload(); 보낸 메시지
		//session.sendMessage(message);클라이언트로 메시지 전송
		//provider.get(); prototype으로 빈 가져오기
		
		C_P006VO messageVO = testMakeMessage(3,b_p001VO.getMember_id(),"other",new Date(Calendar.getInstance().getTimeInMillis()),message.getPayload(),"true");		
		ObjectMapper mapper = new ObjectMapper();
		String result=mapper.writeValueAsString(messageVO);
		System.out.println(result);
		session.sendMessage(new TextMessage(result));
	}
	
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		exception.printStackTrace();
	}
	
	private void sendTo(WebSocketSession senderSession, String message, B_P001VO senderVO) {
		try {
			for(Entry<WebSocketSession, B_P001VO> entry : ChatWebSocketHandler.sessionList.entrySet()) {
				WebSocketSession recSession = entry.getKey();
				B_P001VO recVO = entry.getValue();
				if(!recSession.getId().equals(senderSession.getId())) {
					C_P006VO messageVO=testMakeMessage(5,senderVO.getMember_id(),recVO.getMember_id(),new Date(Calendar.getInstance().getTimeInMillis()),message,"false");							
					ObjectMapper mapper = new ObjectMapper();
					String result=mapper.writeValueAsString(messageVO);
					System.out.println(result);
					recSession.sendMessage(new TextMessage(result));
				}
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private C_P006VO testMakeMessage(int item_no,String sender,String receiver,Date writing_date,String contents,String me_at) {
		C_P006VO c_p006VO = c_p006Provider.get();
		c_p006VO.setItem_no(item_no);
		c_p006VO.setSender(sender);
		c_p006VO.setReceiver(receiver);
		c_p006VO.setWriting_date(writing_date);
		c_p006VO.setContents(contents);
		c_p006VO.setMe_at(me_at);
		return c_p006VO;
	}

}
