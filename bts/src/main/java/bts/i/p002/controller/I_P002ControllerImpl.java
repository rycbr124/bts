package bts.i.p002.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller("i_p002")
@RequestMapping(value="/plan")
public class I_P002ControllerImpl implements I_P002Controller{
	@Override
	@RequestMapping(value="/plan", method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView I_P002_D001(HttpServletRequest request, HttpServletResponse response)throws Exception{
		System.out.println("plan : ");
		ModelAndView mav = new ModelAndView("/i/p002/d001");
		return mav;
				
	}
}
