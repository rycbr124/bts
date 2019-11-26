package bts.a.p004.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("a_p004")
@RequestMapping(value="/admin")
public class A_P004ControllerImpl implements A_P004Controller{
	@Override
	@RequestMapping(value="/question",method= {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView question(HttpServletRequest request, HttpServletResponse response)throws Exception{
		ModelAndView mav = new ModelAndView("/a/p004/d001");
		return mav;
	}
}
