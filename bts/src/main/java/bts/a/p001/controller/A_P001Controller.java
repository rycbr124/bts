package bts.a.p001.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface A_P001Controller {
	public ModelAndView inclnAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception;
}

