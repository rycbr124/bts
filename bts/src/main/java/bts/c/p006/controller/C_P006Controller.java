package bts.c.p006.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface C_P006Controller {
	public ModelAndView getChatViewPage(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
}
