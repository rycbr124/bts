package bts.c.p006.controller;


import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.RemoteEndpoint.Basic;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bts.common.GetHttpSessionForWebSocket;

@Controller
@ServerEndpoint(value="/ws", configurator = GetHttpSessionForWebSocket.class)
public class C_P006ControllerImpl implements C_P006Controller{
	private static final Map<Session,HttpSession> sessionList = new HashMap<Session,HttpSession>();
	/*
	 * */
	
	@RequestMapping(value="/message/main")
	public ModelAndView getChatViewPage(ModelAndView mav) {
		mav.setViewName("/c/p006/d001");
		return mav;
	}
	
	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		/*
		 * */
		HttpSession hss = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		System.out.println("Open session id : " + session.getId());
		//System.out.println("Http session : "+ hss);
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("Connection Established");
		}catch(Exception e){
			e.printStackTrace();
		}
		sessionList.put(session,hss);
	}
	
	/*
	private void sendAllSessionToMessage(Session self,String message) {
		try {
			for(Session session : C_P006ControllerImpl.sessionList ) {
				if(!self.getId().equals(session.getId())) {
					session.getBasicRemote().sendText(message);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	 * */
	
	private void sendAll (Session ss, String message, HttpSession hss) {
		try {
			for(Entry<Session,HttpSession> entry : C_P006ControllerImpl.sessionList.entrySet()) {
				Session ws_ss = entry.getKey();
				HttpSession http_ss = entry.getValue();
				if(!hss.getId().equals(http_ss.getId())) {
					ws_ss.getBasicRemote().sendText("other : "+message);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@OnMessage
	public void onMessage(String message, Session session) {
		HttpSession hss = sessionList.get(session);
		sendAll(session,message,hss);
		try {
			final Basic basic = session.getBasicRemote();
			basic.sendText("to : "+message); 
		}catch(Exception e){
			e.printStackTrace();
		}
		//sendAllSessionToMessage(session,message);
	}
	
	@OnClose
	public void onClose(Session session) {
		sessionList.remove(session);
	}
	
}
