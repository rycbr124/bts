package bts.a.p001.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("a_p001")
@RequestMapping(value="/admin")
public class A_P001ControllerImpl implements A_P001Controller{

	@Override
	@RequestMapping(value="/inclnMain" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView inclnAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/a/p001/d001");
		return mav;
	}

}
