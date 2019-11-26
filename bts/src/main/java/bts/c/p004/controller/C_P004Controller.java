package bts.c.p004.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface C_P004Controller {
	public ModelAndView questionMain(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ModelAndView questionWrite(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public void addQuestion(HttpServletRequest request, HttpServletResponse response)throws Exception;
}
