package bts.c.p004.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/my/contact")
public class C_P004ControllerImpl {
	
	@RequestMapping(value="/list")
	public ModelAndView selectContactList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav = new ModelAndView("/c/p004/d001");
		return mav;
	}
}
