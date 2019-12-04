package bts.a.p002.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface A_P002Controller {
	public ModelAndView showPnishList(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
