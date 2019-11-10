package bts.f.p001_3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("f_p001_3")
@RequestMapping(value="/community")
public class F_P001_3ControllerImpl implements F_P001_3Controller{

	@Override
	@RequestMapping(value="/review_list" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView searchReview(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/f/p001_3/d001");
		return mav;
	}

}
