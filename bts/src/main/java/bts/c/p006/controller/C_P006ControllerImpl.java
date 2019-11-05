package bts.c.p006.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import bts.common.GetHttpSessionForWebSocket;
import bts.b.p001.VO.B_P001VO;
import bts.c.p006.vo.C_P006VO;

@Controller
@RequestMapping(value="/my")
@ServerEndpoint(value="/msg", configurator = GetHttpSessionForWebSocket.class)
public class C_P006ControllerImpl{
	private static final Map<Session,B_P001VO> sessionList = new HashMap<>();

	@RequestMapping(value="/message/main")
	public ModelAndView getChatViewPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/c/p006/d001");
		return mav;
	}
	
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		HttpSession hss = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		B_P001VO hssVO = (B_P001VO) hss.getAttribute("memberInfo");
		System.out.println(config.getUserProperties());
		System.out.println("Open session id : " + session.getId());
		try {
			final Basic basic = session.getBasicRemote();
			ObjectMapper mapper = new ObjectMapper();
			String result=mapper.writeValueAsString(testInitList(hssVO.getMember_id()));
			System.out.println(result);
			basic.sendText(result);
			//basic.sendText("Connection Established");
		}catch(Exception e){
			e.printStackTrace();
		}
		sessionList.put(session,hssVO);
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		B_P001VO b_p001VO = sessionList.get(session);
		sendTo(session,message,b_p001VO);//보내주는 메소드
		try {//받은곳에 쏴주기
			final Basic basic = session.getBasicRemote();
			C_P006VO messageVO=testMakeMessage(3,b_p001VO.getMember_id(),"other",new Date(Calendar.getInstance().getTimeInMillis()),message,"true");		
			ObjectMapper mapper = new ObjectMapper();
			String result=mapper.writeValueAsString(messageVO);
			System.out.println(result);
			basic.sendText(result);
			//basic.sendText("to : "+message); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@OnError
	public void onError(Throwable t) {
		System.out.println("1111111111111111111111111 error");
		t.printStackTrace();
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("11111111111111111 종료");
		sessionList.remove(session);
	}
	
	private void sendTo(Session senderSession, String message, B_P001VO senderVO) {
		try {
			for(Entry<Session,B_P001VO> entry : C_P006ControllerImpl.sessionList.entrySet()) {
				Session recSession = entry.getKey();
				B_P001VO recVO = entry.getValue();
				if(!recSession.getId().equals(senderSession.getId())) {
				//if(!recVO.getMember_id().equals("other")) {
					C_P006VO messageVO=testMakeMessage(5,senderVO.getMember_id(),recVO.getMember_id(),new Date(Calendar.getInstance().getTimeInMillis()),message,"false");		
					ObjectMapper mapper = new ObjectMapper();
					String result=mapper.writeValueAsString(messageVO);
					System.out.println(result);
					recSession.getBasicRemote().sendText(result);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private C_P006VO testMakeMessage(int item_no,String sender,String receiver,Date writing_date,String contents,String me_at) {
		C_P006VO c_p006VO = new C_P006VO();
		c_p006VO.setItem_no(item_no);
		c_p006VO.setSender(sender);
		c_p006VO.setReceiver(receiver);
		c_p006VO.setWriting_date(writing_date);
		c_p006VO.setContents(contents);
		c_p006VO.setMe_at(me_at);
		return c_p006VO;
	}
	
	private List<C_P006VO> testInitList(String id){
		C_P006VO vo = new C_P006VO();
		C_P006VO vo2 = new C_P006VO();
		vo.setItem_no(1);
		vo.setSender(id);
		vo.setReceiver("other");
		vo.setWriting_date(new Date(Calendar.getInstance().getTimeInMillis()));
		vo.setContents("test메시지");
		vo.setMe_at("true");
		
		vo2.setItem_no(2);
		vo2.setSender("other");
		vo2.setReceiver(id);
		vo2.setWriting_date(new Date(Calendar.getInstance().getTimeInMillis()));
		vo2.setContents("test메시지2");
		vo2.setMe_at("false");
		
		List<C_P006VO> list = new ArrayList<>();
		list.add(vo);
		list.add(vo2);
		return list;
	}

}
