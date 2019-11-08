package bts.d.p001_4.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

public interface D_P001_4Controller {
	 public ModelAndView searchArticle(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
