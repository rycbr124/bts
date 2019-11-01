package bts.i.p001.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("i_p001")
@RequestMapping(value="/planner")
public class I_P001ControllerImpl implements I_P001Controller {
	
	@Override
	@RequestMapping(value="/planner" , method = {RequestMethod.POST , RequestMethod.GET})
	public ModelAndView I_P001_D001(HttpServletRequest request, HttpServletResponse response)throws Exception{
		System.out.println("planner : ");
		ModelAndView mav = new ModelAndView("/i/p001/d001");
		return mav;
	}
}