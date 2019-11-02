package bts.g.p000.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("g_p000")
public class G_P000_ControllerImpl implements G_P000_Controller{

	@Override
	@RequestMapping(value="/recommend_main" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView P000_D001(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/g/p000/d001");
		return mav;
	}

}
