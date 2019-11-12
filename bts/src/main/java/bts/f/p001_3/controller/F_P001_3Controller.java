package bts.f.p001_3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface F_P001_3Controller {
	public ModelAndView searchReview(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
