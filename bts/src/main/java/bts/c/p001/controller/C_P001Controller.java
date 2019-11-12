package bts.c.p001.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface C_P001Controller {
	public ModelAndView pageInit(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
