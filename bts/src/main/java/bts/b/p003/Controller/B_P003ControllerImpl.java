package bts.b.p003.Controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;
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

	@Override
	@RequestMapping(value="/findPw", method= RequestMethod.POST)
	public String find_pw(@ModelAttribute B_P001VO p001vo ,HttpServletResponse response) throws Exception {
		p003Service.find_pw(response, p001vo);
		return "/b/p003/d002";
	}
	
}
