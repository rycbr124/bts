package bts.c.p006.controller;

import javax.websocket.EndpointConfig;
import javax.websocket.Session;

import org.springframework.web.servlet.ModelAndView;

public interface C_P006Controller {
	public ModelAndView getChatViewPage(ModelAndView mav);
	public void onOpen(Session session, EndpointConfig config) ;
	public void onMessage(String message, Session session);
	public void onClose(Session session);
}
