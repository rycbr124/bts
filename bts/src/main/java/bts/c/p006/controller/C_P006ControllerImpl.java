package bts.c.p006.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Provider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import bts.b.p001.VO.B_P001VO;
import bts.c.p001.service.C_P001Service;
import bts.c.p006.service.C_P006Service;

@Controller
@RequestMapping(value="/my")
public class C_P006ControllerImpl implements C_P006Controller{
	@Autowired
	Provider<B_P001VO> bProvider;

	@Autowired
	C_P006Service c_p006Service;
	
	@Autowired
	C_P001Service c_p001Service;
	
	@ModelAttribute("member_id")
	public String getMember_id(HttpServletRequest request) {
		HttpSession session = request.getSession();
		B_P001VO b_p001VO = (B_P001VO) session.getAttribute("memberInfo");
		String member_id = b_p001VO.getMember_id();
		return member_id;
	}
	
	@RequestMapping(value="/message/main")
	public ModelAndView getChatViewPage(@ModelAttribute("member_id") String member_id, @RequestParam(value="target_id", required=false) String target_id
			, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav = makeMav(member_id, target_id,"N");
		return mav;
	}
	
	@RequestMapping(value="/message/accompany")
	public ModelAndView getAccompanyViewPage(@ModelAttribute("member_id") String member_id, @RequestParam(value="target_id", required=false) String target_id
			, HttpServletRequest request, HttpServletResponse response) throws Exception{
		ModelAndView mav = makeMav(member_id, target_id,"Y");
		return mav;
	}	
	
	private ModelAndView makeMav(String member_id, String target_id, String accompany_at) {
		List<B_P001VO> initList = new ArrayList<>();
		Map<String,String> searchMap = new HashMap<>();
		searchMap.put("member_id", member_id);
		searchMap.put("accompany_at", accompany_at);
		initList = c_p006Service.selectMemberList(searchMap);
		
		B_P001VO targetInfo = bProvider.get();
		if(target_id!=null && !(target_id.isEmpty())) {
			targetInfo=c_p001Service.selectMember(target_id);			
			targetInfo.setPassword("");
		}
		
		ModelAndView mav = new ModelAndView("/c/p006/d001");
		mav.addObject("target_id",targetInfo);
		mav.addObject("memberList",initList);
		mav.addObject("accompany_at",accompany_at);
		return mav;
	}
}
