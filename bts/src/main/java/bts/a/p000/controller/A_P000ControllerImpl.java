package bts.a.p000.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("a_p000")
@RequestMapping(value="/admin")
public class A_P000ControllerImpl implements A_P000Controller{

	@Override
	@RequestMapping(value="/main" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView adminMain(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/a/p001/d001");
		return mav;
	}

}
