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
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.fasterxml.jackson.databind.ObjectMapper;

import bts.b.p001.VO.B_P001VO;
import bts.c.p006.vo.C_P006VO;

@Controller
@RequestMapping(value="/my")
@ServerEndpoint(value="/msg", configurator = SpringConfigurator.class)
public class C_P006ControllerImpl implements C_P006Controller{
	//@Autowired
	B_P001VO b_p001VO=new B_P001VO();
	
	//@Autowired
	C_P006VO c_p006VO=new C_P006VO(); 
	
	private static final Map<Session,B_P001VO> sessionList = new HashMap<>();
	
	@RequestMapping(value="/message/main")
	public ModelAndView getChatViewPage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("/c/p006/d001");
		b_p001VO = (B_P001VO) request.getAttribute("memberInfo");
		System.out.println(b_p001VO+" 1111111111111111111111111111111111111111111111111");
		//autowired 된 bean 객체를 여기서 붙여주면 소켓에서도 쓸 수 있을까?
		return mav;
	}
	
	private List<C_P006VO> testInitList(){
		C_P006VO vo = new C_P006VO();
		C_P006VO vo2 = new C_P006VO();
		vo.setItem_no(1);
		vo.setSender("me");
		vo.setReceiver("other");
		vo.setWriting_date(new Date(Calendar.getInstance().getTimeInMillis()));
		vo.setContents("test메시지");
		vo.setMe_at("true");
		
		vo2.setItem_no(2);
		vo2.setSender("other");
		vo2.setReceiver("me");
		vo2.setWriting_date(new Date(Calendar.getInstance().getTimeInMillis()));
		vo2.setContents("test메시지2");
		vo2.setMe_at("false");
		
		List<C_P006VO> list = new ArrayList<>();
		list.add(vo);
		list.add(vo2);
		return list;
	}
	
	@OnOpen
	public void onOpen(Session session) {
		//HttpSession hss = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		System.out.println("Open session id : " + session.getId());
		try {
			final Basic basic = session.getBasicRemote();
			ObjectMapper mapper = new ObjectMapper();
			String result=mapper.writeValueAsString(testInitList());
			System.out.println(result);
			basic.sendText(result);
			//basic.sendText("Connection Established");
		}catch(Exception e){
			e.printStackTrace();
		}
		sessionList.put(session,b_p001VO);
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		b_p001VO = sessionList.get(session);
		sendTo(session,message,b_p001VO);//보내주는 메소드
		try {//받은곳에 쏴주기
			final Basic basic = session.getBasicRemote();
			System.out.println(c_p006VO+" 3333333333333333333333333333332");
			//C_P006VO vo = new C_P006VO();
			c_p006VO.setItem_no(3);
			c_p006VO.setSender("me");
			c_p006VO.setReceiver("other");
			c_p006VO.setWriting_date(new Date(Calendar.getInstance().getTimeInMillis()));
			c_p006VO.setContents(message);
			c_p006VO.setMe_at("true");
			
			ObjectMapper mapper = new ObjectMapper();
			String result=mapper.writeValueAsString(c_p006VO);
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
				//B_P001VO recVO = entry.getValue();
				if(!recSession.getId().equals(senderSession.getId())) {
					System.out.println(c_p006VO+" 22222222222222222222222222222222222");
					c_p006VO.setItem_no(5);
					c_p006VO.setSender("other");
					c_p006VO.setReceiver("me");
					c_p006VO.setWriting_date(new Date(Calendar.getInstance().getTimeInMillis()));
					c_p006VO.setContents(message);
					c_p006VO.setMe_at("false");
					
					ObjectMapper mapper = new ObjectMapper();
					String result=mapper.writeValueAsString(c_p006VO);
					System.out.println(result);
					recSession.getBasicRemote().sendText(result);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/*
	private void sendAll (Session ss, String message, HttpSession hss) {
		try {
			for(Entry<Session,HttpSession> entry : C_P006ControllerImpl.sessionList.entrySet()) {
				Session ws_ss = entry.getKey();
				HttpSession http_ss = entry.getValue();
				if(!hss.getId().equals(http_ss.getId())) {
					
					//C_P006VO vo = new C_P006VO();
					c_p006VO.setItem_no(5);
					c_p006VO.setSender("you");
					c_p006VO.setReceiver("me");
					c_p006VO.setWriting_date(new Date(Calendar.getInstance().getTimeInMillis()));
					c_p006VO.setContents(message);
					c_p006VO.setMe_at("false");
					
					ObjectMapper mapper = new ObjectMapper();
					String result=mapper.writeValueAsString(c_p006VO);
					ws_ss.getBasicRemote().sendText(result);
				}else {
					//ws_ss.getBasicRemote().sendText("me : "+message);					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	 * */
}
