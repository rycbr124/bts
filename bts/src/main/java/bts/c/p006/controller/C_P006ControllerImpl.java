package bts.c.p006.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;
import bts.c.p006.service.C_P006Service;

@Controller
@RequestMapping(value="/my")
public class C_P006ControllerImpl implements C_P006Controller{
	@Autowired
	Provider<B_P001VO> bProvider;
	
	@Autowired
	C_P006Service c_p006Service;
	
	
	@RequestMapping(value="/message/main")
	public ModelAndView getChatViewPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		B_P001VO memberInfo = (B_P001VO) request.getSession().getAttribute("memberInfo");
		String id = memberInfo.getMember_id();
		List<B_P001VO> initList = new ArrayList<>();
		initList = c_p006Service.selectMemberList(id);
		System.out.println("11111111111111111111111  "+initList);
		
		ModelAndView mav = new ModelAndView("/c/p006/d001");
		mav.addObject("memberList",initList);
		
		return mav;
	}

}
