package bts.c.p001.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller("c_p001")
@RequestMapping(value="/my")
public class C_P001ControllerImpl implements C_P001Controller{

	@Override
	@RequestMapping(value="/profile")
	public ModelAndView pageInit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		ModelAndView mav = new ModelAndView("/c/p001/d001");
		return mav;
	}

}
