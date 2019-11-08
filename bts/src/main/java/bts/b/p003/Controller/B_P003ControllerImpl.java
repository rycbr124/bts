package bts.b.p003.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bts.b.p003.Service.B_P003Service;

@Controller("b_p003")
@RequestMapping(value="/find")
public class B_P003ControllerImpl implements B_P003Controller{

	@Autowired
	B_P003Service p003Service;
	
	@Override
	@RequestMapping(value="/findIdMain")
	public ModelAndView b_p003_d001(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView("/b/p003/d001");
		return mav;
	}
	
	@Override
	@RequestMapping(value="/findPwMain")
	public ModelAndView b_p003_d002(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ModelAndView mav = new ModelAndView("/b/p003/d002");
		return mav;
	}

	@Override
	@RequestMapping(value="/findId")
	public String find_id(HttpServletResponse response, String email, Model model) throws Exception {
		model.addAttribute("id", p003Service.find_id(response, email));
		return "/b/p003/d001";
	}
	
}
