package bts.d.p001_4.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import bts.d.p001_4.service.D_P001_4Service;
import bts.d.p001_4.vo.D_P001_4VO;

@Controller("d_p001_4")
@RequestMapping(value="/community")
public class D_P001_4ControllerImpl implements D_P001_4Controller{
	
	@Autowired
	D_P001_4Service d_p001_4Service;
	
	@Autowired
	D_P001_4VO d_p001_4VO;

	@Override
	@RequestMapping(value="/plan_list" ,method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView searchArticle(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<D_P001_4VO> listArticle = d_p001_4Service.searchArticle();
		ModelAndView mav = new ModelAndView("/d/p001_4/d001");
		mav.addObject("listArticle", listArticle);
		return mav;
		
	}

}
