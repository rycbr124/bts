package bts.c.p006.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import org.springframework.web.servlet.ModelAndView;

public interface C_P006Controller {
	public ModelAndView getChatViewPage(HttpServletRequest request, HttpServletResponse response);
	public void onOpen(Session session);
	public void onMessage(String message, Session session);
	public void onError(Throwable t);
	public void onClose(Session session) ;
	
}
