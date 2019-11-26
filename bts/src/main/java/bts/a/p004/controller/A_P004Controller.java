package bts.a.p004.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface A_P004Controller {
	public ModelAndView question(HttpServletRequest request, HttpServletResponse response)throws Exception;
}
