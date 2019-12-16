package bts.a.p000.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bts.a.p000.service.A_P000Service;
import bts.e.p001.VO.E_P001VO;
import bts.f.p001_3.vo.F_P001_3VO;
import bts.i.p002.VO.I_P002VO_1;

@Controller("a_p000")
@RequestMapping(value="/admin")
public class A_P000ControllerImpl implements A_P000Controller{
	@Autowired
	A_P000Service a_p000Service;

	@Override
	@RequestMapping(value="/main" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView mainAdmin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mav = new ModelAndView("/a/p000/d001");
		int countMember = a_p000Service.countMember();
		int countContact = a_p000Service.countContact();
		int countReport = a_p000Service.countReport();
		long countTotal = a_p000Service.countAnswer();
		Map<String, String> countWrite = a_p000Service.countWrite();
		List<I_P002VO_1> planner = a_p000Service.selectPlanner();
		List<F_P001_3VO> article = a_p000Service.selectArticle();
		List<E_P001VO> accompany = a_p000Service.selectAccompany();
		mav.addObject("countMember", countMember);
		mav.addObject("countContact", countContact);
		mav.addObject("countReport", countReport);
		mav.addObject("countTotal", countTotal);
		mav.addObject("countWrite", countWrite);
		mav.addObject("planner",planner);
		mav.addObject("article",article);
		mav.addObject("accompany",accompany);
		System.out.println(planner);
		return mav;
	}
	
}
