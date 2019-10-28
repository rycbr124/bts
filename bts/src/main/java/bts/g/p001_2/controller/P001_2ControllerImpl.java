package bts.g.p001_2.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller("g_p001_2")
@RequestMapping(value="/recommend")
public class P001_2ControllerImpl implements P001_2Controller{

	@Override
	@RequestMapping(value="/recommend1" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView G_P001_D001(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");		
		System.out.println("test : ");
		ModelAndView mav = new ModelAndView("/g/p001_2/d001");
		return mav;
	}

}
