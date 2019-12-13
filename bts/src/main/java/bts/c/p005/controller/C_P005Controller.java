package bts.c.p005.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface C_P005Controller {
	public ModelAndView searchWishlist(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
